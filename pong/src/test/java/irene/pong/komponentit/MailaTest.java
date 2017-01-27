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
        assertEquals(90, maila.getKorkeus());
    }
    
    @Test
    public void liikutaMuuttaaYnArvoa() {
        maila.setX(0);
        maila.setY(0);
        maila.liikuta(10);
        assertEquals(10, maila.getY());
        assertEquals(0, maila.getX());
    }
    
}
