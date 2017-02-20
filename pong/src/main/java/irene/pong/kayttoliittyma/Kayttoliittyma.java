/**
 * Pääkäyttöliittymäluokka, joka luo käyttöliittymän osat, sekä saa konstruktorissa parametrina pelin logiikan. 
 * 
 */

package irene.pong.kayttoliittyma;

import irene.pong.logiikka.Paivitettava;
import irene.pong.logiikka.Pelaaja;
import irene.pong.painallukset.AloitusPainallus;
import irene.pong.painallukset.PainallusAlas;
import irene.pong.painallukset.PainallusYlos;
import irene.pong.painallukset.PauseNappi;
import irene.pong.painallukset.PelinValinta;
import irene.pong.painallukset.VapautusAlas;
import irene.pong.painallukset.VapautusYlos;
import irene.pong.logiikka.Pelityyppi;
import irene.pong.logiikka.Pong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Kayttoliittyma implements Runnable, Paivitettava {

    private JFrame ikkuna;
//    private final Kentta kentta;
    private JLabel ilmoitus;
    private JLabel pause;
    private Pong logiikka;
    private Piirturi piirturi;
    private final Animoija animoija;
    

    public Kayttoliittyma(Pong pong) {
        /**
         * Luo kaikki käyttöliittymän komponentit, ja asettaa itsensä päälogiikkaluokan päivitettävä-olioksi.
         * 
         * @param pong päälogiikkaluokka Pong
         * 
         * @see Pong
         * @see Piirturi 
         */
        TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
        logiikka = pong;
        ikkuna = new JFrame();
        piirturi = new Piirturi(pong);
        piirturi.setLayout(new BorderLayout());
        ilmoitus = new JLabel();
        pause = new JLabel("p - pause");
        animoija = new Animoija(logiikka);
        logiikka.setLiikePaivitettava(piirturi);
        logiikka.setTilannePaivitettava(this);
    }
    
    public Pong getLogiikka() {
        return logiikka;
    }
    
    public Animoija getAnimoija() {
        return animoija;
    }
    
    public void asetaIlmoitus(String ilmo) {
        ilmoitus.setText(ilmo);
    }
    
    public void tyhjennaIlmoitus() {
        /**
         * Poistaa pelikentän yläreunassa sijaitsevan ilmoituksen.
         */
        ilmoitus.setText("");
    }
    
    public void pauseNakyvaksi() {
        /**
         * Asettaa ohjeen pelin pausettamisesta näkyville.
         */
        pause.setVisible(true);
    }
    
    public void pausePiiloon() {
        /**
         * Piilottaa ohjeen pelin pausettamisesta.
         */
        pause.setVisible(false);
    }
    
    public void alustaIkkuna() {
        /**
         * Alustaa JFrame-olion stabiilin kokoiseksi, ja antaa sille otsikon.
         */
        ikkuna.setPreferredSize(new Dimension(logiikka.getKentta().getLeveys(), logiikka.getKentta().getKorkeus()));
        ikkuna.setTitle("Pong");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);        
    }
    
    public void alustaIlmoitus() {        
        /**
         * Alustaa paikan pelikentän yläreunassa näkyville ilmoituksille.
         */
        ilmoitus.setFont(new Font("", Font.PLAIN, 20)); //Fontin nimen muuttaminen ei vaikuta
        ilmoitus.setHorizontalAlignment(SwingConstants.CENTER);  
        ilmoitus.setVerticalAlignment(SwingConstants.NORTH);
        ilmoitus.setForeground(Color.WHITE);
        
    }
    
    public void alustaPauseNappi() {
        /**
         * Alustaa ohjeet pelin pausettamisesta.
         */
        pause.setHorizontalAlignment(SwingConstants.RIGHT);
        pause.setForeground(Color.WHITE);  
        pause.setVisible(false);
    }  
    
    public void alustaPiirturi() {
        /**
         * Tekee piirturista kentän kokoisen, lisää piirturiin ilmoituksen ja pauseohjeen, sekä lisää piirturin JFrame-olioon.
         * 
         * @see Piirturi
         */
        piirturi.setPreferredSize(new Dimension(logiikka.getKentta().getLeveys(), logiikka.getKentta().getKorkeus()));
        piirturi.setBackground(Color.BLACK);
        piirturi.add(ilmoitus, BorderLayout.CENTER);
        piirturi.add(pause, BorderLayout.SOUTH);
        ikkuna.add(piirturi);        
    }

    public void lisaaKayttoLiittymaNappaimet() {
        /**
         * Lisää KeyBindingsit, jotka kuuntelevat pelityypin valitsemiseen, pelin aloittamiseen ja pausettamiseen käytetyt näppäimet.
         */
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed P"), "P painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACE painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 1"), "1 painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 2"), "2 painettu");

        piirturi.getActionMap().put("P painettu", new PauseNappi(logiikka, this));
        piirturi.getActionMap().put("SPACE painettu", new AloitusPainallus(logiikka, this));
        piirturi.getActionMap().put("1 painettu", new PelinValinta(logiikka, "1", this));
        piirturi.getActionMap().put("2 painettu", new PelinValinta(logiikka, "2", this));
    }

    public void lisaaPelaajienNappaimet(Pelityyppi tyyppi) {
        /**
         * Lisää KeyBindingsit, joiden avulla pelaaja tai pelaajat voivat ohjata mailoja riippuen pelityypistä.
         * Jos pelityyppi on yksinpeli, vasemmanpuoleisen pelaajan näppäimiä ei kuunnella.
         * 
         * @param Pelityyppi käyttäjän valitsema pelityyppi(yksin- tai kaksinpeli)
         */

        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "UP painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "UP vapautettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "DOWN vapautettu");

        piirturi.getActionMap().put("UP painettu", new PainallusYlos(logiikka.getKontrolleri(), "UP"));
        piirturi.getActionMap().put("UP vapautettu", new VapautusYlos(logiikka.getKontrolleri(), "UP"));
        piirturi.getActionMap().put("DOWN painettu", new PainallusAlas(logiikka.getKontrolleri(), "DOWN"));
        piirturi.getActionMap().put("DOWN vapautettu", new VapautusAlas(logiikka.getKontrolleri(), "DOWN"));

        if (tyyppi == Pelityyppi.KAKSINPELI) {
            piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "W painettu");
            piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "W vapautettu");
            piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "S painettu");
            piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "S vapautettu");

            piirturi.getActionMap().put("W painettu", new PainallusYlos(logiikka.getKontrolleri(), "W"));
            piirturi.getActionMap().put("W vapautettu", new VapautusYlos(logiikka.getKontrolleri(), "W"));
            piirturi.getActionMap().put("S painettu", new PainallusAlas(logiikka.getKontrolleri(), "S"));
            piirturi.getActionMap().put("S vapautettu", new VapautusAlas(logiikka.getKontrolleri(), "S"));

        }

    }
    
    @Override
    public void run() {
        alustaIkkuna();
        alustaIlmoitus();
        alustaPauseNappi();
        alustaPiirturi();       
        ikkuna.pack();
        ikkuna.setVisible(true);
        
        lisaaKayttoLiittymaNappaimet();

        logiikka.aloita();
    }

    @Override
    public void paivita() {
        /**
         * Asettaa kentän yläosan ilmoitukseen tekstin riippuen pelin tilasta.
         * Jos peli on vasta aloitettu, antaa ohjeet pelityypin valitsemiseksi, sitten ohjeistaa pelin aloittamisen ja pelin loputtua kertoo voittajan.
         */
        
        if (logiikka.getTilasto().voittaja() != null && !logiikka.isPeliKaynnissa() && logiikka.peliAlkanut()) {
            String voittaja = "";
            if (logiikka.getTilasto().voittaja() == Pelaaja.VASEN && logiikka.getPelityyppi() == Pelityyppi.YKSINPELI) {
                voittaja = "AI";
            } else if (logiikka.getTilasto().voittaja() == Pelaaja.VASEN && logiikka.getPelityyppi() == Pelityyppi.KAKSINPELI) {
                voittaja = "Player 1";
            } else if (logiikka.getTilasto().voittaja() == Pelaaja.OIKEA) {
                voittaja = "Player 2";
            }
            asetaIlmoitus("<html><div style='text-align: center;'> <strong> " + voittaja + " wins </strong> <br> Press SPACE to restart <br> or close the window </div></html>");
            animoija.getAnimoija().stop();
            
        } else if (!logiikka.peliAlkanut() && !logiikka.isPeliKaynnissa() && logiikka.getPelityyppi() != null) {
            String pelityyppi = "";
            String ohjeet = "";
            if (logiikka.getPelityyppi() == Pelityyppi.YKSINPELI) {
                pelityyppi = "single"; 
                ohjeet = "Use the arrow keys";
            } else if (logiikka.getPelityyppi() == Pelityyppi.KAKSINPELI) {
                pelityyppi = "multiplayer";
                ohjeet = "Player 1: W - S <br> Player 2: Arrow keys";
            }
            asetaIlmoitus("<html><div style='text-align: center;'> <p> You chose " + pelityyppi + "</p> <p>  " + ohjeet + " </p> <p> Press SPACE to start </p></div></html>");
        } else if (!logiikka.peliAlkanut() && !logiikka.isPeliKaynnissa() && logiikka.getPelityyppi() == null && logiikka.getTilasto().voittaja() == null) {
            asetaIlmoitus("<html><div style='text-align: center;'> <strong> PONG </strong> <p> 1 - single <br> 2 - multiplayer </p> </div></html>");
        }
        
    }
            
}
