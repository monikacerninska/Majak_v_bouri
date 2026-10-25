package logika;

/**
 * Příkaz „napoveda“ – vypíše cíle a dostupné příkazy.
 */
public class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private final SeznamPrikazu platnePrikazy;

    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvůj hlavní úkol: rozsvítit světlo pro přístav.\n"
                + "Můžeš také pomoci Vojtovi s vedlejším úkolem.\n"
                + "\nDostupné příkazy: "
                + platnePrikazy.vratNazvyPrikazu();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}