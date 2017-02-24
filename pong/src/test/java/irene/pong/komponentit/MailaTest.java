package irene.pong.komponentit;

import java.awt.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MailaTest {
    private Maila maila;
    
    @Before
    public void setUp() {
        maila = new Maila();
    }
    
    @Test
    public void mailanKorkeusJaLeveysAinaSamat() {
        assertEquals(20, maila.getLeveys());
        assertEquals(100, maila.getKorkeus());
    }
    
    @Test
    public void liikuMuuttaaYnArvoaKiihdytaYlos() {
        maila.setKiihdytaYlos(true);
        maila.setX(0);
        maila.setY(10);
        maila.liiku();
        assertEquals(5, maila.getY());
    }
    
    @Test
    public void liikuMuuttaaYnArvoaKiihdytaAlas() {
        maila.setKiihdytaAlas(true);
        maila.setX(0);
        maila.setY(0);
        maila.liiku();
        assertEquals(5, maila.getY());
    }
    
    @Test
    public void rajatPalauttaaRectanglet() {
        Assert.assertEquals(new Rectangle().getClass(), maila.getAlarajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), maila.getYlarajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), maila.getVasenRajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), maila.getOikeaRajat().getClass());
    }
    
}
