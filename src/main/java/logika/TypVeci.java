package logika;

public enum TypVeci {
    JIDLO("jídlo", true),
    NASTROJ("nástroj", true),
    ROSTLINA("rostlina", true);

    private final String ceskyNazev;
    private final boolean prenositelna;

    private TypVeci(String ceskyNazev, boolean prenositelna) {
        this.ceskyNazev = ceskyNazev;
        this.prenositelna = prenositelna;
    }

    public String getCeskyNazev() {
        return this.ceskyNazev;}
    public boolean isPrenositelna() {
        return this.prenositelna;}

}
