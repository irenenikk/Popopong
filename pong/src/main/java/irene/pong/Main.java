package irene.pong;

import irene.pong.grafiikka.Ikkuna;
import irene.pong.logiikka.Pong;

public class Main {
    
    public static void main(String[] args) {
        Pong peli = new Pong();
        peli.aloita();
    }
}
