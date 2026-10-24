package logika;

public class PlnyKosicekException extends Exception {
    public PlnyKosicekException(String message) {
        super("Košíček je plný.");
    }
}
