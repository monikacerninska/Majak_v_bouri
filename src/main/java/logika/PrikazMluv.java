package logika;

public class PrikazMluv implements IPrikaz {

    private static final String NAZEV = "mluv";
    private final HerniPlan plan;

    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám mluvit?";
        }

        String postava = Prostor.normalizuj(spojSlova(parametry));
        String misto = Prostor.normalizuj(plan.getAktualniProstor().getNazev());

        if ((postava.equals("vojta") || postava.equals("strazce vojta"))
                && misto.equals("hospoda u kotvy")) {
            if (!plan.isHlavniUkolPrijat()) {
                if (!pridejDoInventare(new Vec("klíč od majáku", TypVeci.NASTROJ))) {
                    return "Košíček je plný. Uvolni místo a promluv s Vojtou znovu.";
                }
                plan.setHlavniUkolPrijat(true);
                return "Vojta si drží zraněnou nohu. „Přijmi úkol Světlo pro přístav. "
                        + "Rozsviť maják, než bouře dorazí. Tady je klíč od majáku.“";
            }
            if (!plan.isMastPredana()) {
                return "Vojta: „Až najdeš mast, přines mi ji. Noha mě pořád bolí.“";
            }
            return "Vojta: „Díky za mast. Teď už jen rozsviť maják.“";
        }

        if ((postava.equals("karel") || postava.equals("rybar karel"))
                && misto.equals("pristav")) {
            if (!plan.isHlavniUkolPrijat()) {
                return "Karel: „Nejdřív si promluv s Vojtou v hospodě. Potom ti půjčím sekeru.“";
            }
            if (!plan.isSekeraPredana()) {
                if (!pridejDoInventare(new Vec("sekera", TypVeci.NASTROJ))) {
                    return "Košíček je plný. Uvolni místo a promluv s Karlem znovu.";
                }
                plan.setSekeraPredana(true);
                return "Karel: „Vojta tě posílá? Tady máš sekeru. Budeš ji potřebovat na spadlý strom.“";
            }
            return "Karel: „Sekeru už máš. Dej pozor na bouři.“";
        }

        if ((postava.equals("marta") || postava.equals("obchodnice marta"))
                && misto.equals("obchod")) {
            return "Marta: „Dej mi rybu a vyměním ji za petrolej.“";
        }

        if ((postava.equals("bara") || postava.equals("korenarka bara"))
                && misto.equals("chata korenarky")) {
            if (!plan.isVedlejsiUkolPrijat()) {
                plan.setVedlejsiUkolPrijat(true);
                return "Bára: „Pomůžeš mi s úkolem Mast pro Vojtu? Přines mi kostival a mast připravím.“";
            }
            if (!plan.getKosicek().obsahuje("kostival")
                    && !plan.getKosicek().obsahuje("mast")
                    && !plan.isMastPredana()) {
                return "Bára: „Najdi v lese kostival a přines mi ho. Udělám z něj mast pro Vojtu.“";
            }
            return "Bára: „Dej mi kostival a připravím mast.“";
        }

        return "Tady nikdo takový není.";
    }

    private String spojSlova(String[] slova) {
        StringBuilder text = new StringBuilder();
        for (String slovo : slova) {
            if (text.length() > 0) {
                text.append(" ");
            }
            text.append(slovo);
        }
        return text.toString();
    }

    private boolean pridejDoInventare(Vec vec) {
        try {
            plan.getKosicek().vloz(vec);
            return true;
        } catch (PlnyKosicekException e) {
            return false;
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}