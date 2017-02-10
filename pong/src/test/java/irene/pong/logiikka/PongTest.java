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
        pong.aloita();
    }
    
    @Test
    public void aloitaLisaaIkkunan() {
        assertFalse(pong.getIkkuna()==null);
    }
    
    @Test
    public void aloitaLisaaNappaimet() {
        assertTrue(pong.getIkkuna().getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).size() > 0);
        assertTrue(pong.getIkkuna().getKentta().getActionMap().size() > 0);
        
    }
    
}
