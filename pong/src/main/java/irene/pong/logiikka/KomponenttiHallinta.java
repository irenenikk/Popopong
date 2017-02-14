/**
 * Luokka hallinnoi komponenttien liikkeitä kentällä. 
 * Liikuttaa palloa sekä mailoja, saa pallon kimpoamaan seinistä ja mailoista, sekä tarkistaa maalit.
 */

package irene.pong.logiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class KomponenttiHallinta {

    Kentta kentta;
    Maila maila1;
    Maila maila2;
    Pallo pallo;
    Tilasto tilasto;

    public KomponenttiHallinta(Kentta k, Tilasto t) {
        kentta = k;
        pallo = kentta.getPallo();
        maila1 = kentta.getVasenPelaaja();
        maila2 = kentta.getOikeaPelaaja();
        tilasto = t;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public Maila getMaila1() {
        return maila1;
    }

    public Maila getMaila2() {
        return maila2;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Tilasto getTilasto() {
        return tilasto;
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
        pallo.setX(kentta.getLeveys()/ 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getKorkeus()/ 2 - pallo.getHalkaisija() / 2);
    }

    private void asetaPelaaja1Keskelle() {
        maila1.setX(0);
        maila1.setY(kentta.getKorkeus()/ 2 - maila1.getKorkeus() / 2);
    }

    private void asetaPelaaja2Keskelle() {
        maila2.setX(kentta.getLeveys()- maila2.getLeveys());
        maila2.setY(kentta.getKorkeus()/ 2 - maila2.getKorkeus() / 2);
    }

    public void paivita() {
        /**
         * Liikuttaa komponentteja aiheuttaen myös niiden kimpoamisen seinistä ja mailoista, sekä tarkistaa maalit.
         */
        pallo.liiku();
        maila1.liiku();
        maila2.liiku();
        tarkistaKimpoaakoPallo();
        tarkistaOsuukoPalloMailaan();
        tarkistaOsuvatkoMailatReunaan();
        tarkistaMaali();
    }

    public void tarkistaKimpoaakoPallo() {
        if (pallo.getY() <= 0) {
            pallo.setSuuntaY(Suunta.ALAS);
            pallo.setY(pallo.getY() + pallo.getNopeus());
        }

        if (pallo.getY() + pallo.getHalkaisija() >= kentta.getKorkeus()) {
            pallo.setSuuntaY(Suunta.YLOS);
            pallo.setY(pallo.getY() - pallo.getNopeus());
        }
    }

    public void tarkistaMaali() {
        if (pallo.getX() + pallo.getHalkaisija() <= 0) { //tähän tieto maalista --> pallon liike pysäytetään parin kierroksen ajaksi?
            tilasto.lisaaPistePelaajalle(Pelaaja.OIKEA);
            asetaPalloKeskelle();
        }

        if (pallo.getX() >= kentta.getLeveys()) {
            tilasto.lisaaPistePelaajalle(Pelaaja.VASEN);
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
    
    public void tarkistaOsuvatkoMailatReunaan() {
        if (maila1.getY() <= 0) {
            maila1.setY(0);
        }
        
        if (maila1.getY() + maila1.getKorkeus() >= kentta.getKorkeus()) {
            maila1.setY(kentta.getKorkeus() - maila1.getKorkeus());
        }
        
        if (maila2.getY() <= 0) {
            maila2.setY(0);
        }
        
        if (maila2.getY() + maila2.getKorkeus() >= kentta.getKorkeus()) {
            maila2.setY(kentta.getKorkeus() - maila2.getKorkeus());
        }

    }
    
}
