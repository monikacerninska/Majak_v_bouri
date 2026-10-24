package logika;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Prostor {
    private final String nazev;
    private final String popis;
    private final Set<Prostor> vychody = new HashSet<>();

    public Prostor(String nazev, String popis) {
        this.nazev = Objects.requireNonNull(nazev, "nazev nesmí být null");
        this.popis = Objects.requireNonNull(popis, "popis nesmí být null");
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public Set<Prostor> getVychody() {
        return Collections.unmodifiableSet(vychody);
    }

    public void setVychod(Prostor prostor) {
        vychody.add(Objects.requireNonNull(prostor, "prostor nesmí být null"));
    }

    public Prostor getVychod(String nazevVychodu) {
        if (nazevVychodu == null) {
            return null;
        }
        return vychody.stream()
                .filter(prostor -> prostor.nazev.equals(nazevVychodu))
                .findFirst()
                .orElse(null);
    }

    public String dlouhyPopis() {
        String seznamVychodu = vychody.stream()
                .map(Prostor::getNazev)
                .sorted()
                .collect(Collectors.joining(", "));
        return "Jsi v prostoru " + nazev + ". " + popis + "\nVýchody: " + seznamVychodu;
    }

    @Override
    public boolean equals(Object objekt) {
        if (this == objekt) {
            return true;
        }
        if (!(objekt instanceof Prostor prostor)) {
            return false;
        }
        return nazev.equals(prostor.nazev);
    }

    @Override
    public int hashCode() {
        return nazev.hashCode();
    }
}
