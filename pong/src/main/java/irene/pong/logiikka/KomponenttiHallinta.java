/**
 * Luokka hallinnoi komponenttien liikkeitä kentällä.
 * Liikuttaa palloa sekä mailoja, saa pallon kimpoamaan seinistä ja mailoista, sekä tarkistaa maalit.
 *
 * @see Kentta
 */
package irene.pong.logiikka;

import irene.pong.komponentit.Este;
import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class KomponenttiHallinta {

    private Kentta kentta;
    private Maila maila1;
    private Maila maila2;
    private Pallo pallo;
    private Tilasto tilasto;
    private int pallonOsumat;
    private int pallonOdotusaika;
    private int esteidenTiheys;
    private Aly aly;
    private boolean kaytaAlya;

    public KomponenttiHallinta(Kentta k, Tilasto t) {
        /**
         * Komponenttihallinta saa konstruktorissaan kentän, sekä pelin tilaston. Lisäksi pallonosumat alustetaan ykköseksi, jotta pallo ei nopeutuisi alusta lähtien.
         * 
         * @see Kentta
         * @see Tilasto
         */
        kentta = k;
        tilasto = t;

        pallo = kentta.getPallo();
        maila1 = kentta.getVasenPelaaja();
        maila2 = kentta.getOikeaPelaaja();
        
        aly = new Aly(kentta);
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
        kaytaAlya = true;
    }
    
    public void poistaTekoAlY() {
        kaytaAlya = false;
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
    
    public void alustaEsteidenTiheys() {
        esteidenTiheys = 12;
    }

    public void setEsteidenTiheys(int esteidenTiheys) {
        this.esteidenTiheys = esteidenTiheys;
    }

    public int getEsteidenTiheys() {
        return esteidenTiheys;
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
        esteidenTiheys = 12;
        pallonOsumat = 1;
        kaytaAlya = false;
    }

    public void paivita() {
        /**
         * Liikuttaa komponentteja, varmistaa etteivät ne mene kentän ulkopuolella ja tarkistaa maalit.
         * 
         * @see Maila
         * @see Pallo
         * @see Kentta
         */
        
        tarkistaLiikkuukoPallo();
        pallo.liiku();
        
        if (kaytaAlya) {
            aly.liikutaMailaa();
        }
        
        maila1.liiku();
        maila2.liiku();
        
        tarkistaKimpoaakoPallo();
        tarkistaOsuukoPalloMailaan();
        tarkistaOsuvatkoMailatReunaan();
        tarkistaMaali();
        tarkistaLuodaankoEste();
        tarkistaEsteidenNakyvyys();
        tarkistaOsuukoPalloEsteeseen();
        tarkistaPallonNopeutus();
    }

    private void asetaPalloKeskelle() {
        /**
         * Asettaa pallon pelikentän keskelle.
         * 
         * @see Pallo
         */
        pallo.setX(kentta.getLeveys() / 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getKorkeus() / 2 - pallo.getHalkaisija() / 2);
    }

    private void asetaPelaaja1Keskelle() {
        /**
         * Asettaa vasemmanpuoleisen pelaajan kiinni vasemmanpuoleiseen seinään ja keskelle korkeussuunnassa.
         * 
         * @see Maila
         */
        maila1.setX(0);
        maila1.setY(kentta.getKorkeus() / 2 - maila1.getKorkeus() / 2);
    }

    private void asetaPelaaja2Keskelle() {
        /**
         * Asettaa oikeanpuoleisen pelaajan kiinni oikeanpuoleiseen seinään ja keskelle korkeussuunnassa.
         * 
         * @see Maila
         */
        maila2.setX(kentta.getLeveys() - maila2.getLeveys());
        maila2.setY(kentta.getKorkeus() / 2 - maila2.getKorkeus() / 2);
    }

    private void tarkistaKimpoaakoPallo() { //kommentit johtuu testailusta erilaisen pallonliikkeen kanssa
        /**
         * Tarkistaa osuuko pallo seinään, ja aihetuttaa sen kimpoamisen.
         * 
         * @see Pallo
         */
        if (pallo.getY() <= 0) {
            pallo.setSuuntaY(Suunta.ALAS);
            pallo.setY(pallo.getY() + (int) pallo.getNopeus());
        }

        if (pallo.getY() + pallo.getHalkaisija() >= kentta.getKorkeus()) {
            pallo.setSuuntaY(Suunta.YLOS);
            pallo.setY(pallo.getY() - (int) pallo.getNopeus());
        }
    }

    private void tarkistaMaali() {
        /**
         * Tarkistaa, meni pallo vasemmalta tai oikealta yli, lisää tilastoon maalintekijälle pisteen ja asettaa pallon odottamaan keskellä 15 kierroksen ajan.
         * 
         * @see Tilasto
         */
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
        /**
         * Tarkistaa, osuuko pallo mailaan ja aiheuttaa siitä kimpoamisen.
         * Kimpoaminen tarkistetaan erikseen mailan ylä- ja alapäälle, jotta pallo kimpoaan myös ylöspäin.
         * 
         * @see Pallo
         * @see Maila
         */
        
        if (pallo.getRajat().intersects(maila1.getRajat())) {
            pallo.setSuuntaX(Suunta.OIKEA);
            pallo.setX(pallo.getX() + (int) pallo.getNopeus());
            pallonOsumat++;
        } else if (pallo.getRajat().intersects(maila2.getRajat())) {
            pallo.setSuuntaX(Suunta.VASEN);
            pallo.setX(pallo.getX() - (int) pallo.getNopeus());
            pallonOsumat++;
        }

        if (pallo.getRajat().intersects(maila2.getAlapaa()) || pallo.getRajat().intersects(maila1.getAlapaa())) {
            pallo.setSuuntaY(Suunta.ALAS);
            pallo.setY(pallo.getY() + (int) pallo.getNopeus());
            pallonOsumat++;
        }
        if (pallo.getRajat().intersects(maila2.getYlapaa()) || pallo.getRajat().intersects(maila1.getYlapaa())) {
            pallo.setSuuntaY(Suunta.YLOS);
            pallo.setY(pallo.getY() - (int) pallo.getNopeus());
            pallonOsumat++;
        }

        
        
    }

    private void tarkistaOsuvatkoMailatReunaan() {
        /**
         * Estää pelaajien mailojen menemisen kentän ulkopuolelle.
         * 
         * @see Kentta
         */
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

    public void tarkistaPallonNopeutus() {
        /**
         * Nopeuttaa pallon liikettä, jos se on osunut pelaajien mailoihin tarpeeksi monta kertaa.
         * 
         * @see Pallo#nopeuta()
         */
        if (pallonOsumat % 6 == 0) {
            pallo.nopeuta();
            pallonOsumat++;
        }
        
    }
    
    private void tarkistaLiikkuukoPallo() {
        /**
         * Tarkistaa, onko pallo odottanut sille annetun määrän kierroksia, ja tulisiko sen siis liikkua.
         * 
         * @see Pallo
         */
        if (pallo.isPaikallaan()) {
            pallonOdotusaika--;
            if (pallonOdotusaika == 0) {
                pallo.setPaikallaan(false);
            }
        }
    }
    
    private void tarkistaLuodaankoEste() {
        /**
         * Tarkistaa, onko aika luoda uusi este, eli onko aikaa kulunut tarpeeksi ja onko esteet-lista liian iso.
         */
        if (pallonOsumat % esteidenTiheys == 0 && kentta.getEsteet().size() < 8) { 
            kentta.lisaaEste();
            if (esteidenTiheys > 2) {
                esteidenTiheys--;   
            }
            pallonOsumat++;
        }
    }
    
    private void tarkistaOsuukoPalloEsteeseen() {
        /**
         * Tarkistaa, törmääkö pallo esteeseen ja poistaa tällöin sen.
         * Este tuntee varoitusaikansa ja näkyvyytensä, muttei itse vaihda näkyvyyden tilaa, vaan sen tekee kontrolleri.
         */
        kentta.getEsteet().stream().forEach((Este este) ->{
            if (este.isNakyvissa()) {
                if (pallo.getRajat().intersects(este.getRajat())) {
                    pallo.vaihdaSuuntaaPäinvastaiseen();
                    este.setPoistettava(true);
                }                
            }
        });
        kentta.poistaPoistettavat();
    }
    
    private void tarkistaEsteidenNakyvyys() {
        kentta.getEsteet().stream().forEach((Este este) ->{
            if (este.getVaroitusaika() == 0) {
                este.setNakyvissa(true);
            } else {
                este.vahennaVaroitusaikaa();
            }
        });
    }
}
