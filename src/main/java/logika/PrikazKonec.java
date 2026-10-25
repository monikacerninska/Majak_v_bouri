package logika;

/**
 * Příkaz „konec“ – ukončí hru.
 */
public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";
    private final Hra hra;

    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        hra.setKonecHry(true);
        return "Hra byla ukončena.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}