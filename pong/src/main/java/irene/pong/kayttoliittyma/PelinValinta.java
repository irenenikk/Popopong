/**
 * Luokka sitoo näppäimen painallukseen pelityypin asettamisen.
 */
package irene.pong.kayttoliittyma;

import irene.pong.logiikka.Pelityyppi;
import irene.pong.logiikka.Pong;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PelinValinta extends AbstractAction {

    private Pong peli;
    private String kirjain;

    public PelinValinta(Pong p, String k) {
        peli = p;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!peli.getAnimoija().peliAlkanut()) {
            if (kirjain.equals("1")) {
                peli.setPelityyppi(Pelityyppi.YKSINPELI);
            } else if (kirjain.equals("2")) {
                peli.setPelityyppi(Pelityyppi.KAKSINPELI);
            }
            peli.getAnimoija().setPeliAlkanut(true);
        }
    }

}
