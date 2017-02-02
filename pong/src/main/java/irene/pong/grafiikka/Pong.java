package irene.pong.grafiikka;

import irene.pong.kali.PainallusAlas;
import irene.pong.kali.PainallusYlos;
import irene.pong.kali.VapautusAlas;
import irene.pong.kali.VapautusYlos;
import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.BorderLayout;
import java.awt.Color;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Pong extends JFrame {

    private final int leveys = 500;
    private final int korkeus = 600;
    private final Kentta kentta;
    private final KomponenttiHallinta kontrolleri;
    private final Animoija animoija;

    public Pong() {
        kentta = new Kentta();
        kontrolleri = new KomponenttiHallinta(kentta);
        
        final TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
        
        animoija = new Animoija(kontrolleri);
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void aloita() {
        setSize(leveys, korkeus);
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        kentta.setBackground(Color.BLACK);
        add(kentta, BorderLayout.CENTER);
        
        lisaaNappaimet();
        
        setVisible(true);

        animoija.aloitaPeli();
    }
    
    public void lisaaNappaimet() {
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "UP painettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "UP vapautettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN painettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "DOWN vapautettu");

        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "W painettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "W vapautettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "S painettu");
        kentta.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "S vapautettu");

        kentta.getActionMap().put("UP painettu", new PainallusYlos(kontrolleri, "UP"));
        kentta.getActionMap().put("UP vapautettu", new VapautusYlos(kontrolleri, "UP"));
        kentta.getActionMap().put("DOWN painettu", new PainallusAlas(kontrolleri, "DOWN"));
        kentta.getActionMap().put("DOWN vapautettu", new VapautusAlas(kontrolleri, "DOWN"));
        kentta.getActionMap().put("W painettu", new PainallusYlos(kontrolleri, "W"));
        kentta.getActionMap().put("W vapautettu", new VapautusYlos(kontrolleri, "W"));
        kentta.getActionMap().put("S painettu", new PainallusAlas(kontrolleri, "S"));
        kentta.getActionMap().put("S vapautettu", new VapautusAlas(kontrolleri, "S"));        
    }
}
