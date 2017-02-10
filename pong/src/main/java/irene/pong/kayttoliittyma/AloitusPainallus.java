/**
 * Luokka sitoo välilyönnin painamiseen pelin aloittamisen.
 */
package irene.pong.kayttoliittyma;

import irene.pong.grafiikka.Animoija;
import irene.pong.grafiikka.Ikkuna;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class AloitusPainallus extends AbstractAction {

    private final Animoija animoija;
    private final Ikkuna ikkuna;

    public AloitusPainallus(Animoija a, Ikkuna i) {
        animoija = a;
        ikkuna = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!animoija.isPeliKaynnissa() && !animoija.getAnimoija().isPaused() && animoija.peliAlkanut()) {
            animoija.setPeliKaynnissa(false);
            animoija.aloitaPeli();
            ikkuna.pauseNakyvaksi();
            ikkuna.asetaIlmoitus("");
        }
    }

}
