package logika;

import java.util.HashMap;
import java.util.Map;

public class PrikazInventar implements IPrikaz {

    private static final String NAZEV = "inventar";
    private final HerniPlan plan;

    public PrikazInventar(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Kosicek kosicek = plan.getKosicek();
        if (kosicek.getVeci().isEmpty()) {
            return "Košíček je prázdný.";
        }

        Map<TypVeci, Integer> pocty = new HashMap<>();
        for (Vec vec : kosicek.getVeci()) {
            TypVeci typ = vec.getTyp();
            if (pocty.containsKey(typ)) {
                pocty.put(typ, pocty.get(typ) + 1);
            } else {
                pocty.put(typ, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Košíček (").append(kosicek.getVeci().size())
                .append("/").append(kosicek.getKapacita()).append("):");
        for (TypVeci typ : TypVeci.values()) {
            if (pocty.containsKey(typ)) {
                sb.append("\n- ").append(typ.getCeskyNazev()).append(": ").append(pocty.get(typ));
            }
        }
        return sb.toString();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
