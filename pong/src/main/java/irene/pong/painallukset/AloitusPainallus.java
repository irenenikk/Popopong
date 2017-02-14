/**
 * Luokka sitoo välilyönnin painamiseen pelin aloittamisen.
 */
package irene.pong.painallukset;

import irene.pong.kayttoliittyma.Animoija;
import irene.pong.kayttoliittyma.Kayttoliittyma;
import irene.pong.logiikka.Pong;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class AloitusPainallus extends AbstractAction {

    private final Pong pong;
    private final Kayttoliittyma kali;

    public AloitusPainallus(Pong p, Kayttoliittyma k) {
        pong = p;
        kali = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pong.isPeliKaynnissa() && !kali.getAnimoija().getAnimoija().isPaused() && !pong.peliAlkanut() && pong.getPelityyppi() == null) {
            pong.getTilasto().nollaaPisteet();
            kali.paivita();
        }
        if (!pong.isPeliKaynnissa() && !kali.getAnimoija().getAnimoija().isPaused() && !pong.peliAlkanut() && pong.getPelityyppi() != null) {
            pong.setPeliKaynnissa(true);
            pong.setPeliAlkanut(true);
            kali.getAnimoija().aloitaPeli();
            kali.pauseNakyvaksi();
            kali.tyhjennaIlmoitus();
        }
    }

}
