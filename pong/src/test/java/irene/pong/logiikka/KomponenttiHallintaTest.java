
package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class KomponenttiHallintaTest {
    private KomponenttiHallinta kontrolleri;
    
    @Before
    public void setUp() {
        Kentta kentta = new Kentta();
        kentta.setSize(500, 600);
        kentta.setVisible(true);
        kontrolleri = new KomponenttiHallinta(kentta);
    }
    
    @Test
    public void palloKeskellaAlussa() {
        kontrolleri.aloita();
        assertEquals(240, kontrolleri.getPallo().getX());
        assertEquals(290, kontrolleri.getPallo().getY());
    }
     
    @Test
    public void pelaajatKeskellaAlussa() {
        kontrolleri.aloita();
        assertEquals(0, kontrolleri.getPelaaja1().getX());
        assertEquals(480, kontrolleri.getPelaaja2().getX());
        assertEquals(250, kontrolleri.getPelaaja1().getY());
        assertEquals(250, kontrolleri.getPelaaja2().getY());
    }
    
    @Test
    public void josPalloOsuuYlareunaanSuuntaVaihtuu() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(5);
        pallo.setY(5);
        pallo.setSuuntaY(-1);
        pallo.setSuuntaX(1);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(1, pallo.getSuuntaY());
    }
    
    @Test
    public void josPalloOsuuAlareunaanSuuntaVaihtuu() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(-1);
        pallo.setY(kontrolleri.getKentta().getHeight()-1);
        pallo.setSuuntaY(1);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(-1, pallo.getSuuntaY());
    }    
    
    @Test
    public void palloMaalissaLisaaPisteitaVasemmalla() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(1);
        pallo.setY(1);
        pallo.setSuuntaX(-1);
        pallo.setSuuntaY(-1);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        Maila pelaaja2 = kontrolleri.getPelaaja2();
        assertEquals(1, pelaaja2.getPisteet());
    }
    
    @Test
    public void palloMaalissaLisaaPisteitaOikealla() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(495);
        pallo.setY(550);
        pallo.setSuuntaX(1);
        pallo.setSuuntaY(1);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        Maila pelaaja1 = kontrolleri.getPelaaja1();
        assertEquals(1, pelaaja1.getPisteet());
    }
    
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaOikealle() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(40);
        pallo.setY(40);
        pallo.setSuuntaX(-1);
        pallo.setSuuntaY(-1);
        Maila pelaaja = kontrolleri.getPelaaja1();
        pelaaja.setX(0);
        pelaaja.setY(0);
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        assertEquals(1, pallo.getSuuntaX());
    }
    
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaVasemmalle() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(kontrolleri.getKentta().getWidth() - pallo.getHalkaisija() - 30);
        pallo.setY(40);
        pallo.setSuuntaX(1);
        pallo.setSuuntaY(-1);
        Maila pelaaja = kontrolleri.getPelaaja2();
        pelaaja.setX(kontrolleri.getKentta().getWidth()-pelaaja.getLeveys());
        pelaaja.setY(0);
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        assertEquals(-1, pallo.getSuuntaX());
    }
   
    @Test
    public void palloAsetetaanMaalinJalkeenKeskelle() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(0);
        pallo.setY(0);
        pallo.setSuuntaX(-1);
        pallo.setSuuntaY(-1);
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(240, pallo.getX());
        assertEquals(290, pallo.getY());
    }
    
    @Test
    public void mailaEiMeneReunanYliYlhaalla() {
        Maila maila = kontrolleri.getPelaaja1();
        maila.setX(0);
        maila.setY(1);
        maila.setKiihdytaYlos(true);
        kontrolleri.paivita();
        assertEquals(0, maila.getY());
    }
    
    @Test
    public void mailaEiMeneReunanYliAlhaalla() {
        Maila maila = kontrolleri.getPelaaja1();
        maila.setX(0);
        maila.setY(kontrolleri.getKentta().getWidth());
        maila.setKiihdytaAlas(true);
        kontrolleri.paivita();
        assertEquals(600-maila.getKorkeus(), maila.getY());
    }
}
