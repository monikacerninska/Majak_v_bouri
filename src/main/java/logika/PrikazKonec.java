package logika;

public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";
    private final Hra hra;

    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jsi zadal druhé slovo.";
        }
        hra.ukonciHru();
        return "Hra ukončena příkazem konec.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
