/**
 * Luokka hallinnoi komponenttien liikkeitä kentällä.
 * Liikuttaa palloa sekä mailoja, saa pallon kimpoamaan seinistä ja mailoista, sekä tarkistaa maalit.
 *
 * @see Kentta
 */
package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class KomponenttiHallinta {

    Kentta kentta;
    Maila maila1;
    Maila maila2;
    Pallo pallo;
    Tilasto tilasto;
    int pallonOsumat;
    int pallonOdotusaika;
    Aly aly;

    public KomponenttiHallinta(Kentta k, Tilasto t) {
        /**
         * Komponenttihallinta saa konstruktorissaan kentän, sekä pelin tilaston. Lisäksi pallonosumat alustetaan ykköseksi, jotta pallo ei nopeutuisi alusta lähtien.
         * 
         * @see Kentta
         * @see Tilasto
         */
        kentta = k;
        pallo = kentta.getPallo();
        maila1 = kentta.getVasenPelaaja();
        maila2 = kentta.getOikeaPelaaja();
        tilasto = t;
        pallonOsumat = 1;
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
    
    public void kaytaTekoalya() {
        aly = new Aly(kentta);
    }

    public Aly getAly() {
        return aly;
    }

    public void setPallonOsumat(int pallonOsumat) {
        this.pallonOsumat = pallonOsumat;
    }

    public int getPallonOsumat() {
        return pallonOsumat;
    }

    public int getPallonOdotusaika() {
        return pallonOdotusaika;
    }
    
    public void setPallonOdotusaika(int i) {
        pallonOdotusaika = i;
    }

    public void alustaKomponentit() {
        /**
         * Asettaa komponentit alun tilanteeseen, jossa pallo ja mailat ovat keskellä.
         * 
         * @see Maila
         * @see Pallo 
         */

        asetaPalloKeskelle();
        asetaPelaaja1Keskelle();
        asetaPelaaja2Keskelle();
    }

    public void paivita() {
        /**
         * Liikuttaa komponentteja, varmistaa etteivät ne mene kentän ulkopuolella ja tarkistaa maalit.
         * 
         * @see Maila
         * @see Pallo
         */
        
        tarkistaLiikkuukoPallo();
        pallo.liiku();
        if (aly != null) {
            aly.liikutaMailaa();
        }
        maila1.liiku();
        maila2.liiku();
        tarkistaKimpoaakoPallo();
        tarkistaOsuukoPalloMailaan();
        tarkistaOsuvatkoMailatReunaan();
        tarkistaMaali();
        tarkistaNopeutus();
    }

    private void asetaPalloKeskelle() {
        pallo.setX(kentta.getLeveys() / 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getKorkeus() / 2 - pallo.getHalkaisija() / 2);
    }

    private void asetaPelaaja1Keskelle() {
        maila1.setX(0);
        maila1.setY(kentta.getKorkeus() / 2 - maila1.getKorkeus() / 2);
    }

    private void asetaPelaaja2Keskelle() {
        maila2.setX(kentta.getLeveys() - maila2.getLeveys());
        maila2.setY(kentta.getKorkeus() / 2 - maila2.getKorkeus() / 2);
    }

    private void tarkistaKimpoaakoPallo() { //kommentit johtuu testailusta erilaisen pallonliikkeen kanssa
        if (pallo.getY() <= 0) {
            pallo.setSuuntaY(Suunta.ALAS);
            pallo.setY(pallo.getY() + (int) pallo.getNopeus());
            
//        if (pallo.getY() <= 0 || pallo.getY() + pallo.getHalkaisija() >= kentta.getKorkeus()) {
//            double x = pallo.getX();
//            double y = pallo.getY();
//            double c = -2 * (pallo.getdX() * x + pallo.getdY() * y) / (x * x + y * y);
//            pallo.setdX(pallo.getdX() + c * x);
//            pallo.setdY(pallo.getdY() + c * y);
//            pallo.setdY(pallo.getdY()*-1);
//            pallo.setY(pallo.getY() + (int) pallo.getdY());
        }

        if (pallo.getY() + pallo.getHalkaisija() >= kentta.getKorkeus()) {
            pallo.setSuuntaY(Suunta.YLOS);
            pallo.setY(pallo.getY() - (int) pallo.getNopeus());
            
//            pallo.setdY(pallo.getdY()*-1);
////            double x = pallo.getX() ;
////            double y = pallo.getY() - kentta.getKorkeus();
////            double c = -2 * (pallo.getdX() * x + pallo.getdY() * y) / (x * x + y * y);
////            pallo.setdX(pallo.getdX() + c * x);
////            pallo.setdY(pallo.getdY() + c * y);
//        }
        }
    }

    private void tarkistaMaali() {
        if (pallo.getX() + pallo.getHalkaisija() <= 0) { 
            tilasto.lisaaPistePelaajalle(Pelaaja.OIKEA);
            asetaPalloKeskelle();
            pallo.vaihdaSuuntaaSatunnaisesti();
            pallo.setPaikallaan(true);
            pallonOdotusaika = 15;
        }

        if (pallo.getX() >= kentta.getLeveys()) {
            tilasto.lisaaPistePelaajalle(Pelaaja.VASEN);
            asetaPalloKeskelle();
            pallo.vaihdaSuuntaaSatunnaisesti();
            pallo.setPaikallaan(true);
            pallonOdotusaika = 15;
        }
    }

    private void tarkistaOsuukoPalloMailaan() {
        if (pallo.getRajat().intersects(maila1.getRajat())) {
//        if (pallo.getX() <= maila1.getX() + maila1.getLeveys() && pallo.getY() >= maila1.getY() && pallo.getY() <= maila1.getY() + maila1.getKorkeus()) {
//            System.out.println("osui mailaan 1");
            
//            if (pallo.getX() == maila1.getX() + maila1.getLeveys()) {
                pallo.setSuuntaX(Suunta.OIKEA);
                pallo.setX(pallo.getX() + (int) pallo.getNopeus());
//                pallo.setdX(pallo.getdX()*-1);
//                pallo.setX(pallo.getX() + (int) pallo.getdX());            
                pallonOsumat++;
//                if (Math.abs(pallo.getY() - maila1.getY()) != 0) {
//                    pallo.setdY(pallo.getdY()*-1);
//                }
//            }
//            
//            if (pallo.getY() < maila1.getY()) {
//                pallo.setSuuntaY(Suunta.YLOS);
//                pallo.setY(pallo.getY() - (int)pallo.getNopeus());
//            } else if (pallo.getY() > maila1.getY() + maila1.getKorkeus()) {
//                pallo.setSuuntaY(Suunta.ALAS);
//                pallo.setY(pallo.getY() + (int)pallo.getNopeus());
//            }
            
//            double x = pallo.getX();
//            double y = pallo.getY();
//            double c = -2 * (pallo.getdX() * x + pallo.getdY() * y) / (x * x + y * y);
//            pallo.setdX(pallo.getdX() + c * x);
//            pallo.setdY(pallo.getdY() + c * y);
            
        }

        if (pallo.getRajat().intersects(maila2.getRajat())) {
//        if (pallo.getX() + pallo.getHalkaisija() >= maila2.getX() && pallo.getY() >= maila2.getY() && pallo.getY() <= maila2.getY() + maila2.getKorkeus()) {
//            if (pallo.getX() + pallo.getHalkaisija() == maila2.getX()) {
                pallo.setSuuntaX(Suunta.VASEN);
                pallo.setX(pallo.getX() - (int) pallo.getNopeus());
//                pallo.setdX(pallo.getdX()*-1);
//                pallo.setX(maila2.getLeveys());            
                pallonOsumat++;
//            }
//            
//            if (pallo.getSuuntaY() == Suunta.ALAS) {
//                pallo.setSuuntaY(Suunta.YLOS);
//            }
//            if (pallo.getSuuntaY() == Suunta.YLOS) {
//                pallo.setSuuntaY(Suunta.ALAS);
//            }
//            
//            if (pallo.getY() < maila2.getY()) {
//                pallo.setSuuntaY(Suunta.YLOS);
//                pallo.setY(pallo.getY() - (int)pallo.getNopeus());
//            } else if (pallo.getY() > maila2.getY() + maila2.getKorkeus()) {
//                pallo.setSuuntaY(Suunta.ALAS);
//                pallo.setY(pallo.getY() + (int)pallo.getNopeus());
//            }

//            
//            double x = pallo.getX();
//            double y = pallo.getY();
//            double c = -2 * (pallo.getdX() * x + pallo.getdY() * y) / (x * x + y * y);
//            pallo.setdX(pallo.getdX() + c * x);
//            pallo.setdY(pallo.getdY() + c * y);

        }


//        if (maila1.osuu(pallo.getX(), pallo.getY())) {
//            System.out.println("Osui mailaan 1");
//        }
//        

//        if (maila2.osuu(pallo.getX() + pallo.getHalkaisija(), pallo.getY())) {
//            if (pallo.getX() + pallo.getHalkaisija() >= maila2.getX()) {
//                pallo.setSuuntaX(Suunta.VASEN);
//                pallo.setX(pallo.getX() - (int) pallo.getNopeus());
//            }
//            
//            if (pallo.getY() + pallo.getHalkaisija() >= maila2.getY()) {
//               pallo.setSuuntaY(Suunta.YLOS);
//               pallo.setY(pallo.getY() - (int) pallo.getNopeus());
//            }
//
//            if (pallo.getY() + pallo.getHalkaisija() <= maila2.getY() + maila2.getKorkeus()) {
//               pallo.setSuuntaY(Suunta.ALAS);
//               pallo.setY(pallo.getY() + (int) pallo.getNopeus());
//            }
//                        
//        }
//        
//        if (maila1.osuu(pallo.getX(), pallo.getY())) {
//            if (pallo.getX() + pallo.getHalkaisija() >= maila1.getX()) {
//                pallo.setSuuntaX(Suunta.OIKEA);
//                pallo.setX(pallo.getX() + (int) pallo.getNopeus());
//            }
//            
        
    }

    private void tarkistaOsuvatkoMailatReunaan() {
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

    public void tarkistaNopeutus() {
        if (pallonOsumat % 10 == 0) {
            pallo.nopeuta();
        }
        
    }
    
    private void tarkistaLiikkuukoPallo() {
        if (pallo.isPaikallaan()) {
            pallonOdotusaika--;
            if (pallonOdotusaika == 0) {
                pallo.setPaikallaan(false);
            }
        }
    }

}
