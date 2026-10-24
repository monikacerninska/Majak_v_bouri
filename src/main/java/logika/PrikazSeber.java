package logika;

public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private final HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat?";
        }
        String nazevVeci = parametry[0];
        Prostor prostor = plan.getAktualniProstor();

        Vec vec = prostor.vyberVec(nazevVeci);
        if (vec == null) {
            return nazevVeci + " tu není.";
        }
        if (!vec.jePrenositelna()) {
            prostor.vlozVec(vec);
            return nazevVeci + " se nedá sebrat.";
        }
        try {
            plan.getKosicek().vloz(vec);
        } catch (PlnyKosicekException e) {
            prostor.vlozVec(vec);
            return e.getMessage();
        }
        return "Sebral jsi předmět „" + vec.getNazev() + "“.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
