
package irene.pong.logiikka;

import irene.pong.komponentit.Este;
import irene.pong.komponentit.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KomponenttiHallintaTest {
    private KomponenttiHallinta kontrolleri;
    
    @Before
    public void setUp() {
        Kentta kentta = new Kentta();
        kontrolleri = new KomponenttiHallinta(kentta, new Tilasto());
        kontrolleri.alustaKomponentit();
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
    public void palloMaalissaLisaaPisteitaVasemmallaJaPysayttaaPallon() {
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
    public void palloMaalissaLisaaPisteitaOikeallaJaPysayttaaPallon() {
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
        kontrolleri.paivita();
        assertEquals(0, kontrolleri.getTilasto().vasemmanPelaajanPisteet());
        assertEquals(0, kontrolleri.getTilasto().oikeanPelaajanPisteet());
    }
            
    @Test
    public void pallonOsumatKasvavatKimmotessa() {
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
        assertEquals(2, kontrolleri.getPallonOsumat());
    }
    
    
    @Test
    public void maalinJalkeenOdotusaika15() {
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
        assertEquals(15, kontrolleri.getPallonOdotusaika());
    }
    
    @Test
    public void pallonOsumatKasvavatOsumastaMailaan() {
        kontrolleri.setPallonOsumat(0);
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
        assertEquals(1, kontrolleri.getPallonOsumat());
    }
    
    @Test
    public void palloNopeutuu6sumanJalkeen() {
        kontrolleri.setPallonOsumat(6);
        kontrolleri.paivita();
        assertEquals(3.5, kontrolleri.getKentta().getPallo().getNopeus(), 0.0001);
    }
    
    @Test
    public void josPalloOnPaikallaanOdotusAikaVahenee() {
        kontrolleri.getPallo().setPaikallaan(true);
        kontrolleri.setPallonOdotusaika(15);
        kontrolleri.paivita();
        assertEquals(14, kontrolleri.getPallonOdotusaika());
    }
    
    @Test
    public void josPallonOsotusAikaOnNollaPalloLiikkeelle() {
        kontrolleri.setPallonOdotusaika(0);
        kontrolleri.paivita();
        assertFalse(kontrolleri.getPallo().isPaikallaan());
    }
    
    @Test
    public void josPallonosumiaOikeaMaaraEsteitaLisataan() {
        kontrolleri.setPallonOsumat(12);
        kontrolleri.paivita();
        assertEquals(1, kontrolleri.getKentta().getEsteet().size());
    }
    
    @Test 
    public void josEsteitaLiikaaEiLisata() {
        Kentta k = kontrolleri.getKentta();
        for (int i = 0; i < 5; i++) {
            k.lisaaEste();
        }
        kontrolleri.setPallonOsumat(12);
        kontrolleri.paivita();
        assertEquals(5, kontrolleri.getKentta().getEsteet().size());
    }
    
    @Test
    public void esteidenTiheysAinaYli2() {
        kontrolleri.setPallonOsumat(0);
    }
    
    @Test
    public void paivitaAsettaaEsteetNakyviksi() {
        kontrolleri.setEsteidenTiheys(2);
        kontrolleri.setPallonOsumat(12);
        kontrolleri.paivita();
        assertEquals(kontrolleri.getEsteidenTiheys(), 2);
    }
    
    @Test
    public void josEsteenVaroitusAikaNollaEsteNakyviin() {
        kontrolleri.getKentta().lisaaEste();
        kontrolleri.getKentta().getEsteet().get(0).setVaroitusaika(0);
        kontrolleri.setEsteidenTiheys(2);
        kontrolleri.paivita();
        assertTrue(kontrolleri.getKentta().getEsteet().get(0).isNakyvissa());
    }
    
    @Test
    public void vahennaEsteenVaroitusAikaaPaivittaessa() {
        kontrolleri.getKentta().lisaaEste();
        kontrolleri.getKentta().getEsteet().get(0).setVaroitusaika(2);
        kontrolleri.setEsteidenTiheys(2);
        kontrolleri.paivita();
        assertEquals(kontrolleri.getKentta().getEsteet().get(0).getVaroitusaika(), 1);
    }
    
    @Test
    public void josEsteidenTiheysSuurempiKuinKaksiVahennetaanPaivitettaessa() {
        kontrolleri.setPallonOsumat(6);
        kontrolleri.setEsteidenTiheys(3);
        kontrolleri.paivita();
        assertEquals(2, kontrolleri.getEsteidenTiheys());
    }
}
