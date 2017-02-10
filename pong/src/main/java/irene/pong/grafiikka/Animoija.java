/**
 * Luokka totetuttaa pallon ja mailojen liikkeen.
 */
package irene.pong.grafiikka;

import irene.pong.logiikka.KomponenttiHallinta;
import irene.pong.logiikka.Pong;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

public class Animoija implements TimingTarget {

//    private final KomponenttiHallinta kontrolleri;
    private final Pong pong;
    private final Animator animoija;
    private boolean peliKaynnissa;
    private boolean peliAlkanut;

    public Animoija(Pong p) { //sekoittaa logiikkaa ja UI:tä
//        kontrolleri = k;
        pong = p;
        animoija = new Animator.Builder()
                .setRepeatCount(Animator.INFINITE)
                .addTarget(this)
                .build();
        peliKaynnissa = false;
        peliAlkanut = false;
    }

    public Animator getAnimoija() {
        return animoija;
    }

    public void aloitaPeli() {
        peliKaynnissa = true;
        peliAlkanut = true;
        animoija.start();
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

    public void pauseta() {
        animoija.pause();
    }

    public void jatka() {
        animoija.resume();
    }

    @Override
    public void begin(Animator anmtr) {
    }

    @Override
    public void end(Animator anmtr) {
    }

    @Override
    public void repeat(Animator anmtr) {
    }

    @Override
    public void reverse(Animator anmtr) {
    }

    @Override
    public void timingEvent(Animator anmtr, double d) {
        if (peliKaynnissa) {
            pong.getKontrolleri().paivita();
            //        if (kontrolleri.tarkistaMaali()) {
            //            animoija.stop();                  //miten saada pallo pysymään paikoillaan hetken ennen uuden kierroksen aloittamista?
            //        }

            if (pong.getKontrolleri().getTilasto().voittaja() != null) {
                animoija.stop();
                peliKaynnissa = false;
                pong.getIkkuna().asetaIlmoitus("Pelaaja " + pong.getKontrolleri().getTilasto().voittaja().getId() + " voitti"); //mitä sitten?
            }
        }
        pong.getKontrolleri().getKentta().repaint();
    }
}
