package logika;

public class VecNeniVKosickuException extends Exception {

    public VecNeniVKosickuException(String nazevVeci) {
        super(nazevVeci + " v košíčku nemáš.");
    }
}
