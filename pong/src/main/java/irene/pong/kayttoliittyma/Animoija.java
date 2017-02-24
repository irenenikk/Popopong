package irene.pong.kayttoliittyma;

import irene.pong.logiikka.KomponenttiHallinta;
import irene.pong.logiikka.Pong;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

/**
 * Luokka totetuttaa pallon ja mailojen liikkeen.
 */
public class Animoija {

    private final Pong pong;
    private final Animator animoija;

    /**
     * Luo pelille animoijan, joka kutsuu päälogiikkaluokan sisällä varsinaisen peliloopin luovaa metodia.
     * 
     * @param p päälogiikkaluokka Pong
     */
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

    /**
     * Käynnistää animoijan, eli kentän liikkeen.
     */
    public void aloitaPeli() {
        animoija.start();
    }

    /**
     * Keskeyttää animaation, jos se on käynnissä.
     */
    public void pauseta() {
        animoija.pause();
    }

    /**
     * Jatkaa animaatioita, jos se on keskeytetty.
     */
    public void jatka() {
        animoija.resume();
    }

}
