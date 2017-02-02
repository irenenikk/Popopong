package irene.pong.grafiikka;

import irene.pong.logiikka.KomponenttiHallinta;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

public class Animoija implements TimingTarget {
    private final KomponenttiHallinta kontrolleri;
    private final Animator animoija;
    
    public Animoija(KomponenttiHallinta k) {
        kontrolleri = k;
        animoija = new Animator.Builder()
            .setRepeatCount(Animator.INFINITE)
            .addTarget(this)
            .build();
    }

    
    public void aloitaPeli() {
        animoija.start();
    }

    @Override
    public void begin(Animator anmtr) {
        kontrolleri.aloita();
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
        kontrolleri.paivita();
        kontrolleri.getKentta().repaint();
    }
    
}
