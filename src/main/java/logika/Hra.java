package logika;

import java.util.Locale;

public class Hra implements IHra {
    private final HerniPlan herniPlan;
    private boolean konecHry;

    public Hra() {
        herniPlan = new HerniPlan();
        konecHry = false;
    }

    @Override
    public boolean konecHry() {
        return konecHry;
    }

    @Override
    public String zpracujPrikaz(String prikaz) {
        if (konecHry) {
            return "Hra je již ukončena.";
        }
        if (prikaz == null || prikaz.isBlank()) {
            return "Zadej příkaz. Pro nápovědu napiš „napoveda“.";
        }

        String[] castiPrikazu = prikaz.trim().split("\\s+", 2);
        String sloveso = castiPrikazu[0].toLowerCase(Locale.ROOT);

        return switch (sloveso) {
            case "konec" -> ukonciHru();
            case "napoveda", "pomoc" -> napoveda();
            case "jdi" -> jdi(castiPrikazu.length == 2 ? castiPrikazu[1].trim() : "");
            default -> "Tomu nerozumím. Pro nápovědu napiš „napoveda“.";
        };
    }

    private String ukonciHru() {
        setKonecHry(true);
        return "Hra byla ukončena.";
    }

    private String napoveda() {
        return "Dostupné příkazy: jdi <prostor>, napoveda, konec.";
    }

    private String jdi(String nazevVychodu) {
        if (nazevVychodu.isBlank()) {
            return "Napiš název prostoru, kam chceš jít. "
                    + herniPlan.getAktualniProstor().dlouhyPopis();
        }

        Prostor cil = herniPlan.getAktualniProstor()
                .getVychod(nazevVychodu.toLowerCase(Locale.ROOT));
        if (cil == null) {
            return "Tím směrem se odtud nedá jít.\n"
                    + herniPlan.getAktualniProstor().dlouhyPopis();
        }

        herniPlan.setAktualniProstor(cil);
        return cil.dlouhyPopis();
    }

    @Override
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    @Override
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
}
