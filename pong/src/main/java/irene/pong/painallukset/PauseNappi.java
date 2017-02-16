/**
 * Luokka sitoo p-näppäimen painalluksen pelin pysäyttämiseen.
 */

package irene.pong.painallukset;

import irene.pong.kayttoliittyma.Animoija;
import irene.pong.kayttoliittyma.Kayttoliittyma;
import irene.pong.logiikka.Pong;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PauseNappi extends AbstractAction {

    private final Pong pong;
    private final Kayttoliittyma kali;
    
    public PauseNappi(Pong p, Kayttoliittyma i) {
        pong = p;
        kali = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pong.peliAlkanut()) {
            if (pong.isPeliKaynnissa()) {
                pong.setPeliKaynnissa(false);
                kali.asetaIlmoitus("paused");
                kali.pausePiiloon();
                kali.getAnimoija().pauseta();
            } else {
                pong.setPeliKaynnissa(true);
                kali.tyhjennaIlmoitus();
                kali.pauseNakyvaksi();
                kali.getAnimoija().jatka();
            }            
        }
    }
}

