package irene.pong;

import irene.pong.kayttoliittyma.Kayttoliittyma;
import irene.pong.logiikka.Pong;
import javax.swing.SwingUtilities;

public class Main {
    
    /**
     * Peli käynnistetään käyttöliittymän kautta.
     * 
     * @param args Ohjelmalle käynnistettäessa annetut parametrit.
     * 
     * @see Pong
     * @see Kayttoliittyma
     */
    public static void main(String[] args) {
        Pong peli = new Pong();
        Kayttoliittyma kali = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kali);
    }
}
