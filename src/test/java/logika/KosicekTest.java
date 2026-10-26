package logika;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KosicekTest {

    @Test
    void vloziVybereAPorovnaNazevBezDiakritiky() throws Exception {
        Kosicek kosicek = new Kosicek();
        Vec klic = new Vec("klíč od majáku", TypVeci.NASTROJ);

        kosicek.vloz(klic);

        assertTrue(kosicek.obsahuje("klic"));
        assertEquals(klic, kosicek.vyndej("klic"));
        assertFalse(kosicek.obsahuje("klíč"));
    }

    @Test
    void dodrzujeKapacituAObsahujePouzeSedmVeci() throws Exception {
        Kosicek kosicek = new Kosicek();
        for (int i = 0; i < kosicek.getKapacita(); i++) {
            kosicek.vloz(new Vec("věc " + i, TypVeci.NASTROJ));
        }

        assertThrows(PlnyKosicekException.class,
                () -> kosicek.vloz(new Vec("navíc", TypVeci.NASTROJ)));
        assertEquals(7, kosicek.getVeci().size());
    }

    @Test
    void inventarNelzeUpravitPresGetter() throws Exception {
        Kosicek kosicek = new Kosicek();
        kosicek.vloz(new Vec("ryba", TypVeci.JIDLO));

        assertThrows(UnsupportedOperationException.class, () -> kosicek.getVeci().clear());
        assertTrue(kosicek.obsahuje("ryba"));
    }

    @Test
    void odmitneVlozitNull() {
        Kosicek kosicek = new Kosicek();

        assertThrows(IllegalArgumentException.class, () -> kosicek.vloz(null));
    }
}
