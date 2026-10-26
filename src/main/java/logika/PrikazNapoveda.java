package logika;

public class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private final SeznamPrikazu platnePrikazy;

    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return "Hlavní úkol: Světlo pro přístav. Získej klíč od Vojty, odstraň sekerou "
                + "spadlý strom, odemkni maják a v lucerně použij petrolej a sirky.\n"
                + "Vedlejší úkol: přines kostival Báře a mast pak dej Vojtovi.\n"
                + "Po 30 tazích přijde varování, po 40 tazích bouře zničí lodě.\n"
                + "Příkazy a jejich použití: " + vypisPrikazy()
                + "\nTypy věcí:" + vypisTypy();
    }

    private String vypisPrikazy() {
        StringBuilder sb = new StringBuilder();
        for (String nazev : platnePrikazy.vratNazvyPrikazu().split(", ")) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(switch (nazev) {
                case "jdi" -> "jdi [místo]";
                case "mluv" -> "mluv [osoba]";
                case "seber" -> "seber [věc]";
                case "poloz" -> "poloz [věc]";
                case "prozkoumej" -> "prozkoumej [věc]";
                case "typ" -> "typ [typ věci]";
                case "uloz" -> "uloz [soubor]";
                case "prehraj" -> "prehraj [soubor]";
                case "dej" -> "dej [věc] [osoba]";
                case "pouzij" -> "pouzij [věc] [cíl]";
                default -> nazev;
            });
        }
        return sb.toString();
    }

    private String vypisTypy() {
        StringBuilder sb = new StringBuilder();
        for (TypVeci typ : TypVeci.values()) {
            sb.append(sb.isEmpty() ? " " : ", ").append(typ.getCeskyNazev());
        }
        return sb.toString();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
