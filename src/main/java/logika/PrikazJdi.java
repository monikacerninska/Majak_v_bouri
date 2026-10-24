package logika;

public class PrikazJdi implements IPrikaz {

    private static final String NAZEV = "jdi";
    private final HerniPlan plan;

    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít?";
        }
        String cil = spojSlova(parametry);
        Prostor aktualni = plan.getAktualniProstor();
        Prostor soused = aktualni.vratSousedniProstor(cil);
        if (soused == null) {
            return "Tam se odsud jít nedá.";
        }
        String cesta = Prostor.normalizuj(aktualni.getNazev());
        String misto = Prostor.normalizuj(soused.getNazev());
        if ((cesta.equals("les") && misto.equals("pobrezni stezka")
                || cesta.equals("pobrezni stezka") && misto.equals("les"))
                && !plan.isStromOdstranen()) {
            return "Cestu blokuje spadlý strom. Potřebuješ sekeru.";
        }
        if ((cesta.equals("pobrezni stezka") && misto.equals("majak")
                || cesta.equals("majak") && misto.equals("pobrezni stezka"))
                && !plan.isDvereOdemcene()) {
            return "Dveře majáku jsou zamčené. Potřebuješ klíč.";
        }
        if (misto.equals("lucerna") && !plan.isDvereOdemcene()) {
            return "Do lucerny se zatím nedostaneš.";
        }
        plan.setAktualniProstor(soused);
        return soused.dlouhyPopis();
    }

    private String spojSlova(String[] slova) {
        StringBuilder text = new StringBuilder();
        for (String slovo : slova) {
            if (text.length() > 0) {
                text.append(" ");
            }
            text.append(slovo);
        }
        return text.toString();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
