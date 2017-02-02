package irene.pong.grafiikka;

import irene.pong.logiikka.KomponenttiHallinta;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

public class Animoija implements TimingTarget {
    private final KomponenttiHallinta kontrolleri;
    private final Animator animaattori;
    
    public Animoija(KomponenttiHallinta k) {
        kontrolleri = k;
        animaattori = new Animator.Builder()
            .setRepeatCount(Animator.INFINITE)
            .addTarget(this)
            .build();
    }

    
    public void aloitaPeli() {
        animaattori.start();
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
