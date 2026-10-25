package logika;

public class PrikazPouzij implements IPrikaz {

    private static final String NAZEV = "pouzij";
    private final HerniPlan plan;
    private final Hra hra;

    public PrikazPouzij(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length < 2) {
            return "Napiš, co chceš použít a na co.";
        }

        String vec = Prostor.normalizuj(parametry[0]);
        String cil = Prostor.normalizuj(spojSlova(parametry, 1));
        String misto = Prostor.normalizuj(plan.getAktualniProstor().getNazev());

        if (vec.equals("sekera") && cil.equals("strom")
                && misto.equals("les")) {
            if (!plan.getKosicek().obsahuje("sekera")) {
                return "Sekeru u sebe nemáš.";
            }
            if (plan.isStromOdstranen()) {
                return "Spadlý strom už je odstraněný.";
            }
            plan.setStromOdstranen(true);
            return "Sekera rozsekala spadlý strom. Cesta k pobřežní stezce je volná.";
        }

        if ((vec.equals("klic") || vec.equals("klic od majaku"))
                && cil.equals("dvere") && misto.equals("pobrezni stezka")) {
            if (!plan.getKosicek().obsahuje("klíč od majáku")) {
                return "Klíč od majáku u sebe nemáš.";
            }
            if (plan.isDvereOdemcene()) {
                return "Dveře majáku už jsou odemčené.";
            }
            plan.setDvereOdemcene(true);
            return "Odemkl jsi dveře majáku.";
        }

        if (vec.equals("petrolej") && cil.equals("lucerna") && misto.equals("lucerna")) {
            if (!plan.getKosicek().obsahuje("petrolej")) {
                return "Petrolej u sebe nemáš.";
            }
            if (plan.isPetrolejNalita()) {
                return "Lucerna už je naplněná petrolejem.";
            }
            try {
                plan.getKosicek().vyndej("petrolej");
            } catch (VecNeniVKosickuException e) {
                return e.getMessage();
            }
            plan.setPetrolejNalita(true);
            if (plan.isSirkyPouzity()) {
                hra.vyhrat();
            }
            return "Nalil jsi petrolej do lucerny.";
        }

        if (vec.equals("sirky") && cil.equals("lucerna") && misto.equals("lucerna")) {
            if (!plan.getKosicek().obsahuje("sirky")) {
                return "Sirky u sebe nemáš.";
            }
            if (plan.isSirkyPouzity()) {
                return "Lucerna už hoří.";
            }
            if (!plan.isPetrolejNalita()) {
                return "Nejdřív musíš naplnit lucernu petrolejem.";
            }
            try {
                plan.getKosicek().vyndej("sirky");
            } catch (VecNeniVKosickuException e) {
                return e.getMessage();
            }
            plan.setSirkyPouzity(true);
            plan.setLucernaRozsvicena(true);
            hra.vyhrat();
            return "Škrtnul jsi sirkou. Lucerna se rozzářila nad přístavem!";
        }

        return "Tady tu věc použít nemůžeš.";
    }

    private String spojSlova(String[] slova, int zacatek) {
        StringBuilder text = new StringBuilder();
        for (int i = zacatek; i < slova.length; i++) {
            if (text.length() > 0) {
                text.append(" ");
            }
            text.append(slova[i]);
        }
        return text.toString();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
