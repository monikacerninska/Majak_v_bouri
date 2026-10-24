package logika;

public class Vec {
    private final String nazev;
    private final TypVeci typ;

    public Vec(String nazev, TypVeci typ) {
        this.nazev = nazev;
        this.typ = typ;
    }

    public String getNazev() {
        return this.nazev;
    }

    public TypVeci getTyp() {
        return this.typ;
    }

    public boolean jePrenositelna() {
        return this.typ.isPrenositelna();
    }
}
