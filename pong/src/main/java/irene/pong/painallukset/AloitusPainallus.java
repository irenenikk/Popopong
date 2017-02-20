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
        /**
         * Luokka saa konstruktorissaan sekä päälogiikkaluokan, että pääkäyttöliittymäluokan.
         * 
         * @see logiikka.Pong
         * @see logiikka.Kayttoliittyma
         */
        pong = p;
        kali = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Jos peli on vasta aloitettu, päivittää päälogiikkaluokan tilan, tyhjentää kentän yläreunan ilmoituksen ja aloittaa pelianimaation.
         * Jos peli on juuri loppunut, nollaa pelitilanteen nollaamalla tilastoluokan pisteetsekä mailoille viimeksi annetut suunnat, poistaa tekoälyn käytöstä, ja palauttaa pallon nopeuden aloitusnopeudeksi.
         */
        if (!pong.isPeliKaynnissa() && !kali.getAnimoija().getAnimoija().isPaused() && !pong.peliAlkanut() && pong.getPelityyppi() == null) {
            pong.getTilasto().nollaaPisteet();
            pong.getKontrolleri().poistaTekoAlY();
            pong.getKontrolleri().getKentta().getPallo().palautaNopeus();
            pong.getKontrolleri().getKentta().getOikeaPelaaja().nollaaSuunnat();
            pong.getKontrolleri().getKentta().getVasenPelaaja().nollaaSuunnat();  
            pong.getKentta().getEsteet().clear();
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
