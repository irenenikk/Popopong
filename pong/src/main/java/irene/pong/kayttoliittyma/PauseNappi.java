/**
 * Luokka sitoo napin painalluksen mailan liikkeeseen.
 */

package irene.pong.kayttoliittyma;

import irene.pong.grafiikka.Animoija;
import irene.pong.grafiikka.Ikkuna;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PauseNappi extends AbstractAction {

    private final Animoija animoija;
    private final Ikkuna ikkuna;
    
    public PauseNappi (Animoija a, Ikkuna i) {
        animoija = a;
        ikkuna = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (animoija.isPeliKaynnissa()) {
            animoija.setPeliKaynnissa(false);
            ikkuna.asetaIlmoitus("PYSÄYTETTY");
            ikkuna.pausePiiloon();
            animoija.pauseta();
        } else {
            animoija.setPeliKaynnissa(true);
            ikkuna.asetaIlmoitus("");
            ikkuna.pauseNakyvaksi();
            animoija.jatka();
        }
    }
}

