package logika;

public interface IHra {

    String vratUvitani();

    String vratEpilog();

    boolean konecHry();

    String zpracujPrikaz(String radek);

    HerniPlan getHerniPlan();
}
