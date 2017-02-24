package irene.pong.logiikka;

import irene.pong.komponentit.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class KenttaRajaTarkastajaTest {
    private Kentta k;
    private KenttaRajaTarkistaja kt;
    
    @Before
    public void setUp() {
        k = new Kentta();
        kt = new KenttaRajaTarkistaja(k);
    }
    
    @Test
    public void oikeaMailaEiMeneReunanYliYlhaalla() {
        Maila maila = k.getOikeaPelaaja();
        maila.setX(0);
        maila.setY(1);
        maila.setKiihdytaYlos(true);
        maila.liiku();
        kt.tarkistaOsuukoKentanReunaan(maila);
        assertEquals(0, maila.getY());
    }

    @Test
    public void vasenMailaEiMeneReunanYliAlhaalla() {
        Maila maila = k.getVasenPelaaja();
        maila.setX(0);
        maila.setY(k.getLeveys());
        maila.setKiihdytaAlas(true);
        kt.tarkistaOsuukoKentanReunaan(maila);
        assertEquals(600-maila.getKorkeus(), maila.getY());
    }
    
}
