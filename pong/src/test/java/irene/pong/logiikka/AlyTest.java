package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AlyTest {
    
    private Aly aly;
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
        aly = new Aly(kentta);
    }
    
    @Test
    public void josPalloVasemmallaJaYlapuolellaJaSuuntaVasenMeneYlos() {
        kentta.getPallo().setX(200);
        kentta.getPallo().setSuuntaX(Suunta.VASEN);
        kentta.getPallo().setSuuntaY(Suunta.ALAS);
        kentta.getVasenPelaaja().setY(200);
        kentta.getPallo().setY(0);
        aly.liikutaMailaa();
        
        assertTrue(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
    }
    
    @Test
    public void josPalloYlapuolellaSuuntaKoillinenMeneYlos() {
        kentta.getPallo().setX(200);
        kentta.getPallo().setSuuntaX(Suunta.VASEN);
        kentta.getPallo().setSuuntaY(Suunta.YLOS);
        kentta.getVasenPelaaja().setY(200);
        kentta.getPallo().setY(0);
        aly.liikutaMailaa();
        
        assertTrue(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
    }
    
    @Test
    public void josPalloVasemmallaJaAlapuolellaJaSuuntaVasenMeneAlas() {
        kentta.getPallo().setX(200);
        kentta.getVasenPelaaja().setY(0);
        kentta.getPallo().setSuuntaX(Suunta.VASEN);
        kentta.getPallo().setSuuntaY(Suunta.YLOS);
        kentta.getPallo().setY(300);
        aly.liikutaMailaa();
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertTrue(kentta.getVasenPelaaja().isKiihdytaAlas());
    }
    
    @Test
    public void josPalloAlapuolellaSuuntaLounasMeneAlas() {
        kentta.getPallo().setX(200);
        kentta.getPallo().setY(300);
        kentta.getVasenPelaaja().setY(0);
        kentta.getPallo().setSuuntaX(Suunta.VASEN);
        kentta.getPallo().setSuuntaY(Suunta.ALAS);
        aly.liikutaMailaa();
        
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertTrue(kentta.getVasenPelaaja().isKiihdytaAlas());
    }

    @Test
    public void josPalloVasemmallaSeuraaPalloaAlas() {
        kentta.getPallo().setSuuntaX(Suunta.VASEN);
        kentta.getPallo().setSuuntaY(Suunta.ALAS);
        kentta.getPallo().setX(200);
        kentta.getVasenPelaaja().setY(0);
        kentta.getPallo().setY(300);
        aly.liikutaMailaa();
        
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertTrue(kentta.getVasenPelaaja().isKiihdytaAlas());
    }    
    
    @Test
    public void josPallonSuuntaOikeaPysyPaikallaan() {
        kentta.getPallo().setX(400);
        kentta.getPallo().setSuuntaX(Suunta.OIKEA);
        kentta.getPallo().setSuuntaY(Suunta.ALAS);
        kentta.getVasenPelaaja().setY(200);
        kentta.getPallo().setY(0);
        aly.liikutaMailaa();
        
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
    }
}
