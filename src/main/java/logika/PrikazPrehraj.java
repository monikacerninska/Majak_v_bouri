package logika;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PrikazPrehraj implements IPrikaz {

    private static final String NAZEV = "prehraj";
    private final Hra hra;

    public PrikazPrehraj(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Jaký soubor mám přehrát?";
        }

        String soubor = String.join(" ", parametry);
        List<String> prikazy;
        boolean souborHry = false;
        try {
            prikazy = new ArrayList<>();
            for (String radek : Files.readAllLines(Path.of(soubor), StandardCharsets.UTF_8)) {
                String prikaz = radek.trim();
                if (prikaz.equals(PrikazUloz.HLAVICKA_SOUBORU)) {
                    souborHry = true;
                } else if (!prikaz.isEmpty() && !prikaz.startsWith("#")) {
                    prikazy.add(prikaz);
                }
            }
        } catch (NoSuchFileException e) {
            return "Soubor " + soubor + " neexistuje.";
        } catch (InvalidPathException e) {
            return "Neplatná cesta k souboru " + soubor + ".";
        } catch (IOException e) {
            return "Chyba při čtení souboru " + soubor + ".";
        }

        if (prikazy.isEmpty() && !souborHry) {
            return "Soubor " + soubor + " je prázdný.";
        }
        List<String> odpovedi;
        try {
            odpovedi = hra.obnovStav(prikazy);
        } catch (IllegalArgumentException e) {
            return "Soubor " + soubor + " obsahuje neplatná data: " + e.getMessage();
        }
        String vystup = String.join("\n", odpovedi);
        if (!vystup.isEmpty()) {
            vystup += "\n";
        }
        return vystup + "Obnovena hra z " + soubor
                + ", počet příkazů: " + prikazy.size() + ".";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
