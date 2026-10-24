package logika;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KosicekTest {
    KosicekTest() {
    }

    @Test
    void vloziVybereAPorovnaNazevBezDiakritiky() throws Exception {
        Kosicek kosicek = new Kosicek();
        Vec klic = new Vec("klíč od majáku", TypVeci.NASTROJ);
        kosicek.vloz(klic);
        Assertions.assertTrue(kosicek.obsahuje("klic"));
        Assertions.assertEquals(klic, kosicek.vyndej("klic"));
        Assertions.assertFalse(kosicek.obsahuje("klic"));
    }

    @Test
    void dodrzujeKapacituAObsahujePouzeSedmVeci() throws Exception {
        Kosicek kosicek = new Kosicek();

        for(int i = 0; i< kosicek.getKapacita(); ++i) {
            kosicek.vloz(new Vec("vec " + i, TypVeci.NASTROJ));
        }

        Assertions.assertThrows(PlnyKosicekException.class, () -> kosicek.vloz(new Vec("navic",TypVeci.NASTROJ)));
        Assertions.assertEquals(7, kosicek.getVeci().size());
    }

    @Test
    void inventarNelzeUpravitPresGetter() throws Exception {
        Kosicek kosicek = new Kosicek();
        kosicek.vloz(new Vec("ryba", TypVeci.JIDLO));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> kosicek.getVeci().clear());
    }

    @Test
    void odmitneVlozitNull() {
        Kosicek kosicek = new Kosicek();
        Assertions.assertThrows(IllegalArgumentException.class, () -> kosicek.vloz((Vec)null));
    }
}
