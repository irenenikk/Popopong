/**
 * Luokka totetuttaa pallon ja mailojen liikkeen.
 */
package irene.pong.kayttoliittyma;

import irene.pong.logiikka.KomponenttiHallinta;
import irene.pong.logiikka.Pong;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

public class Animoija {

    private final Pong pong;
    private final Animator animoija;

    public Animoija(Pong p) { 
        pong = p;
        animoija = new Animator.Builder()
                .setRepeatCount(Animator.INFINITE)
                .addTarget(pong)
                .build();
    }

    public Animator getAnimoija() {
        return animoija;
    }

    public void aloitaPeli() {
        animoija.start();
    }

    public void pauseta() {
        animoija.pause();
    }

    public void jatka() {
        animoija.resume();
    }

//    @Override
//    public void begin(Animator anmtr) {
//    }
//
//    @Override
//    public void end(Animator anmtr) {
//    }
//
//    @Override
//    public void repeat(Animator anmtr) {
//    }
//
//    @Override
//    public void reverse(Animator anmtr) {
//    }
//
//    @Override
//    public void timingEvent(Animator anmtr, double d) {
//        if (peliKaynnissa) {
//            pong.getKontrolleri().paivita();
//            //        if (kontrolleri.tarkistaMaali()) {
//            //            animoija.stop();                  //miten saada pallo pysymään paikoillaan hetken ennen uuden kierroksen aloittamista?
//            //        }
//
//            if (pong.getKontrolleri().getTilasto().voittaja() != null) {
//                peliKaynnissa = false;
//                pong.getIkkuna().asetaIlmoitus("Pelaaja " + pong.getKontrolleri().getTilasto().voittaja().getId() + " voitti"); //mitä sitten?
//            }
//        }
//        pong.getKontrolleri().getKentta().repaint();
//    }
}
