package logika;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HraTest {

    @TempDir
    Path tempDir;

    @Test
    void obnovaZeSouboruVratiMistnostInventarAPostup() {
        Hra puvodni = new Hra();
        puvodni.zpracujPrikaz("jdi namesti");
        puvodni.zpracujPrikaz("jdi hospoda U Kotvy");
        puvodni.zpracujPrikaz("mluv Vojta");
        Path soubor = tempDir.resolve("ulozena hra.txt");

        assertTrue(puvodni.zpracujPrikaz("uloz " + soubor).startsWith("Hra uložena"));

        Hra obnovena = new Hra();
        String odpoved = obnovena.zpracujPrikaz("prehraj " + soubor);

        assertTrue(odpoved.contains("Obnovena hra"));
        assertEquals("Hospoda U Kotvy", obnovena.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(obnovena.getHerniPlan().getKosicek().obsahuje("klíč od majáku"));
        assertTrue(obnovena.getHerniPlan().isHlavniUkolPrijat());
        assertEquals(puvodni.getHistorie(), obnovena.getHistorie());
    }

    @Test
    void uloziAObnoviIhruPredPrvnimTahem() {
        Hra hra = new Hra();
        Path soubor = tempDir.resolve("nova-hra.txt");
        hra.zpracujPrikaz("uloz " + soubor);
        hra.zpracujPrikaz("jdi namesti");
        hra.zpracujPrikaz("jdi hospoda u kotvy");
        String odpoved = hra.zpracujPrikaz("prehraj " + soubor);

        assertTrue(odpoved.contains("počet příkazů: 0"));
        assertEquals("Přístav", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra.getHistorie().isEmpty());
    }

    @Test
    void prazdnySouborJeOznamenBezZmenyStavu() throws Exception {
        Hra hra = new Hra();
        hra.zpracujPrikaz("jdi namesti");
        Path soubor = tempDir.resolve("prazdny.txt");
        Files.createFile(soubor);

        String odpoved = hra.zpracujPrikaz("prehraj " + soubor);

        assertTrue(odpoved.contains("je prázdný"));
        assertEquals("Náměstí", hra.getHerniPlan().getAktualniProstor().getNazev());
    }

    @Test
    void poskozenySouborNezmeniAktualniStavHry() throws Exception {
        Hra hra = new Hra();
        hra.zpracujPrikaz("jdi namesti");
        Path soubor = tempDir.resolve("poskozena-hra.txt");
        Files.writeString(soubor, "jdi les\nneexistujici-prikaz\n");

        String odpoved = hra.zpracujPrikaz("prehraj " + soubor);

        assertTrue(odpoved.contains("neplatná data"));
        assertEquals("Náměstí", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(1, hra.getHistorie().size());
    }

    @Test
    void bezpecneOsetriPrazdneNeznameANeuplnePrikazy() {
        Hra hra = new Hra();

        assertEquals("Napiš nějaký příkaz.", hra.zpracujPrikaz("  "));
        assertEquals("Tento příkaz neznám.", hra.zpracujPrikaz("neznámý"));
        assertEquals("Co mám prozkoumat?", hra.zpracujPrikaz("prozkoumej"));
        assertTrue(hra.zpracujPrikaz("prozkoumej kámen").contains("tu není"));
    }

    @Test
    void filtrujePredmetyPodleTypuPomociPrikazuTyp() {
        Hra hra = new Hra();

        assertEquals("Věci typu jídlo tady: ryba.", hra.zpracujPrikaz("typ jidlo"));
        assertTrue(hra.zpracujPrikaz("typ rostlina").contains("Žádné věci typu rostlina"));
        assertFalse(hra.zpracujPrikaz("typ jidlo").contains("sekera"));
    }
}
