
package irene.pong.logiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KomponenttiHallintaTest {
    private KomponenttiHallinta kontrolleri;
    
    @Before
    public void setUp() {
        Kentta kentta = new Kentta();
        kontrolleri = new KomponenttiHallinta(kentta, new Tilasto());
    }
    
    @Test
    public void paivitaLiikuttaaPalloa() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(0);
        pallo.setY(0);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        kontrolleri.paivita();
        assertEquals(3, pallo.getX());
        assertEquals(3, pallo.getY());
    }
    
    @Test
    public void paivitaLiikuttaaMailoja() {
        Maila oikea = kontrolleri.getKentta().getVasenPelaaja();
        oikea.setX(480);
        oikea.setY(0);
        oikea.setKiihdytaAlas(true);
        Maila vasen = kontrolleri.getKentta().getVasenPelaaja();
        vasen.setX(0);
        vasen.setY(0);
        vasen.setKiihdytaAlas(true);
        kontrolleri.paivita();
        assertEquals(5, vasen.getY());
        assertEquals(5, oikea.getY());
    }
    
    @Test
    public void palloKeskellaAlussa() {
        kontrolleri.alustaKomponentit();
        assertEquals(240, kontrolleri.getPallo().getX());
        assertEquals(290, kontrolleri.getPallo().getY());
    }
     
    @Test
    public void pelaajatKeskellaAlussa() {
        kontrolleri.alustaKomponentit();
        assertEquals(0, kontrolleri.getMaila1().getX());
        assertEquals(480, kontrolleri.getMaila2().getX());
        assertEquals(250, kontrolleri.getMaila1().getY());
        assertEquals(250, kontrolleri.getMaila2().getY());
    }
    
    @Test
    public void josPalloOsuuYlareunaanSuuntaVaihtuu() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(5);
        pallo.setY(5);
        pallo.setSuuntaY(Suunta.YLOS);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(Suunta.ALAS, pallo.getSuuntaY());
    }
    
    @Test
    public void josPalloOsuuAlareunaanSuuntaVaihtuu() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(-1);
        pallo.setY(kontrolleri.getKentta().getKorkeus()-1);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(Suunta.YLOS, pallo.getSuuntaY());
    }    
    
    @Test
    public void palloMaalissaLisaaPisteitaVasemmalla() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(1);
        pallo.setY(1);
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
        kontrolleri.paivita();
        assertEquals(1, kontrolleri.getTilasto().oikeanPelaajanPisteet());
    }
    
    @Test
    public void palloMaalissaLisaaPisteitaOikealla() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(495);
        pallo.setY(550);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(1, kontrolleri.getTilasto().vasemmanPelaajanPisteet());
    }
    
    @Test
    public void tarkistaMaaliEiLisaaPisteitaTurhaan() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(5);
        pallo.setY(5);
        pallo.setSuuntaX(Suunta.ALAS);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        kontrolleri.tarkistaMaali();
        assertEquals(0, kontrolleri.getTilasto().vasemmanPelaajanPisteet());
        assertEquals(0, kontrolleri.getTilasto().oikeanPelaajanPisteet());
    }
    
    @Test
    public void palloKeskelleMaalinJalkeen() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(495);
        pallo.setY(550);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(240, kontrolleri.getPallo().getX());
        assertEquals(290, kontrolleri.getPallo().getY());
    }
    
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaOikealle() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(40);
        pallo.setY(40);
        pallo.setSuuntaX(Suunta.VASEN);
        pallo.setSuuntaY(Suunta.YLOS);
        Maila pelaaja = kontrolleri.getMaila1();
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
        assertEquals(Suunta.OIKEA, pallo.getSuuntaX());
    }
    
    @Test
    public void josPalloOsuuMailaanPalloKimpoaaVasemmalle() {
        Pallo pallo = kontrolleri.getPallo();
        pallo.setX(kontrolleri.getKentta().getLeveys()- pallo.getHalkaisija() - 30);
        pallo.setY(40);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.YLOS);
        Maila pelaaja = kontrolleri.getMaila2();
        pelaaja.setX(kontrolleri.getKentta().getLeveys()-pelaaja.getLeveys());
        pelaaja.setY(0);
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        kontrolleri.paivita();
        assertEquals(Suunta.VASEN, pallo.getSuuntaX());
    }
   
    @Test
    public void palloAsetetaanMaalinJalkeenKeskelle() {
        Pallo pallo = kontrolleri.getPallo();
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
        kontrolleri.paivita();
        assertEquals(240, pallo.getX());
        assertEquals(290, pallo.getY());
    }
    
    @Test
    public void mailaEiMeneReunanYliYlhaalla() {
        Maila maila = kontrolleri.getMaila1();
        maila.setX(0);
        maila.setY(1);
        maila.setKiihdytaYlos(true);
        kontrolleri.paivita();
        assertEquals(0, maila.getY());
    }
    
    @Test
    public void mailaEiMeneReunanYliAlhaalla() {
        Maila maila = kontrolleri.getMaila1();
        maila.setX(0);
        maila.setY(kontrolleri.getKentta().getLeveys());
        maila.setKiihdytaAlas(true);
        kontrolleri.paivita();
        assertEquals(600-maila.getKorkeus(), maila.getY());
    }
}
