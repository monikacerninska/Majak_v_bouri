package logika;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hra implements IHra {

    private static final int POCET_TAHU_PRO_VAROVANI = 30;
    private static final int POCET_TAHU_DO_PROHRY = 40;
    private final HerniPlan herniPlan;
    private final SeznamPrikazu platnePrikazy;
    private boolean konec = false;
    private boolean vyhra = false;
    private boolean prohra = false;
    private int pocetTahu = 0;
    private final List<String> historie = new ArrayList<>();

    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTyp(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUloz(this));
        platnePrikazy.vlozPrikaz(new PrikazPrehraj(this));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan, this));
    }

    @Override
    public String vratUvitani() {
        return "Nad přístavem se stahuje bouře. Rozsviť maják, než bude pozdě!\n"
                + "Napiš 'napoveda', pokud nevíš, co dělat.\n\n"
                + herniPlan.getAktualniProstor().dlouhyPopis();
    }

    @Override
    public String vratEpilog() {
        if (vyhra) {
            return "Maják svítí a lodě bezpečně vplouvají do přístavu. Vyhrál jsi!";
        }
        if (prohra) {
            return "Bouře dorazila. Lodě ztroskotaly u pobřeží. Prohrál jsi.";
        }
        if (konec) {
            return "Rozhodl ses opustit přístav. Díky za hru.";
        }
        return "";
    }

    @Override
    public boolean konecHry() {
        return konec || vyhra || prohra;
    }

    void ukonciHru() {
        konec = true;
    }

    public void vyhrat() {
        vyhra = true;
    }

    @Override
    public String zpracujPrikaz(String radek) {
        if (radek == null || radek.trim().isEmpty()) {
            return "Napiš nějaký příkaz.";
        }
        String[] slova = radek.trim().split("\\s+");
        String nazevPrikazu = slova[0].toLowerCase(Locale.ROOT);
        if (konecHry() && !nazevPrikazu.equals("uloz") && !nazevPrikazu.equals("prehraj")) {
            return "Hra už skončila.";
        }
        if (!platnePrikazy.jePlatnyPrikaz(nazevPrikazu)) {
            return "Tento příkaz neznám.";
        }
        String[] parametry = new String[slova.length - 1];
        for (int i = 1; i < slova.length; i++) {
            parametry[i - 1] = slova[i];
        }
        boolean tahHry = !nazevPrikazu.equals("napoveda")
                && !nazevPrikazu.equals("inventar")
                && !nazevPrikazu.equals("uloz")
                && !nazevPrikazu.equals("prehraj");
        if (tahHry) {
            historie.add(radek.trim());
        }
        IPrikaz prikaz = platnePrikazy.vratPrikaz(nazevPrikazu);
        String odpoved = prikaz.provedPrikaz(parametry);
        if (tahHry) {
            pocetTahu++;
            if (vyhra || konec) {
                return odpoved;
            }
            if (pocetTahu >= POCET_TAHU_DO_PROHRY) {
                prohra = true;
                return odpoved + "\nVítr se mění v ničivou bouři.";
            }
            if (pocetTahu == POCET_TAHU_PRO_VAROVANI) {
                return odpoved + "\nVítr sílí, bouře je skoro tady.";
            }
        }
        return odpoved;
    }

    public List<String> getHistorie() {
        return new ArrayList<>(historie);
    }

    public List<String> obnovStav(List<String> prikazy) {
        Hra novaHra = new Hra();
        List<String> odpovedi = new ArrayList<>();
        for (String prikaz : prikazy) {
            String cistyPrikaz = prikaz.trim();
            if (cistyPrikaz.isEmpty()) {
                continue;
            }
            String nazevPrikazu = cistyPrikaz.split("\\s+")[0].toLowerCase(Locale.ROOT);
            if (!novaHra.platnePrikazy.jePlatnyPrikaz(nazevPrikazu)
                    || nazevPrikazu.equals("uloz") || nazevPrikazu.equals("prehraj")) {
                throw new IllegalArgumentException("Neplatný příkaz v uložené hře: " + cistyPrikaz);
            }
            if (novaHra.konecHry()) {
                throw new IllegalArgumentException("Uložená hra obsahuje příkazy po jejím konci.");
            }
            odpovedi.add(novaHra.zpracujPrikaz(cistyPrikaz));
        }

        herniPlan.obnovStavZ(novaHra.herniPlan);
        pocetTahu = novaHra.pocetTahu;
        konec = novaHra.konec;
        vyhra = novaHra.vyhra;
        prohra = novaHra.prohra;
        historie.clear();
        historie.addAll(novaHra.historie);
        return odpovedi;
    }

    @Override
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }
}
