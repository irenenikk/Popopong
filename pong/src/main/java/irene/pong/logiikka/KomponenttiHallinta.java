
package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.awt.Rectangle;

public class KomponenttiHallinta {
    Kentta kentta;
    Maila pelaaja1;
    Maila pelaaja2;
    Pallo pallo;

    public KomponenttiHallinta(Kentta kentta) {
        this.kentta = kentta;
        pallo = kentta.getPallo();
        pelaaja1 = kentta.getPelaaja1();
    }

    public void aloita() {
        asetaPalloKeskelle();
        asetaPelaaja1Keskelle();
        asetaPelaaja2Keskelle();
    }

    public void asetaPalloKeskelle() {
        pallo.setX(kentta.getWidth() / 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getHeight() / 2 - pallo.getHalkaisija() / 2);
    }

    public void asetaPelaaja1Keskelle() {
        pelaaja1.setX(0);
        pelaaja1.setY(kentta.getHeight() / 2 - kentta.getPelaaja1().getKorkeus() / 2);
    }

    public void asetaPelaaja2Keskelle() {
        kentta.getPelaaja2().setX(kentta.getWidth() - pelaaja2.getLeveys());
        kentta.getPelaaja2().setY(kentta.getHeight() / 2 - pelaaja2.getKorkeus() / 2);
    }

    public void paivita() {
        pallo.liiku();
        tarkistaKimpoaakoPallo();
        tarkistaMaali();
        tarkistaOsuukoPalloMailaan();
    }

    public void tarkistaKimpoaakoPallo() {
        if (pallo.getY() >= 0) {
            pallo.setSuuntaY(-1);
            pallo.setY(pallo.getY() - 1);
        }

        if (pallo.getY() <= -kentta.getHeight()) {
            pallo.setSuuntaY(1);
            pallo.setY(pallo.getY() + 1);
        }
    }

    public void tarkistaMaali() {
        if (pallo.getX() <= 0 - pallo.getHalkaisija()) {
            pelaaja2.lisaaPiste();
            asetaPalloKeskelle();
        }

        if (pallo.getX() >= kentta.getWidth()) {
            pelaaja1.lisaaPiste();
            asetaPalloKeskelle();
        }
    }

    public void tarkistaOsuukoPalloMailaan() {
        Rectangle pallonRajat = new Rectangle(pallo.getX(), pallo.getY(), pallo.getHalkaisija(), pallo.getHalkaisija());
        Rectangle pelaaja1Rajat = new Rectangle(pelaaja1.getX(), pelaaja1.getY(), pelaaja1.getLeveys(), pelaaja1.getKorkeus());
        Rectangle pelaaja2Rajat = new Rectangle(pelaaja2.getX(), pelaaja2.getY(), pelaaja2.getLeveys(), pelaaja2.getKorkeus());
        if (pallonRajat.intersects(pelaaja1Rajat)) {
            pallo.setSuuntaX(1);
        }
    }
}
