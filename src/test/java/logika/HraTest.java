package logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HraTest {

    private Hra hra;

    @BeforeEach
    void setUp() {
        hra = new Hra();
    }

    @Test
    void prazdnyPrikazVratiNapovedu() {
        String odpoved = hra.zpracujPrikaz("");
        assertTrue(odpoved.toLowerCase().contains("příkaz")
                || odpoved.toLowerCase().contains("napoveda"));
    }

    @Test
    void neznamyPrikazVratiChybu() {
        String odpoved = hra.zpracujPrikaz("asdfgh");
        assertTrue(odpoved.toLowerCase().contains("nerozumím")
                || odpoved.toLowerCase().contains("napoveda"));
    }

    @Test
    void prikazKonecUkonciHru() {
        assertFalse(hra.konecHry());
        String odpoved = hra.zpracujPrikaz("konec");
        assertTrue(hra.konecHry());
        assertTrue(odpoved.toLowerCase().contains("ukončena"));
    }

    @Test
    void prikazNapovedaObsahujePrikazy() {
        String odpoved = hra.zpracujPrikaz("napoveda");
        assertTrue(odpoved.toLowerCase().contains("jdi")
                || odpoved.toLowerCase().contains("napoveda")
                || odpoved.toLowerCase().contains("konec"));
    }

    @Test
    void poKonciNelzePokracovat() {
        hra.zpracujPrikaz("konec");
        String odpoved = hra.zpracujPrikaz("jdi majak");
        assertTrue(odpoved.toLowerCase().contains("ukončena"));
    }

    @Test
    void hraMaHerniPlan() {
        assertNotNull(hra.getHerniPlan());
        assertNotNull(hra.getHerniPlan().getAktualniProstor());
    }
}