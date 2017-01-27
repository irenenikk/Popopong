package irene.pong.grafiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PongTest {
    private Pong pong;
    
    @Before
    public void setUp() {
        pong = new Pong();
    }
    
    @Test
    public void ponginKokoAinaSama() {
        assertEquals(500, pong.getLeveys());
        assertEquals(600, pong.getKorkeus());
    }
    
    @Test
    public void aloitaLisaaKentan() {
        pong.aloita();
        assertFalse(pong.getKentta()==null);
    }
}
