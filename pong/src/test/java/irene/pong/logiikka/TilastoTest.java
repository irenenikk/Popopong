package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TilastoTest {
    private Tilasto tilasto;
    private Kentta kentta;
    
    @Before
    public void setUp() {
        kentta = new Kentta();
        tilasto = new Tilasto();
    }
    
    @Test
    public void pelaajillaAluksiNollaPistetta() {
        assertEquals(0, tilasto.oikeanPelaajanPisteet());
        assertEquals(0, tilasto.vasemmanPelaajanPisteet());
    }
    
    @Test
    public void lisaaPisteLisaaPisteenOikealle() {
        tilasto.lisaaPistePelaajalle(Pelaaja.OIKEA);
        assertEquals(1, tilasto.oikeanPelaajanPisteet());
    }
    
    @Test
    public void lisaaPisteLisaaPisteenVasemmalle() {
        tilasto.lisaaPistePelaajalle(Pelaaja.VASEN);
        assertEquals(1, tilasto.vasemmanPelaajanPisteet());
    }
    
    @Test
    public void voittajaPalauttaaNullKunPeliKesken() {
        assertEquals(null, tilasto.voittaja());
    }
    
    @Test
    public void voittajaPalauttaaOikeanVoittajanKun2Voittaa() {
        tilasto.setOikeaPisteet(20);
        assertEquals(Pelaaja.OIKEA, tilasto.voittaja());
    }
    
    @Test
    public void voittajaPalauttaaOikeanVoittajanKun1Voittaa() {
        tilasto.setVasenPisteet(20);
        assertEquals(Pelaaja.VASEN, tilasto.voittaja());
    }
    
    @Test
    public void voittajaPalauttaaNullKunEiVoittoa() {
        tilasto.setOikeaPisteet(2);
        tilasto.setVasenPisteet(0);
        assertEquals(null, tilasto.voittaja());
    }
    
    @Test
    public void nollaaPisteetToimii() {
        tilasto.setOikeaPisteet(1);
        tilasto.setVasenPisteet(2);
        tilasto.nollaaPisteet();
        assertEquals(0, tilasto.oikeanPelaajanPisteet());
        assertEquals(0, tilasto.vasemmanPelaajanPisteet());
    }
    
}
