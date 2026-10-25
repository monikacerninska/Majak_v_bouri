package logika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.text.Normalizer;
import java.util.Locale;

public class Prostor {

    private final String nazev;
    private final String popis;
    private final Set<Prostor> vychody = new LinkedHashSet<>();
    private final List<Vec> veci = new ArrayList<>();

    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }

    public String getNazev() {
        return nazev;
    }

    public void setVychod(Prostor soused) {
        vychody.add(soused);
    }

    public Prostor vratSousedniProstor(String nazevSouseda) {
        for (Prostor soused : vychody) {
            if (normalizuj(soused.getNazev()).equals(normalizuj(nazevSouseda))) {
                return soused;
            }
        }
        return null;
    }

    public void vlozVec(Vec vec) {
        veci.add(vec);
    }

    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : veci) {
            if (normalizuj(vec.getNazev()).equals(normalizuj(nazevVeci))) {
                return true;
            }
        }
        return false;
    }

    public Vec vyberVec(String nazevVeci) {
        for (int i = 0; i < veci.size(); i++) {
            Vec vec = veci.get(i);
            if (normalizuj(vec.getNazev()).equals(normalizuj(nazevVeci))) {
                veci.remove(i);
                return vec;
            }
        }
        return null;
    }

    public List<Vec> getVeci() {
        return Collections.unmodifiableList(new ArrayList<>(veci));
    }

    void nastavVeci(List<Vec> noveVeci) {
        veci.clear();
        veci.addAll(noveVeci);
    }

    public String dlouhyPopis() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jsi v prostoru ").append(popis).append(".\n");
        sb.append("Východy:");
        boolean prvni = true;
        for (Prostor soused : vychody) {
            sb.append(prvni ? " " : ", ").append(soused.getNazev());
            prvni = false;
        }
        sb.append("\nVěci:");
        prvni = true;
        for (Vec vec : veci) {
            sb.append(prvni ? " " : ", ").append(vec.getNazev());
            prvni = false;
        }
        return sb.toString();
    }

    public static String normalizuj(String text) {
        String bezDiakritiky = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return bezDiakritiky.toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prostor)) {
            return false;
        }
        Prostor druhy = (Prostor) o;
        return Objects.equals(nazev, druhy.nazev);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nazev);
    }
}
