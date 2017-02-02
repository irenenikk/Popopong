package irene.pong;

import irene.pong.grafiikka.Pong;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Main {
    
    public static void main(String[] args) {
        Pong pong = new Pong();
        pong.aloita();
    }
    
}
