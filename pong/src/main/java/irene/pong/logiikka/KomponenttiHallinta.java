
package irene.pong.logiikka;

import irene.pong.komponentit.Este;
import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

/**
 * Luokka hallinnoi komponenttien liikkeitä kentällä.
 * Liikuttaa palloa sekä mailoja, aiheuttaa esteiden luomisen sekä pallon
 * nopeutumisen oikealla tiheydellä ja tarkistaa maalit.
 *
 * @see Kentta
 */
public class KomponenttiHallinta {

    private Kentta kentta;
    private Maila maila1;
    private Maila maila2;
    private Pallo pallo;
    private PallonTormaystenTarkistaja pallonTormaystenTarkistaja;
    private KenttaRajaTarkistaja kenttaRajaTarkastaja;
    private Tilasto tilasto;
    private Aly aly;
    
    private int pallonOsumat;
    private int pallonOdotusaika;
    private int esteidenTiheys;
    private boolean kaytaAlya;

    /**
     * Komponenttihallinta saa konstruktorissaan kentän, sekä pelin tilaston.
     * Lisäksi pallonosumat alustetaan ykköseksi, jotta pallo ei nopeutuisi
     * alusta lähtien.
     * 
     * @param k Pelin kenttä
     * @param t Pelin pistetilasto
     */
    public KomponenttiHallinta(Kentta k, Tilasto t) {
        kentta = k;
        tilasto = t;

        pallo = kentta.getPallo();
        maila1 = kentta.getVasenPelaaja();
        maila2 = kentta.getOikeaPelaaja();
        pallonTormaystenTarkistaja = new PallonTormaystenTarkistaja(k);
        kenttaRajaTarkastaja = new KenttaRajaTarkistaja(k);
        
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
    
    /**
     * Asettaa tekoälyn liikuttamaan vasenta pelaajaa.
     * 
     * @see Aly
     */
    public void kaytaTekoalya() {
        kaytaAlya = true;
    }
    
    /**
     * Estää tekoälyä liikuttamasta vasenta pelaajaa.
     * 
     * @see Aly
     */
    public void poistaTekoAlY() {
        kaytaAlya = false;
    }

    public Aly getAly() {
        return aly;
    }

    void setPallonOsumat(int pallonOsumat) {
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
    
    /**
     * Asettaa esteiden tiheyden kahdeksitoista, eli alkuperäiseksi määräksi.
     */
    public void alustaEsteidenTiheys() {
        esteidenTiheys = 12;
    }

    public void setEsteidenTiheys(int esteidenTiheys) {
        this.esteidenTiheys = esteidenTiheys;
    }

    public int getEsteidenTiheys() {
        return esteidenTiheys;
    }

    /**
     * Asettaa komponentit alun tilanteeseen, jossa pallo ja mailat ovat keskellä.
     * Arpoo pallolle satunnaisen suunnan. Nollaa tekoälyn käytön.
     * 
     * @see Maila
     * @see Pallo 
     */
    public void alustaKomponentit() {
        asetaPalloKeskelle();
        pallo.vaihdaSuuntaaSatunnaisesti();
        asetaPelaaja1Keskelle();
        asetaPelaaja2Keskelle();
        esteidenTiheys = 12;
        pallonOsumat = 1;
        kaytaAlya = false;
    }

    /**
     * Liikuttaa komponentteja, varmistaa etteivät ne mene kentän ulkopuolella ja tarkistaa maalit.
     * 
     * @see Maila
     * @see Pallo
     * @see Kentta
     */
    public void paivita() {       
        tarkistaLiikkuukoPallo();
        pallo.liiku();
        
        if (kaytaAlya) {
            aly.liikutaMailaa();
        }
        
        maila1.liiku();
        maila2.liiku();
        
        tarkistaOsuukoPalloMailaan();
        kenttaRajaTarkastaja.tarkistaOsuukoKentanReunaan(maila1);
        kenttaRajaTarkastaja.tarkistaOsuukoKentanReunaan(maila2);
        pallonTormaystenTarkistaja.tarkistaOsuukoPalloEsteeseen();
        pallonTormaystenTarkistaja.tormaakoPalloSeinaan();

        kentta.poistaPoistettavatEsteet();

        tarkistaMaali();
        tarkistaLuodaankoEste();
        tarkistaEsteidenNakyvyys();
        tarkistaPallonNopeutus();
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

    private void tarkistaMaali() {
        Pelaaja pisteensaaja = pallonTormaystenTarkistaja.tarkistaMaali();
        if (pisteensaaja != null) {
            tilasto.lisaaPistePelaajalle(pisteensaaja);
            pallonOdotusaika = 15;
        }
    }

    private void tarkistaOsuukoPalloMailaan() {      
        if (pallonTormaystenTarkistaja.palloTormaa(kentta.getVasenPelaaja()) || pallonTormaystenTarkistaja.palloTormaa(kentta.getOikeaPelaaja())) {
            pallonOsumat++;
        }
    }

    private void tarkistaPallonNopeutus() {
        if (pallonOsumat % 6 == 0) {
            pallo.nopeuta();
            pallonOsumat++;
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
    
    private void tarkistaLuodaankoEste() {
        if (pallonOsumat % esteidenTiheys == 0 && kentta.getEsteet().size() < 5) { 
            kentta.lisaaEste();
            if (esteidenTiheys > 2) {
                esteidenTiheys--;   
            }
            pallonOsumat++;
        }
    }
    
    private void tarkistaEsteidenNakyvyys() {
        kentta.getEsteet().stream().forEach((Este este) -> {
            if (este.getVaroitusaika() == 0) {
                este.setNakyvissa(true);
            } else {
                este.vahennaVaroitusaikaa();
            }
        });
    }
}
