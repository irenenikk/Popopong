package irene.pong.painallukset;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Luokka sitoo napin painalluksen mailan liikkeeseen.
 */
public class PainallusAlas extends AbstractAction {

    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    /**
     * Luokka saa konstruktorissaan komponenttien hallinnoijan, sekä näppäimen kirjaimen, jota on painettu.
     * 
     * @param kh pelin komponenttien hallintaluokka
     * @param k painetun näppäimen kirjain
     */
    public PainallusAlas(KomponenttiHallinta kh, String k) {

        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (kirjain.equals("S")) {
            kontrolleri.getMaila1().setKiihdytaAlas(true);
        }

        if (kirjain.equals("DOWN")) {
            kontrolleri.getMaila2().setKiihdytaAlas(true);
        }
    }
}
