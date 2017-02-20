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
        /**
         * Luokka saa päälogiikkaluokan, pääkäyttöliittymäluokan, sillä se muokkaa sekä pelin tilaa että käyttäjälle näkyviä ilmoituksia. Luokka saa myös painetun näppäimen kirjaimen.
         * 
         * @param p päälogiikkaluokka
         * @param k painetun näppäimen kirjain
         * @param kl pääkäyttöliittymäluokka
         * 
         * @see logiikka.Pong
         * @see kayttoliittyma.Kayttoliittyma
         */
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
                peli.getKontrolleri().kaytaTekoalya();
            } else if (kirjain.equals("2")) {
                peli.setPelityyppi(Pelityyppi.KAKSINPELI);
                kali.lisaaPelaajienNappaimet(Pelityyppi.KAKSINPELI);
            }
            kali.paivita();
        }
    }

}
