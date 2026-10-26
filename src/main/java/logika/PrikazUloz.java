package logika;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PrikazUloz implements IPrikaz {

    private static final String NAZEV = "uloz";
    static final String HLAVICKA_SOUBORU = "# ADVENTURA_HISTORY_V1";
    private final Hra hra;

    public PrikazUloz(Hra hra) {
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám hru uložit?";
        }
        String soubor = String.join(" ", parametry);
        List<String> historie = hra.getHistorie();
        List<String> obsahSouboru = new ArrayList<>();
        obsahSouboru.add(HLAVICKA_SOUBORU);
        obsahSouboru.addAll(historie);
        try {
            Files.write(Path.of(soubor), obsahSouboru, StandardCharsets.UTF_8);
        } catch (InvalidPathException e) {
            return "Neplatná cesta k souboru " + soubor + ".";
        } catch (IOException e) {
            return "Hru se nepodařilo uložit do souboru " + soubor + ".";
        }
        return "Hra uložena do " + soubor + ", počet příkazů: " + historie.size() + ".";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
