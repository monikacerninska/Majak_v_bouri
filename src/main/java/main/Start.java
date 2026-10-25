package main;

import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 * Spouštěcí třída hry.
 */
public class Start {

    public static void main(String[] args) {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();
    }
}