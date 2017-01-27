package irene.pong.komponentit;

import irene.pong.grafiikka.Kentta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class PalloTest {
    private Pallo pallo;
    private Kentta kentta;
    
    @Before
    public void setUp() {
        pallo = new Pallo();
        kentta = new Kentta();
    }
    
    @Test
    public void pallonHalkaisijaAina20() {
        assertEquals(20, pallo.getHalkaisija());
    }
    
    @Test
    public void pallonNopeusAina5() {
        assertEquals(5, pallo.getNopeus());
    }
        
    @Test
    public void liikutaMuuttaaKoordinaatteja() {
        pallo.setX(0);
        pallo.setY(0);
        pallo.liiku();
        assertEquals(5, pallo.getX());
        assertEquals(-5, pallo.getY());
    }

}
