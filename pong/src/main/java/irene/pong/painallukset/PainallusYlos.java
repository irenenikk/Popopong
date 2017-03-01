package irene.pong.painallukset;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Luokka sitoo napin painalluksen mailan liikkeeseen.
 */
public class PainallusYlos extends AbstractAction {

    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    /**
     * Luokka saa konstruktorissaan komponenttien hallinnoijan, sekä painetun näppäimen kirjaimen.
     * 
     * @param kh pelin komponenttien hallintaluokka
     * @param k painetun näppäimen kirjain
     * 
     * @see logiikka.KomponenttiHallinta
     */
    public PainallusYlos(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Painoit ylös");
        if (kirjain.equals("W")) {
            kontrolleri.getMaila1().setKiihdytaYlos(true);
        }

        if (kirjain.equals("UP")) {
            kontrolleri.getMaila2().setKiihdytaYlos(true);
        }
    }
}
