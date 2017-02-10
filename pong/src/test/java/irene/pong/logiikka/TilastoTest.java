package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import irene.pong.komponentit.Pelaaja;
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
        tilasto = new Tilasto(kentta);
    }
    
    @Test
    public void lisaaPisteLisaaPisteen() {
        Pelaaja p = new Pelaaja(1);
        tilasto.lisaaPiste(p);
        assertEquals(1, p.getPisteet());
    }
    
    @Test
    public void voittajaPalauttaaNullKunPeliKesken() {
        assertEquals(null, tilasto.voittaja());
    }
    
    @Test
    public void voittajaPalauttaaOikeanVoittajanKun2Voittaa() {
        KomponenttiHallinta kontrolleri = new KomponenttiHallinta(kentta, tilasto);
        Pallo pallo = kentta.getPallo();
        kentta.getPelaaja2().setPisteet(14);
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
        assertEquals(kentta.getPelaaja2(), tilasto.voittaja());
    }
    
    @Test
    public void voittajaPalauttaaOikeanVoittajanKun1Voittaa() {
        KomponenttiHallinta kontrolleri = new KomponenttiHallinta(kentta, tilasto);
        Pallo pallo = kontrolleri.getPallo();
        kentta.getPelaaja1().setPisteet(14);
        pallo.setX(495);
        pallo.setY(550);
        pallo.setSuuntaX(Suunta.OIKEA);
        pallo.setSuuntaY(Suunta.ALAS);
        pallo.liiku();
        pallo.liiku();
        kontrolleri.paivita();
        assertEquals(kentta.getPelaaja1(), tilasto.voittaja());
    }
    
}
