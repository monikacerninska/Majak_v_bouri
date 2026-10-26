package logika;

import java.util.List;
import java.util.stream.Collectors;

public class PrikazTyp implements IPrikaz {

    private static final String NAZEV = "typ";
    private final HerniPlan plan;

    public PrikazTyp(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Jaký typ věci mě zajímá?";
        }
        TypVeci typ = null;
        for (TypVeci druh : TypVeci.values()) {
            if (Prostor.normalizuj(druh.name()).equals(Prostor.normalizuj(parametry[0]))
                    || Prostor.normalizuj(druh.getCeskyNazev())
                    .equals(Prostor.normalizuj(parametry[0]))) {
                typ = druh;
                break;
            }
        }
        if (typ == null) {
            return "Neznámý typ věci: " + parametry[0] + ".";
        }

        TypVeci hledanyTyp = typ;
        List<String> nalezene = plan.getAktualniProstor().getVeci().stream()
                .filter(vec -> vec.getTyp() == hledanyTyp)
                .map(Vec::getNazev)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        if (nalezene.isEmpty()) {
            return "Žádné věci typu " + typ.getCeskyNazev() + " tady nejsou.";
        }
        return "Věci typu " + typ.getCeskyNazev() + " tady: " + String.join(", ", nalezene) + ".";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
