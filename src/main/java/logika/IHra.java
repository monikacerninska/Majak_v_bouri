package logika;

public interface IHra {
    boolean konecHry();

    String zpracujPrikaz(String prikaz);

    HerniPlan getHerniPlan();

    void setKonecHry(boolean konecHry);
}
