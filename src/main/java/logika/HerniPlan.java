package logika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HerniPlan {

    private Prostor aktualniProstor;
    private final Kosicek kosicek = new Kosicek();
    private final Map<String, Prostor> prostory = new LinkedHashMap<>();
    private boolean hlavniUkolPrijat;
    private boolean stromOdstranen;
    private boolean dvereOdemcene;
    private boolean vedlejsiUkolPrijat;
    private boolean lucernaRozsvicena;
    private boolean sekeraPredana;
    private boolean petrolejNalita;
    private boolean sirkyPouzity;
    private boolean mastPredana;

    public HerniPlan() {
        zalozProstoryHry();
    }

    private void zalozProstoryHry() {
        Prostor pristav = new Prostor("Přístav", "Přístav, kde rybář Karel spravuje loď a v bedně leží ryba");
        Prostor namesti = new Prostor("Náměstí", "Náměstí, nad kterým se stahují bouřková mračna");
        Prostor hospoda = new Prostor("Hospoda U Kotvy", "Hospoda U Kotvy, kde u krbu sedí zraněný Vojta");
        Prostor obchod = new Prostor("Obchod", "Obchod, kde Marta prodává zásoby na cestu");
        Prostor les = new Prostor("Les", "Les, ve kterém roste kostival");
        Prostor chata = new Prostor("Chata kořenářky", "Chata kořenářky Báry");
        Prostor stezka = new Prostor("Pobřežní stezka", "Pobřežní stezka vedoucí k majáku");
        Prostor majak = new Prostor("Maják", "Přízemí majáku, kde čeká schodiště do lucerny");
        Prostor lucerna = new Prostor("Lucerna", "Lucerna na vrcholu majáku");

        for (Prostor prostor : List.of(pristav, namesti, hospoda, obchod, les, chata, stezka, majak, lucerna)) {
            prostory.put(Prostor.normalizuj(prostor.getNazev()), prostor);
        }

        pristav.setVychod(namesti);
        namesti.setVychod(pristav);
        namesti.setVychod(hospoda);
        namesti.setVychod(obchod);
        namesti.setVychod(les);
        hospoda.setVychod(namesti);
        obchod.setVychod(namesti);
        les.setVychod(namesti);
        les.setVychod(chata);
        chata.setVychod(les);
        les.setVychod(stezka);
        stezka.setVychod(les);
        stezka.setVychod(majak);
        majak.setVychod(stezka);
        majak.setVychod(lucerna);
        lucerna.setVychod(majak);

        pristav.vlozVec(new Vec("ryba", TypVeci.JIDLO));
        hospoda.vlozVec(new Vec("sirky", TypVeci.NASTROJ));
        les.vlozVec(new Vec("kostival", TypVeci.ROSTLINA));

        aktualniProstor = pristav;
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public void setAktualniProstor(Prostor prostor) {
        if (prostor == null) {
            throw new IllegalArgumentException("Aktuální prostor nemůže být prázdný.");
        }
        Prostor znamyProstor = prostory.get(Prostor.normalizuj(prostor.getNazev()));
        if (znamyProstor == null) {
            throw new IllegalArgumentException("Tento prostor do herního plánu nepatří.");
        }
        aktualniProstor = znamyProstor;
    }

    public List<Prostor> getProstory() {
        return Collections.unmodifiableList(new ArrayList<>(prostory.values()));
    }

    void obnovStavZ(HerniPlan zdroj) {
        for (Prostor prostor : prostory.values()) {
            Prostor zdrojovyProstor = zdroj.prostory.get(Prostor.normalizuj(prostor.getNazev()));
            prostor.nastavVeci(zdrojovyProstor.getVeci());
        }
        kosicek.nastavVeci(zdroj.kosicek.getVeci());
        setAktualniProstor(zdroj.aktualniProstor);
        hlavniUkolPrijat = zdroj.hlavniUkolPrijat;
        stromOdstranen = zdroj.stromOdstranen;
        dvereOdemcene = zdroj.dvereOdemcene;
        vedlejsiUkolPrijat = zdroj.vedlejsiUkolPrijat;
        lucernaRozsvicena = zdroj.lucernaRozsvicena;
        sekeraPredana = zdroj.sekeraPredana;
        petrolejNalita = zdroj.petrolejNalita;
        sirkyPouzity = zdroj.sirkyPouzity;
        mastPredana = zdroj.mastPredana;
    }

    public Kosicek getKosicek() {
        return kosicek;
    }

    public boolean isHlavniUkolPrijat() {
        return hlavniUkolPrijat;
    }

    public void setHlavniUkolPrijat(boolean prijat) {
        hlavniUkolPrijat = prijat;
    }

    public boolean isStromOdstranen() {
        return stromOdstranen;
    }

    public void setStromOdstranen(boolean odstran) {
        stromOdstranen = odstran;
    }

    public boolean isDvereOdemcene() {
        return dvereOdemcene;
    }

    public void setDvereOdemcene(boolean odemcene) {
        dvereOdemcene = odemcene;
    }

    public boolean isVedlejsiUkolPrijat() {
        return vedlejsiUkolPrijat;
    }

    public void setVedlejsiUkolPrijat(boolean prijat) {
        vedlejsiUkolPrijat = prijat;
    }

    public boolean isSekeraPredana() {
        return sekeraPredana;
    }

    public void setSekeraPredana(boolean predana) {
        sekeraPredana = predana;
    }

    public boolean isPetrolejNalita() {
        return petrolejNalita;
    }

    public void setPetrolejNalita(boolean nalita) {
        petrolejNalita = nalita;
    }

    public boolean isSirkyPouzity() {
        return sirkyPouzity;
    }

    public void setSirkyPouzity(boolean pouzity) {
        sirkyPouzity = pouzity;
    }

    public boolean isMastPredana() {
        return mastPredana;
    }

    public void setMastPredana(boolean predana) {
        mastPredana = predana;
    }

    public boolean isLucernaRozsvicena() {
        return lucernaRozsvicena;
    }

    public void setLucernaRozsvicena(boolean rozsvicena) {
        lucernaRozsvicena = rozsvicena;
    }
}
