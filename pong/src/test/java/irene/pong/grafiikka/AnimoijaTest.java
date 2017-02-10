package irene.pong.grafiikka;

import irene.pong.komponentit.Pallo;
import irene.pong.logiikka.KomponenttiHallinta;
import irene.pong.logiikka.Pong;
import irene.pong.logiikka.Suunta;
import irene.pong.logiikka.Tilasto;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

    public class AnimoijaTest {
    private Animoija animoija;
    private Pong pong;
    private Kentta kentta;

    
    @Before
    public void setUp() {
        TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
        pong = new Pong();
        kentta = pong.getIkkuna().getKentta();
        animoija = new Animoija(pong);
    }
    
    @Test
    public void peliEiAluksiKaynnissa() {
        assertEquals(false, animoija.isPeliKaynnissa());                                          
    }   
    
    @Test
    public void aloitaPeliKaynnistaaPelin() {
        animoija.aloitaPeli();
        assertEquals(true, animoija.isPeliKaynnissa());
    }
    
//    @Test //animoijan testaaminen epämääräistä
//    public void kontrolleriaEiPaivitetaJosPeliEiOleKaynnissa() {
//        kentta.getPallo().setX(0);
//        kentta.getPallo().setY(0);
//        animoija.setPeliKaynnissa(false);
//        animoija.getAnimoija().start();
//        assertEquals(0, kentta.getPallo().getX());
//        assertEquals(0, kentta.getPallo().getY());
//    }
//    
//    @Test
//    public void kontrolleriPaivitetaanJosPeliOnKaynnissa() {
//        Pallo pallo = kentta.getPallo();
//        pallo.setX(0);
//        pallo.setY(0);
//        pallo.setSuuntaX(Suunta.OIKEA);
//        pallo.setSuuntaY(Suunta.ALAS);
//        animoija.setPeliKaynnissa(true);
//        animoija.getAnimoija().start();
//        assertFalse(pallo.getX() == 0);
//        assertFalse(pallo.getY() == 0);
//    }
    
    @Test
    public void animaatioLoppuuVoittoon() {
        animoija.setPeliKaynnissa(true);
        Pallo pallo = kentta.getPallo();
        kentta.getPelaaja2().setPisteet(14);
        pallo.setX(1);
        pallo.setY(1);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        assertFalse(animoija.getAnimoija().isRunning());
    }
}