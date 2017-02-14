/**
 * Luokka sitoo napin painalluksen mailan liikkeeseen.
 */

package irene.pong.painallukset;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class VapautusYlos extends AbstractAction {
    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    
    public VapautusYlos(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Vapautit napin ylös");
        if (kirjain.equals("W")) {
            kontrolleri.getMaila1().setKiihdytaYlos(false);
        }
        
        if (kirjain.equals("UP")) {
            kontrolleri.getMaila2().setKiihdytaYlos(false);            
        }
    }
    
}
