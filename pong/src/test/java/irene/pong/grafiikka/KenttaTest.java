package irene.pong.grafiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KenttaTest {
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
    }
    
    @Test
    public void kentallaOnPalloJaKaksiMailaa() {
        assertTrue(kentta.getPelaaja1() != null);
        assertTrue(kentta.getPallo() != null);
        assertTrue(kentta.getPelaaja2() != null);
    }
    
    @Test
    public void kentallaKaksiEriPelaajaa() {
        assertFalse(kentta.getPelaaja1() == kentta.getPelaaja2());
    }
}
