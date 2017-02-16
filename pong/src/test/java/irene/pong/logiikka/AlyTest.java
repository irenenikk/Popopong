/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author nikkaire
 */
public class AlyTest {
    
    private Aly aly;
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
        aly = new Aly(kentta);
    }
    
    @Test
    public void josPalloVasemmallaSeuraaPalloaYlos() {
        kentta.getPallo().setX(200);
        kentta.getVasenPelaaja().setY(200);
        kentta.getPallo().setY(0);
        aly.liikutaMailaa();
        assertTrue(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
    }
    
    @Test
    public void josPalloVasemmallaSeuraaPalloaAlas() {
        kentta.getPallo().setX(200);
        kentta.getVasenPelaaja().setY(0);
        kentta.getPallo().setY(300);
        aly.liikutaMailaa();
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertTrue(kentta.getVasenPelaaja().isKiihdytaAlas());
    }    
    
    @Test
    public void josPalloOikeallaeuraaPelaajaaVastaikkaisestiAlas() {
        kentta.getPallo().setX(400);
        kentta.getVasenPelaaja().setY(0);
        kentta.getOikeaPelaaja().setY(0);
        aly.liikutaMailaa();
        assertFalse(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertTrue(kentta.getVasenPelaaja().isKiihdytaAlas());
    }    
        
    @Test
    public void josPalloOikeallaeuraaPelaajaaVastaikkaisestiYlos() {
        kentta.getPallo().setX(400);
        kentta.getVasenPelaaja().setY(300);
        kentta.getOikeaPelaaja().setY(300);
        aly.liikutaMailaa();
        assertTrue(kentta.getVasenPelaaja().isKiihdytaYlos());
        assertFalse(kentta.getVasenPelaaja().isKiihdytaAlas());
    }    
}
