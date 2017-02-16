/**
 * Päälogiikkaluokka, joka luo kentän, sen tilaa hallinnoivan kontrollerin sekä pisteet laskevan tilaston.
 * Pong myös säilyttää tiedon pelin nykyisestä tilasta.
 */
package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

public class Pong implements TimingTarget { 

    private final Tilasto tilasto;
    private final KomponenttiHallinta kontrolleri;
    private final Kentta kentta;
    private Pelityyppi pelityyppi;
    private Paivitettava liikePaivitettava;
    private Paivitettava tilannePaivitettava;
    private boolean peliKaynnissa;
    private boolean peliAlkanut;


    public Pong() {
        tilasto = new Tilasto();
        kentta = new Kentta();
        kontrolleri = new KomponenttiHallinta(kentta, tilasto);
        peliKaynnissa = false;
        peliAlkanut = false;
    }

    public Pelityyppi getPelityyppi() {
        return pelityyppi;
    }
    public boolean isPeliKaynnissa() {
        return peliKaynnissa;
    }

    public boolean peliAlkanut() {
        return peliAlkanut;
    }

    public void setPeliAlkanut(boolean peliAlkanut) {
        this.peliAlkanut = peliAlkanut;
    }

    public void setPeliKaynnissa(boolean peliKaynnissa) {
        this.peliKaynnissa = peliKaynnissa;
    }

    public Tilasto getTilasto() {
        return tilasto;
    }

    public KomponenttiHallinta getKontrolleri() {
        return kontrolleri;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setPelityyppi(Pelityyppi pelityyppi) {
        this.pelityyppi = pelityyppi;
    }

    public Paivitettava getLiikePaivitettava() {
        return liikePaivitettava;
    }

    public void setLiikePaivitettava(Paivitettava liikePaivitettava) {
        this.liikePaivitettava = liikePaivitettava;
    }

    public Paivitettava getTilannePaivitettava() {
        return tilannePaivitettava;
    }

    public void setTilannePaivitettava(Paivitettava tilannePaivitettava) {
        this.tilannePaivitettava = tilannePaivitettava;
    }

    public void aloita() {
        /**
         * Kutsuu kontrollerin metodia, joka järjestää kentän aloitusasetelman. 
         * Pyytää mahdollista käyttöliittymää päivittämään, jotta käyttäjä saa seuraavat ohjeet.
         * 
         * @see KomponenttiHallinta
         * 
         * @see Paivitettava
         */
        kontrolleri.alustaKomponentit();
        tilannePaivitettava.paivita();
    }

    @Override
    public void begin(Animator source) {
    }

    @Override
    public void end(Animator source) {
    }

    @Override
    public void repeat(Animator source) {
    }

    @Override
    public void reverse(Animator source) {
    }

    @Override
    public void timingEvent(Animator source, double fraction) {
        if (peliKaynnissa) {
            kontrolleri.paivita();
    //        if (kontrolleri.tarkistaMaali()) {
    //            animoija.stop();                  //miten saada pallo pysymään paikoillaan hetken ennen uuden kierroksen aloittamista?
    //        }

            if (tilasto.voittaja() != null) {
                peliKaynnissa = false;
                tilannePaivitettava.paivita(); 
                peliAlkanut = false;
                pelityyppi = null;
            }
        }
        liikePaivitettava.paivita();
    }
}
