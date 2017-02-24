package irene.pong.logiikka;

import irene.pong.komponentit.Este;
import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PallonTormaystentarkistajaTest {
    private PallonTormaystenTarkistaja tt;
    private Kentta k;
    private KomponenttiHallinta kontrolleri;
        
    @Before
    public void setUp() {
        k = new Kentta();
        tt = new PallonTormaystenTarkistaja(k);
        kontrolleri = new KomponenttiHallinta(k, new Tilasto());
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
        tt.palloTormaa(pelaaja);
        assertEquals(Suunta.OIKEA, pallo.getSuuntaX());
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
        tt.palloTormaa(pelaaja);
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
    public void palloMaalinJalkeenPaikallaan() {
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
    public void palloAsetetaanMaalinJalkeenKeskelle() {
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
}
