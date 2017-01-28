package irene.pong.grafiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KenttaTest {
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
        kentta.aloita();
    }
    
    @Test
    public void kentallaOnPalloJaKaksiMailaa() {
        assertTrue(kentta.getPelaaja1() != null);
        assertTrue(kentta.pallo() != null);
        assertTrue(kentta.getPelaaja2() != null);
    }
    
    @Test
    public void kentallaKaksiEriPelaajaa() {
        assertFalse(kentta.getPelaaja1() == kentta.getPelaaja2());
    }
    
    @Test
    public void josPalloOsuuYlareunaanSuuntaVaihtuu() {
        Pallo pallo = kentta.pallo();
        pallo.setX(1);
        pallo.setY(1);
        pallo.liiku();
        kentta.paivita();
        assertEquals(pallo.getSuuntaY(), 1);
    }
    
    @Test
    public void josPalloOsuuAlareunaanSuuntaVaihtuu() {
        Pallo pallo = kentta.pallo();
        pallo.setX(1);
        pallo.setY(-kentta.getHeight());
        pallo.setSuuntaY(-1);
        pallo.liiku();
        kentta.paivita();
        assertEquals(pallo.getSuuntaY(), -1);
    }    
    
    @Test
    public void palloMaalissaLisaaPisteitaVasemmalla() {
        Pallo pallo = kentta.pallo();
        pallo.setX(1);
        pallo.setY(1);
        pallo.setSuuntaX(-1);
        pallo.setSuuntaY(1);
        pallo.liiku();
        kentta.paivita();
        Maila pelaaja2 = kentta.getPelaaja2();
        assertEquals(1, pelaaja2.getPisteet());
    }
}
