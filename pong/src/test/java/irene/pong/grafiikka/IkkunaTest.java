package irene.pong.grafiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IkkunaTest {
    private Ikkuna ikkuna;
    
    @Before
    public void setUp() {
        ikkuna = new Ikkuna();
        ikkuna.alusta();
    }
    
    @Test
    public void ponginKokoAinaSama() {
        assertEquals(500, ikkuna.getLeveys());
        assertEquals(600, ikkuna.getKorkeus());
    }
    
    @Test
    public void alustaLisaaKentan() {
        assertFalse(ikkuna.getKentta()==null);
    }
}
