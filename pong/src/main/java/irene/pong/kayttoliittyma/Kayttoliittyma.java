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
import irene.pong.logiikka.Suunta;
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
    private JLabel ilmoitus;
    private JLabel pause;
    private Pong logiikka;
    private Piirturi piirturi;
    private final Animoija animoija;
    
    /**
     * Luo kaikki käyttöliittymän komponentit, ja asettaa itsensä päälogiikkaluokan päivitettävä-olioksi.
     * 
     * @param pong päälogiikkaluokka Pong
     * 
     * @see Pong
     * @see Piirturi 
     */
    public Kayttoliittyma(Pong pong) {
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
    
    public Piirturi getPiirturi() {
        return piirturi;
    }
    
    /**
     * Asettaa kentän yläreunan ilmoitukseen tekstin.
     * @param ilmo Ilmoituksen teksti.
     */
    public void asetaIlmoitus(String ilmo) {
        ilmoitus.setText(ilmo);
    }
    
    /**
     * Poistaa pelikentän yläreunassa sijaitsevan ilmoituksen.
     */
    public void tyhjennaIlmoitus() {
        ilmoitus.setText("");
    }
    /**
     * Asettaa ohjeen pelin pausettamisesta näkyville.
     */    
    public void pauseNakyvaksi() {
        pause.setVisible(true);
    }
    
    /**
     * Piilottaa ohjeen pelin pausettamisesta.
     */
    public void pausePiiloon() {
        pause.setVisible(false);
    }
    
    /**
     * Alustaa JFrame-olion stabiilin kokoiseksi, ja antaa sille otsikon.
     */
    public void alustaIkkuna() {
        ikkuna.setPreferredSize(new Dimension(logiikka.getKentta().getLeveys(), logiikka.getKentta().getKorkeus()));
        ikkuna.setTitle("Pong");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);        
    }
    
    /**
     * Alustaa paikan pelikentän yläreunassa näkyville ilmoituksille.
     */
    public void alustaIlmoitus() {        
        ilmoitus.setFont(new Font("", Font.PLAIN, 20)); //Fontin nimen muuttaminen ei vaikuta
        ilmoitus.setHorizontalAlignment(SwingConstants.CENTER);  
        ilmoitus.setVerticalAlignment(SwingConstants.NORTH);
        ilmoitus.setForeground(Color.WHITE);
        
    }
    
    /**
     * Alustaa ohjeet pelin pausettamisesta.
     */
    public void alustaPauseNappi() {
        pause.setHorizontalAlignment(SwingConstants.RIGHT);
        pause.setForeground(Color.WHITE);  
        pause.setVisible(false);
    }  
    
    /**
     * Tekee piirturista kentän kokoisen, lisää piirturiin ilmoituksen ja pauseohjeen, sekä lisää piirturin JFrame-olioon.
     * 
     * @see Piirturi
     */
    public void alustaPiirturi() {
        piirturi.setPreferredSize(new Dimension(logiikka.getKentta().getLeveys(), logiikka.getKentta().getKorkeus()));
        piirturi.setBackground(Color.BLACK);
        piirturi.add(ilmoitus, BorderLayout.CENTER);
        piirturi.add(pause, BorderLayout.SOUTH);
        ikkuna.add(piirturi);        
    }

    /**
     * Lisää KeyBindingsit, jotka kuuntelevat pelityypin valitsemiseen, pelin aloittamiseen ja pausettamiseen käytetyt näppäimet.
     */
    public void lisaaKayttoLiittymaNappaimet() {
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed P"), "P painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACE painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 1"), "1 painettu");
        piirturi.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 2"), "2 painettu");

        piirturi.getActionMap().put("P painettu", new PauseNappi(logiikka, this));
        piirturi.getActionMap().put("SPACE painettu", new AloitusPainallus(logiikka, this));
        piirturi.getActionMap().put("1 painettu", new PelinValinta(logiikka, "1", this));
        piirturi.getActionMap().put("2 painettu", new PelinValinta(logiikka, "2", this));
    }

    /**
     * Lisää KeyBindingsit, joiden avulla pelaaja tai pelaajat voivat ohjata mailoja riippuen pelityypistä.
     * Jos pelityyppi on yksinpeli, vasemmanpuoleisen pelaajan näppäimiä ei kuunnella.
     * 
     * @param tyyppi käyttäjän valitsema pelityyppi(yksin- tai kaksinpeli)
     */
    public void lisaaPelaajienNappaimet(Pelityyppi tyyppi) {
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
        if (logiikka.isPeliKaynnissa() && logiikka.getKentta().getPallo().isPaikallaan()) {
            if (logiikka.getKentta().getPallo().getSuuntaX() == Suunta.OIKEA) {
                logiikka.getKontrolleri().setPalloMerkkiX(logiikka.getKentta().getLeveys()-30);
            } else {
                logiikka.getKontrolleri().setPalloMerkkiX(30);
            }
            if (logiikka.getKentta().getPallo().getSuuntaY() == Suunta.YLOS) {
                logiikka.getKontrolleri().setPalloMerkkiY(0);
            } else {
                logiikka.getKontrolleri().setPalloMerkkiY(logiikka.getKentta().getKorkeus()-logiikka.getKentta().getPallo().getHalkaisija()-10);
            }
        } else {
            logiikka.getKontrolleri().setPalloMerkkiX(-1);
            logiikka.getKontrolleri().setPalloMerkkiY(-1);
        }
        if (logiikka.getTilasto().voittaja() != null && !logiikka.isPeliKaynnissa() && logiikka.isPeliAlkanut()) {
            String voittaja = "";
            if (logiikka.getTilasto().voittaja() == Pelaaja.VASEN && logiikka.getPelityyppi() == Pelityyppi.YKSINPELI) {
                voittaja = "AI";
            } else if (logiikka.getTilasto().voittaja() == Pelaaja.VASEN && logiikka.getPelityyppi() == Pelityyppi.KAKSINPELI) {
                voittaja = "Player 1";
            } else if (logiikka.getTilasto().voittaja() == Pelaaja.OIKEA) {
                voittaja = "Player 2";
            }
            asetaIlmoitus("<html><div style='text-align: center;'> <strong> " + voittaja + " wins </strong> <br> Press SPACE to restart <br> or close the window </div></html>");            
        } else if (!logiikka.isPeliAlkanut() && !logiikka.isPeliKaynnissa() && logiikka.getPelityyppi() != null) {
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
        } else if (!logiikka.isPeliAlkanut() && !logiikka.isPeliKaynnissa() && logiikka.getPelityyppi() == null && logiikka.getTilasto().voittaja() == null) {
            asetaIlmoitus("<html><div style='text-align: center;'> <strong> PONG </strong> <p>Choose game type: </p><p> 1 - single <br> 2 - multiplayer </p> </div></html>");
        }
    }            
}
