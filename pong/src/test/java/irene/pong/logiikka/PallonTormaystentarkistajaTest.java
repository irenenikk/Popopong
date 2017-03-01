package irene.pong.logiikka;

import irene.pong.komponentit.Este;
import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PallonTormaystentarkistajaTest {
    private PallonTormaystenTarkistaja tt;
    private Kentta k;
        
    @Before
    public void setUp() {
        k = new Kentta();
        tt = new PallonTormaystenTarkistaja(k);
    }

    @Test
    public void palloTormaaPalauttaaFalseEikaMaaliaJosPalloEiMaalissaTaiMailassa() {
        Pallo pallo = k.getPallo();
        pallo.setX(100);
        pallo.setY(100);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        Maila pelaaja1 = k.getVasenPelaaja();
        pelaaja1.setX(0);
        pelaaja1.setY(0);
        Maila pelaaja2 = k.getOikeaPelaaja();
        pelaaja2.setX(300);
        pelaaja2.setY(300);
        assertFalse(tt.palloKimpoaa(pelaaja1));
        assertFalse(tt.palloKimpoaa(pelaaja2));
        assertEquals(null, tt.tarkistaMaali());
    }
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaOikealle() {
        Pallo pallo = k.getPallo();
        pallo.setX(30);
        pallo.setY(40);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        Maila pelaaja = k.getVasenPelaaja();
        pelaaja.setX(0);
        pelaaja.setY(0);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.palloKimpoaa(pelaaja);
        assertEquals(Suunta.OIKEA, pallo.getSuuntaX());
        assertTrue(tt.palloKimpoaa(pelaaja));
    }
    
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaVasemmalle() {
        Pallo pallo = k.getPallo();
        pallo.setX(k.getLeveys()- pallo.getHalkaisija() - 30);
        pallo.setY(40);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.YLOS);
        Maila pelaaja = k.getOikeaPelaaja();
        pelaaja.setX(k.getLeveys()-pelaaja.getLeveys());
        pelaaja.setY(0);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.palloKimpoaa(pelaaja);
        assertEquals(Suunta.VASEN, pallo.getSuuntaX());
        assertTrue(tt.palloKimpoaa(pelaaja));
    }
    
    @Test
    public void palloKimpoaaNakyvastaEsteesta() {
        k.lisaaEste();
        Este este = k.getEsteet().get(0);
        este.setNakyvissa(true);
        Pallo pallo = k.getPallo();
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.setX(este.getX()-15);
        pallo.setY(este.getY());
        tt.tarkistaOsuukoPalloEsteeseen();
        assertEquals(Suunta.VASEN, pallo.getSuuntaX());
    }
    
    @Test
    public void josEsteEiNakyvissaPalloMeneeOhi() {
        k.lisaaEste();
        Este este = k.getEsteet().get(0);
        Pallo pallo = k.getPallo();
        pallo.setX(este.getX() + 5);
        pallo.setY(este.getY() + 5);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tarkistaOsuukoPalloEsteeseen();
        assertEquals(Suunta.VASEN, pallo.getSuuntaX());
    }
    
    @Test
    public void palloVasemmanMaalinJalkeenPaikallaan() {
        Pallo pallo = k.getPallo();
        pallo.setX(0);
        pallo.setY(0);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tarkistaMaali();
        assertTrue(pallo.isPaikallaan());
    }
      
    @Test
    public void palloOikeanMaalinJalkeenPaikallaan() {
        Pallo pallo = k.getPallo();
        pallo.setX(k.getLeveys());
        pallo.setY(200);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tarkistaMaali();
        assertTrue(pallo.isPaikallaan());
    }
    
    @Test
    public void palloAsetetaanVasemmanMaalinJalkeenKeskelle() {
        Pallo pallo = k.getPallo();
        pallo.setX(0);
        pallo.setY(0);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tarkistaMaali();
        assertEquals(240, pallo.getX());
        assertEquals(290, pallo.getY());
    }
    
    @Test
    public void palloAsetetaanOikeanMaalinJalkeenKeskelle() {
        Pallo pallo = k.getPallo();
        pallo.setX(k.getLeveys());
        pallo.setY(200);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tarkistaMaali();
        assertEquals(240, pallo.getX());
        assertEquals(290, pallo.getY());
    }
    
    @Test
    public void kunMaaliVasemmallaPalauttaaOikeanPelaajan() {
        Pallo pallo = k.getPallo();
        pallo.setX(1);
        pallo.setY(10);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        assertEquals(Pelaaja.OIKEA, tt.tarkistaMaali());
    }
    
    @Test
    public void kunMaaliOikeallaPalauttaaVasemmanPelaajan() {
        Pallo pallo = k.getPallo();
        pallo.setX(495);
        pallo.setY(590);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        assertEquals(Pelaaja.VASEN, tt.tarkistaMaali());
    }
    
    @Test
    public void josPalloOsuuYlareunaanSuuntaVaihtuu() {
        Pallo pallo = k.getPallo();
        pallo.setX(5);
        pallo.setY(5);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        tt.tormaakoPalloSeinaan();
        assertEquals(Suunta.ALAS, pallo.getSuuntaY());
    }
    
    @Test
    public void josPalloOsuuYlareunaanSeKimpoaa() {
        Pallo pallo = k.getPallo();
        pallo.setX(2);
        pallo.setY(0);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.setSuuntaX(Suunta.OIKEA);
        tt.tormaakoPalloSeinaan();
        assertEquals(3, pallo.getY());
    }

    @Test
    public void josPalloOsuuAlareunaanSeKimpoaa() {
        Pallo pallo = k.getPallo();
        pallo.setX(2);
        pallo.setY(580);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.setSuuntaX(Suunta.OIKEA);
        tt.tormaakoPalloSeinaan();
        assertEquals(577, pallo.getY());
    }

    @Test
    public void josPalloOsuuAlareunaanSuuntaVaihtuu() {
        Pallo pallo = k.getPallo();
        pallo.setX(-1);
        pallo.setY(k.getKorkeus()-1);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        tt.tormaakoPalloSeinaan();
        assertEquals(Suunta.YLOS, pallo.getSuuntaY());
    }    
    
    @Test
    public void josPalloOsuuEsteeseenEsteestaPoistettava() {
        k.lisaaEste();
        Este este = k.getEsteet().get(0);
        este.setNakyvissa(true);
        Pallo pallo = k.getPallo();
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.setX(este.getX()-15);
        pallo.setY(este.getY());
        tt.tarkistaOsuukoPalloEsteeseen();
        assertTrue(este.isPoistettava());
    }
    

}
