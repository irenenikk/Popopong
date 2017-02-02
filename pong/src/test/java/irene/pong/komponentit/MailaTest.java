package irene.pong.komponentit;

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
}
