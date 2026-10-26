package logika;

public class PrikazProzkoumej implements IPrikaz {

    private static final String NAZEV = "prozkoumej";
    private final HerniPlan plan;

    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat?";
        }

        String nazevVeci = parametry[0];
        Vec nalezena = null;
        for (Vec vec : plan.getAktualniProstor().getVeci()) {
            if (Prostor.normalizuj(vec.getNazev()).equals(Prostor.normalizuj(nazevVeci))) {
                nalezena = vec;
                break;
            }
        }
        if (nalezena == null) {
            return nazevVeci + " tu není.";
        }

        String prenaseni = nalezena.jePrenositelna() ? "dá se přenášet" : "nedá se přenášet";
        return nazevVeci + " je " + nalezena.getTyp().getCeskyNazev() + ", " + prenaseni + ".";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
