package logika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kosicek {
    private static final int KAPACITA = 7;
    private final List<Vec> veci = new ArrayList();

    public Kosicek() {
    }

    public void vloz(Vec vec) throws PlnyKosicekException {
        if (vec == null) {
            throw new IllegalArgumentException("Do košíčku nelze vložit prázdný předmet.");
        } else if(this.veci.size() >= 7) {
            throw new PlnyKosicekException();
        } else {
            this.veci.add(vec);
        }
    }

    public Vec vyndej(String nazevVeci) throws VecNeniVKosickuException {
        for(int i = 0; i < this.veci.size(); ++i) {
            Vec vec = (Vec)this.veci.get(i);
            if (this.shodujeSe(vec, nazevVeci)) {
                this.veci.remove(i);
                return vec;
            }
        }

        throw new VecNeniVKosickuException(nazevVeci);
    }

    public boolean obsahuje(String nazevVeci) {
        for(Vec vec : this.veci) {
            if (this.shodujeSe(vec, nazevVeci)) {
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
        return Collections.unmodifiableList(new ArrayList(this.veci));
    }

    void nastavVeci(List<Vec> noveVeci) {
        if (noveVeci.size() > 7) {
            throw new IllegalArgumentException("Obsah přesahuje kapacitu košíčku.");
        } else {
            this.veci.clear();
            this.veci.addAll(noveVeci);
        }
    }
    public int getKapacita() {
        return 7;
    }
}
