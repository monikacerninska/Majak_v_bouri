package uiText;

import logika.IHra;
import java.util.Scanner;

/**
 * Textové uživatelské rozhraní – čte příkazy a vypisuje odpovědi hry.
 */
public class TextoveRozhrani {

    private final IHra hra;
    private final Scanner scanner;

    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Spustí herní smyčku, dokud hra neskončí.
     */
    public void hraj() {
        System.out.println("Vítej v přístavní adventuře Maják v bouři!");
        System.out.println("Tvůj úkol: rozsvítit světlo pro přístav.");
        System.out.println("Pro nápovědu napiš: napoveda");
        System.out.println();
        System.out.println(hra.getHerniPlan().getAktualniProstor().dlouhyPopis());

        while (!hra.konecHry()) {
            System.out.print("> ");
            String radek = scanner.nextLine();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println("Díky za hru!");
    }
}