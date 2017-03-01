package irene.pong.logiikka;

import irene.pong.komponentit.*;

/**
 * Luokka hallinnnoi Liikkuja-rajapinnan toteuttavien olioiden liikettä
 * kentän reunoilla.
 * 
 */
public class KenttaRajaTarkistaja {
    private Kentta kentta;

    /**
     * Kenttarajatarkastaja saa konstruktorissa kentän, jonka komponenttien
     * liikkeitä sen tulee valvoa.
     * 
     * @param kentta Pelikenttä
     */
    public KenttaRajaTarkistaja(Kentta kentta) {
        this.kentta = kentta;
    }
    
    
    /**
     * Estää Liikkuva-rajapinnan toteuttavaa oliota poistumasta kentältä.
     * 
     * @param l Liikkuva-rajapinnan toteuttava maila.
     */
    public void tarkistaOsuukoKentanReunaan(Liikkuja l) {
        if (l.getY() <= 0) {
            l.setY(0);
        }

        if (l.getY() + l.getKorkeus() >= kentta.getKorkeus()) {
            l.setY(kentta.getKorkeus() - l.getKorkeus());
        }
    }
}
