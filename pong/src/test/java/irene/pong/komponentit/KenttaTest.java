package irene.pong.komponentit;

import irene.pong.komponentit.Kentta;
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
        assertTrue(kentta.getOikeaPelaaja()!= null);
        assertTrue(kentta.getPallo() != null);
        assertTrue(kentta.getVasenPelaaja()!= null);
    }
    
    @Test
    public void kentallaKaksiEriPelaajaa() {
        assertFalse(kentta.getOikeaPelaaja()== kentta.getVasenPelaaja());
    }
}
