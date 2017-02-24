package irene.pong.komponentit;

import java.util.*;
import java.util.stream.Collectors;
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
    
    @Test
    public void alustaKenttaPalauttaaPallonNopeuden() {
        kentta.getPallo().nopeuta();
        kentta.getPallo().nopeuta();
        kentta.getPallo().nopeuta();
        kentta.alustaKentta();
        assertEquals(3.0, kentta.getPallo().getNopeus(), 0.1);
    }
    
    @Test
    public void alustaKenttaNollaaMailojenSuunnat() {
        kentta.getOikeaPelaaja().setKiihdytaAlas(true);
        kentta.getOikeaPelaaja().setKiihdytaYlos(true);
        kentta.getVasenPelaaja().setKiihdytaAlas(true);
        kentta.getVasenPelaaja().setKiihdytaAlas(true);
        kentta.alustaKentta();
        assertFalse(kentta.getOikeaPelaaja().isKiihdytaAlas());
        assertFalse(kentta.getOikeaPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
    }
    
    @Test
    public void alustaKenttaPoistaaEsteet() {
        kentta.lisaaEste();
        kentta.lisaaEste();
        kentta.alustaKentta();
        assertEquals(0, kentta.getEsteet().size());
    }
    
    @Test
    public void poistaPoistettavatPoistaaJosPoistettava() {
        kentta.lisaaEste();
        kentta.lisaaEste();
        kentta.getEsteet().get(0).setPoistettava(true);
        kentta.poistaPoistettavatEsteet();
        assertEquals(1, kentta.getEsteet().size());
    }
    
     
}
