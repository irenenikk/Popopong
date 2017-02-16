package irene.pong.logiikka;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PongTest {
    private Pong pong;
    
    @Before
    public void setUp() {
        pong = new Pong();
        pong.setTilannePaivitettava(new Paivitettava() {
            @Override
            public void paivita() {
            }
        });
    }
    
    @Test
    public void peliAluksEiKaynnissaTaiAlkanut() {
        assertFalse(pong.isPeliKaynnissa());
        assertFalse(pong.peliAlkanut());
    }
    
    @Test
    public void aloitaKutsuuKontrollerinAlustaMetodia() {
        pong.aloita();
        assertTrue(palloKentanKeskella());
        assertTrue(mailatKentanKeskella());
    }
    
    private boolean palloKentanKeskella() {
        return pong.getKentta().getPallo().getX() == 240 && pong.getKentta().getPallo().getY() == 290;
    }
    
    private boolean mailatKentanKeskella() {
        return pong.getKentta().getVasenPelaaja().getX() == 0 
                && pong.getKentta().getOikeaPelaaja().getX() == 480
                && pong.getKentta().getVasenPelaaja().getY() == 250 
                && pong.getKentta().getOikeaPelaaja().getY() == 250;
    }
        
}
