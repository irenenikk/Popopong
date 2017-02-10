/**
 * Luokka hallinnoi komponenttien liikkeitä kentällä. 
 * Liikuttaa palloa sekä mailoja, saa pallon kimpoamaan seinistä ja mailoista, sekä tarkistaa maalit.
 */

package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class KomponenttiHallinta { //ei pitäisi tuntea Kenttää, sillä se on osa UI:tä

    Kentta kentta;
    Maila maila1;
    Maila maila2;
    Pallo pallo;
    Tilasto tilasto;

    public KomponenttiHallinta(Kentta k, Tilasto t) {
        kentta = k;
        pallo = kentta.getPallo();
        maila1 = kentta.getPelaaja1().getMaila();
        maila2 = kentta.getPelaaja2().getMaila();
        tilasto = t;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setKentta(Kentta kentta) {
        this.kentta = kentta;
    }

    public Maila getMaila1() {
        return maila1;
    }

    public void setMaila1(Maila pelaaja1) {
        this.maila1 = pelaaja1;
    }

    public Maila getMaila2() {
        return maila2;
    }

    public void setMaila2(Maila pelaaja2) {
        this.maila2 = pelaaja2;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public void setPallo(Pallo pallo) {
        this.pallo = pallo;
    }

    public Tilasto getTilasto() {
        return tilasto;
    }

    public void setTilasto(Tilasto tilasto) {
        this.tilasto = tilasto;
    }

    public void alustaKomponentit() {
        /**
         * Asettaa komponentit alun tilanteeseen, jossa pallo ja mailat ovat keskellä.
         */
        asetaPalloKeskelle();
        asetaPelaaja1Keskelle();
        asetaPelaaja2Keskelle();
    }

    private void asetaPalloKeskelle() {
        pallo.setX(kentta.getWidth() / 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getHeight() / 2 - pallo.getHalkaisija() / 2);
    }

    private void asetaPelaaja1Keskelle() {
        maila1.setX(0);
        maila1.setY(kentta.getHeight() / 2 - maila1.getKorkeus() / 2);
    }

    private void asetaPelaaja2Keskelle() {
        maila2.setX(kentta.getWidth() - maila2.getLeveys());
        maila2.setY(kentta.getHeight() / 2 - maila2.getKorkeus() / 2);
    }

    public void paivita() {
        /**
         * Liikuttaa komponentteja aiheuttaen myös niiden kimpoamisen seinistä ja mailoista, sekä tarkistaa maalit.
         */
        pallo.liiku();
        maila1.liiku();
        maila2.liiku();
        tarkistaOsuukoMailaReunaan();
        tarkistaKimpoaakoPallo();
        tarkistaOsuukoPalloMailaan();
        tarkistaMaali();
    }

    public void tarkistaKimpoaakoPallo() {
        if (pallo.getY() <= 0) {
            pallo.setSuuntaY(Suunta.ALAS);
            pallo.setY(pallo.getY() + pallo.getNopeus());
        }

        if (pallo.getY() + pallo.getHalkaisija() >= kentta.getHeight()) {
            pallo.setSuuntaY(Suunta.YLOS);
            pallo.setY(pallo.getY() - pallo.getNopeus());
        }
    }

    public void tarkistaMaali() {
        if (pallo.getX() + pallo.getHalkaisija() <= 0) { //tähän tieto maalista --> pallon liike pysäytetään parin kierroksen ajaksi?
            tilasto.lisaaPiste(kentta.getPelaaja2());
            asetaPalloKeskelle();
        }

        if (pallo.getX() >= kentta.getWidth()) {
            tilasto.lisaaPiste(kentta.getPelaaja1());
            asetaPalloKeskelle();
        }
    }

    public void tarkistaOsuukoPalloMailaan() {
        if (pallo.getRajat().intersects(maila1.getRajat())) {
            pallo.setSuuntaX(Suunta.OIKEA);
            pallo.setX(pallo.getX() + pallo.getNopeus());
        }
        
        if (pallo.getRajat().intersects(maila2.getRajat())) {
            pallo.setSuuntaX(Suunta.VASEN);
            pallo.setX(pallo.getX() - pallo.getNopeus());
        }
    }
    
    public void tarkistaOsuukoMailaReunaan() {
        if (maila1.getY() <= 0) {
            maila1.setY(0);
        }
        
        if (maila1.getY() + maila1.getKorkeus() >= kentta.getHeight()) {
            maila1.setY(kentta.getHeight() - maila1.getKorkeus());
        }
        
        if (maila2.getY() <= 0) {
            maila2.setY(0);
        }
        
        if (maila2.getY() + maila1.getKorkeus() >= kentta.getHeight()) {
            maila2.setY(kentta.getHeight() - maila2.getKorkeus());
        }

    }
    
}
