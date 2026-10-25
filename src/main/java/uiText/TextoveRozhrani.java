package uiText;

import java.util.Scanner;
import logika.IHra;

public class TextoveRozhrani {

    private final IHra hra;

    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    public void hraj() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(hra.vratUvitani());
        while (!hra.konecHry()) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String radek = scanner.nextLine();
            System.out.println(hra.zpracujPrikaz(radek));
        }
        System.out.println(hra.vratEpilog());
    }
}
