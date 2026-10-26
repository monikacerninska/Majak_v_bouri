package logika;

public class PrikazDej implements IPrikaz {

    private static final String NAZEV = "dej";
    private final HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length < 2) {
            return "Napiš, co chceš dát a komu.";
        }

        String vec = Prostor.normalizuj(parametry[0]);
        String komu = Prostor.normalizuj(parametry[1]);
        String misto = Prostor.normalizuj(plan.getAktualniProstor().getNazev());

        if (vec.equals("ryba") && komu.equals("marta") && misto.equals("obchod")) {
            if (!plan.getKosicek().obsahuje("ryba")) {
                return "Rybu u sebe nemáš.";
            }
            try {
                plan.getKosicek().vyndej("ryba");
                plan.getKosicek().vloz(new Vec("petrolej", TypVeci.NASTROJ));
            } catch (VecNeniVKosickuException e) {
                return e.getMessage();
            } catch (PlnyKosicekException e) {
                return "Košíček je plný. Uvolni místo a zkus výměnu znovu.";
            }
            return "Marta si vzala rybu a dala ti petrolej.";
        }

        if (vec.equals("kostival") && komu.equals("bara") && misto.equals("chata korenarky")) {
            if (!plan.isVedlejsiUkolPrijat()) {
                return "Nejdřív si promluv s Bárou.";
            }
            if (!plan.getKosicek().obsahuje("kostival")) {
                return "Kostival u sebe nemáš.";
            }
            try {
                plan.getKosicek().vyndej("kostival");
                plan.getKosicek().vloz(new Vec("mast", TypVeci.JIDLO));
            } catch (VecNeniVKosickuException e) {
                return e.getMessage();
            } catch (PlnyKosicekException e) {
                return "Košíček je plný. Uvolni místo a zkus výměnu znovu.";
            }
            return "Bára zpracovala kostival a dala ti mast pro Vojtu.";
        }

        if (vec.equals("mast") && komu.equals("vojta") && misto.equals("hospoda u kotvy")) {
            if (!plan.getKosicek().obsahuje("mast")) {
                return "Mast u sebe nemáš.";
            }
            try {
                plan.getKosicek().vyndej("mast");
            } catch (VecNeniVKosickuException e) {
                return e.getMessage();
            }
            plan.setMastPredana(true);
            return "Vojta si namazal zraněnou nohu. „Děkuju, teď už to zvládnu.“";
        }

        return "Tuhle věc tady tomu člověku dát nemůžeš.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
