package logika;

public enum TypVeci {
    JIDLO("jídlo", true),
    NASTROJ("nástroj", true),
    ROSTLINA("rostlina", true);

    private final String ceskyNazev;
    private final boolean prenositelna;

    TypVeci(String ceskyNazev, boolean prenositelna) {
        this.ceskyNazev = ceskyNazev;
        this.prenositelna = prenositelna;
    }

    public String getCeskyNazev() {
        return ceskyNazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }
}
