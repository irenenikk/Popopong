package irene.pong.komponentit;

import irene.pong.logiikka.Suunta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class PalloTest {
    private Pallo pallo;
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
        pallo = kentta.getPallo();
    }
    
    @Test
    public void pallonHalkaisijaAina20() {
        assertEquals(20, pallo.getHalkaisija());
    }
    
    @Test
    public void pallonNopeusAluksi3() {
        assertEquals(3.0, pallo.getNopeus(), 0.1);
    }
        
    @Test
    public void liikutaMuuttaaKoordinaatteja() {
        pallo.setX(0);
        pallo.setY(0);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        assertEquals(3, pallo.getX());
        assertEquals(3, pallo.getY());
    }

}
