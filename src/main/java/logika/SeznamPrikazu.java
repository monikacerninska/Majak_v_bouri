package logika;

import java.util.LinkedHashMap;
import java.util.Map;

public class SeznamPrikazu {

    private final Map<String, IPrikaz> prikazy = new LinkedHashMap<>();

    public void vlozPrikaz(IPrikaz prikaz) {
        prikazy.put(prikaz.getNazev(), prikaz);
    }

    public boolean jePlatnyPrikaz(String nazev) {
        return prikazy.containsKey(nazev);
    }

    public IPrikaz vratPrikaz(String nazev) {
        return prikazy.get(nazev);
    }

    public String vratNazvyPrikazu() {
        StringBuilder sb = new StringBuilder();
        for (String nazev : prikazy.keySet()) {
            sb.append(nazev).append(" ");
        }
        return sb.toString().trim();
    }
}
