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
        /**
         * Luo pelille animoijan, joka kutsuu päälogiikkaluokan sisällä varsinaisen peliloopin luovaa metodia
         * 
         * @param p päälogiikkaluokka Pong
         */
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
        /**
         * Käynnistää animoijan, eli kentän liikkeen.
         */
        animoija.start();
    }

    public void pauseta() {
        /**
         * Keskeyttää animaation, jos se on käynnissä.
         */
        animoija.pause();
    }

    public void jatka() {
        /**
         * Jatkaa animaatioita, jos se on keskeytetty.
         */
        animoija.resume();
    }

}
