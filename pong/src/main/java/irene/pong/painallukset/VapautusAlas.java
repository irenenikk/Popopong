/**
 * Luokka sitoo napin vapauttamisen mailan liikkeeseen.
 */

package irene.pong.painallukset;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class VapautusAlas extends AbstractAction {
    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    
    public VapautusAlas(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (kirjain.equals("S")) {
            kontrolleri.getMaila1().setKiihdytaAlas(false);
        }
        
        if (kirjain.equals("DOWN")) {
            kontrolleri.getMaila2().setKiihdytaAlas(false);            
        }        
    }
}
