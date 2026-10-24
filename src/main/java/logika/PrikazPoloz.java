package logika;

public class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "poloz";
    private final HerniPlan plan;

    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám položit?";
        }
        String nazevVeci = parametry[0];
        try {
            Vec vec = plan.getKosicek().vyndej(nazevVeci);
            plan.getAktualniProstor().vlozVec(vec);
            return "Položil jsi předmět „" + vec.getNazev() + "“.";
        } catch (VecNeniVKosickuException e) {
            return e.getMessage();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}

