package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HerniPlan {
    private final Map<String, Prostor> mapaProstoru = new LinkedHashMap<>();
    private Prostor aktualniProstor;

    public HerniPlan() {
        vytvorMapu();
    }

    private void vytvorMapu() {
        Prostor plaz = pridejProstor("plaz", "Kamienkova plaz je rozburena silnym vetrem.");
        Prostor molo = pridejProstor("molo", "Stare molo se houpe na vlnach. Na konci bliká svetlo majaku.");
        Prostor majak = pridejProstor("majak", "Vysoky majak stoji nad utesem. Jeho svetlo je zhasnute.");
        Prostor sklad = pridejProstor("sklad", "V male budove u mola jsou ulozene nastroje a zasoby.");

        plaz.setVychod(molo);
        molo.setVychod(plaz);
        molo.setVychod(majak);
        molo.setVychod(sklad);
        majak.setVychod(molo);
        sklad.setVychod(molo);

        aktualniProstor = plaz;
    }

    private Prostor pridejProstor(String nazev, String popis) {
        Prostor prostor = new Prostor(nazev, popis);
        mapaProstoru.put(nazev, prostor);
        return prostor;
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public void setAktualniProstor(Prostor prostor) {
        Objects.requireNonNull(prostor, "prostor nesmí být null");
        Prostor prostorMapy = mapaProstoru.get(prostor.getNazev());
        if (prostorMapy == null || prostorMapy != prostor) {
            throw new IllegalArgumentException("Prostor není součástí herního plánu.");
        }
        aktualniProstor = prostor;
    }

    public Prostor getProstor(String nazev) {
        return mapaProstoru.get(nazev);
    }

    public Collection<Prostor> getMapaProstoru() {
        return Collections.unmodifiableCollection(mapaProstoru.values());
    }
}
