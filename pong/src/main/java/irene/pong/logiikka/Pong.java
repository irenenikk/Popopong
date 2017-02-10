/**
 * Pelin tarvittavat osat luova luokka, joka myös säilyttää tiedon käyttäjän valitsemasta pelityypistä.
 * Asettaa näppäimet kuunneltaviksi ja aloittaa pelin.
 */
package irene.pong.logiikka;

import irene.pong.grafiikka.Animoija;
import irene.pong.grafiikka.Ikkuna;
import irene.pong.kayttoliittyma.AloitusPainallus;
import irene.pong.kayttoliittyma.PainallusAlas;
import irene.pong.kayttoliittyma.PainallusYlos;
import irene.pong.kayttoliittyma.PauseNappi;
import irene.pong.kayttoliittyma.PelinValinta;
import irene.pong.kayttoliittyma.VapautusAlas;
import irene.pong.kayttoliittyma.VapautusYlos;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.KeyStroke;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Pong { //ei yhtä luokkaa, joka luo sekä UI:n että logiikan elementtejä. Tulisi muuttaa logiikan pääluokaksi.

    Ikkuna ikkuna;
    Animoija animoija;
    Tilasto tilasto;
    KomponenttiHallinta kontrolleri;
    Pelityyppi pelityyppi;

    public Pong() {
        ikkuna = new Ikkuna();
        tilasto = new Tilasto(ikkuna.getKentta());
        TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
    }

    public Ikkuna getIkkuna() {
        return ikkuna;
    }

    public void setIkkuna(Ikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }

    public Pelityyppi getPelityyppi() {
        return pelityyppi;
    }

    public Animoija getAnimoija() {
        return animoija;
    }

    public KomponenttiHallinta getKontrolleri() {
        return kontrolleri;
    }

    public void setPelityyppi(Pelityyppi pelityyppi) {
        this.pelityyppi = pelityyppi;
        lisaaPelaajienNappaimet(pelityyppi);
        if (pelityyppi == Pelityyppi.KAKSINPELI) {
            ikkuna.asetaIlmoitus("<html><div style='text-align: center;'> Valitsit kaksinpelin <br> Paina SPACE aloittaaksesi </div></html>");
        } else if (pelityyppi == Pelityyppi.YKSINPELI) {
            ikkuna.asetaIlmoitus("<html><div style='text-align: center;'> Valitsit yksinpelin (tekoäly) <br> Paina SPACE aloittaaksesi </div></html");
        }
    }

    public void aloita() {
        ikkuna.alusta();
        kontrolleri = new KomponenttiHallinta(ikkuna.getKentta(), tilasto);
        kontrolleri.alustaKomponentit();
        animoija = new Animoija(this);
        lisaaKayttoLiittymaNappaimet();
        ikkuna.asetaIlmoitus("<html><div style='text-align: center;'> Tervetuloa Pong-peliin!<br>Valitse pelityyppi: <br> 1 - yksinpeli <br> 2 - kaksinpeli </div></html>");
//        animoija.aloitaPeli();
    }

    public void lisaaKayttoLiittymaNappaimet() {
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed P"), "P painettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACE painettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 1"), "1 painettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 2"), "2 painettu");

        ikkuna.getKentta().getActionMap().put("P painettu", new PauseNappi(animoija, ikkuna));
        ikkuna.getKentta().getActionMap().put("SPACE painettu", new AloitusPainallus(animoija, ikkuna));
        ikkuna.getKentta().getActionMap().put("1 painettu", new PelinValinta(this, "1"));
        ikkuna.getKentta().getActionMap().put("2 painettu", new PelinValinta(this, "2"));
    }

    public void lisaaPelaajienNappaimet(Pelityyppi tyyppi) {

        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "UP painettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "UP vapautettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN painettu");
        ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "DOWN vapautettu");

        ikkuna.getKentta().getActionMap().put("UP painettu", new PainallusYlos(kontrolleri, "UP"));
        ikkuna.getKentta().getActionMap().put("UP vapautettu", new VapautusYlos(kontrolleri, "UP"));
        ikkuna.getKentta().getActionMap().put("DOWN painettu", new PainallusAlas(kontrolleri, "DOWN"));
        ikkuna.getKentta().getActionMap().put("DOWN vapautettu", new VapautusAlas(kontrolleri, "DOWN"));

        if (tyyppi == Pelityyppi.KAKSINPELI) {
            ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "W painettu");
            ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "W vapautettu");
            ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "S painettu");
            ikkuna.getKentta().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "S vapautettu");

            ikkuna.getKentta().getActionMap().put("W painettu", new PainallusYlos(kontrolleri, "W"));
            ikkuna.getKentta().getActionMap().put("W vapautettu", new VapautusYlos(kontrolleri, "W"));
            ikkuna.getKentta().getActionMap().put("S painettu", new PainallusAlas(kontrolleri, "S"));
            ikkuna.getKentta().getActionMap().put("S vapautettu", new VapautusAlas(kontrolleri, "S"));

        }

    }
}
