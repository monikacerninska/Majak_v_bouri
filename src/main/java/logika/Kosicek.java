package logika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kosicek {

    private static final int KAPACITA = 7;

    private final List<Vec> veci = new ArrayList<>();

    public void vloz(Vec vec) throws PlnyKosicekException {
        if (vec == null) {
            throw new IllegalArgumentException("Do košíčku nelze vložit prázdný předmět.");
        }
        if (veci.size() >= KAPACITA) {
            throw new PlnyKosicekException();
        }
        veci.add(vec);
    }

    public Vec vyndej(String nazevVeci) throws VecNeniVKosickuException {
        for (int i = 0; i < veci.size(); i++) {
            Vec vec = veci.get(i);
            if (shodujeSe(vec, nazevVeci)) {
                veci.remove(i);
                return vec;
            }
        }
        throw new VecNeniVKosickuException(nazevVeci);
    }

    public boolean obsahuje(String nazevVeci) {
        for (Vec vec : veci) {
            if (shodujeSe(vec, nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    private boolean shodujeSe(Vec vec, String nazevVeci) {
        String nazev = Prostor.normalizuj(vec.getNazev());
        String hledany = Prostor.normalizuj(nazevVeci);
        return nazev.equals(hledany) || nazev.startsWith(hledany + " ");
    }

    public List<Vec> getVeci() {
        return Collections.unmodifiableList(new ArrayList<>(veci));
    }

    void nastavVeci(List<Vec> noveVeci) {
        if (noveVeci.size() > KAPACITA) {
            throw new IllegalArgumentException("Obsah překračuje kapacitu košíčku.");
        }
        veci.clear();
        veci.addAll(noveVeci);
    }

    public int getKapacita() {
        return KAPACITA;
    }
}
