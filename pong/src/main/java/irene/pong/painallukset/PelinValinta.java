/**
 * Luokka sitoo näppäimen painallukseen pelityypin asettamisen.
 */
package irene.pong.painallukset;

import irene.pong.kayttoliittyma.Kayttoliittyma;
import irene.pong.logiikka.Pelityyppi;
import irene.pong.logiikka.Pong;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PelinValinta extends AbstractAction {

    private Pong peli;
    private String kirjain;
    private Kayttoliittyma kali;

    public PelinValinta(Pong p, String k, Kayttoliittyma kl) {
        peli = p;
        kirjain = k;
        kali = kl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!peli.peliAlkanut() && !peli.isPeliKaynnissa() && peli.getPelityyppi() == null && peli.getTilasto().voittaja() == null) {
            if (kirjain.equals("1")) {
                peli.setPelityyppi(Pelityyppi.YKSINPELI);
                kali.lisaaPelaajienNappaimet(Pelityyppi.YKSINPELI);
            } else if (kirjain.equals("2")) {
                peli.setPelityyppi(Pelityyppi.KAKSINPELI);
                kali.lisaaPelaajienNappaimet(Pelityyppi.KAKSINPELI);
            }
//            peli.setPeliAlkanut(true);
            kali.paivita();
        }
    }

}
