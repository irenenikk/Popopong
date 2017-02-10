/**
 * Luokka lisää pelaajille pisteitä käyttämällä niiden omaa metodia, sekä tarkistaa, onko pelillä voittaja.
 * 
 * @see komponentit.Pelaaja#lisaaPiste}
 */

package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Pelaaja;

public class Tilasto {

    private Kentta kentta;
    private final int voittoPisteet = 15;
    
    public Tilasto(Kentta k) {
        kentta = k;
    }
    
    public void lisaaPiste(Pelaaja p) {
        p.lisaaPiste();
    }
    

    public Pelaaja voittaja() {
        if (kentta.getPelaaja1().getPisteet() >= voittoPisteet) {
            return kentta.getPelaaja1();
        } else if (kentta.getPelaaja2().getPisteet() >= voittoPisteet) {
            return kentta.getPelaaja2();
        } else {
            return null;
        }
    }
}
