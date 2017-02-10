/**
 * Ikkuna luo varsinaiset komponentit sisältävän luokan Kenttä, sekä siinä ilmestyvät ilmoitukset. 
 * Luokka tarjoaa mahdollisuuden ilmoitusten tekstin muuttamiseen Kenttä-luokan kautta. 
 * 
 * @see grafiikka.Kentta
 */

package irene.pong.grafiikka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ikkuna extends JFrame { //hyvä lähtökohta pääUI-luokalle

    private final int leveys = 500;
    private final int korkeus = 600;
    private final Kentta kentta;
    private final JLabel ilmoitus;
    private final JLabel pause;
    

    public Ikkuna() {
        kentta = new Kentta();
        kentta.setLayout(new BorderLayout());
        ilmoitus = new JLabel();
        pause = new JLabel("p - pause");
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public Kentta getKentta() {
        return kentta;
    }
    
    public void asetaIlmoitus(String ilmo) {
        ilmoitus.setText(ilmo);
    }
    
    public void pauseNakyvaksi() {
        pause.setVisible(true);
    }
    
    public void pausePiiloon() {
        pause.setVisible(false);
    }
    
    public void alusta() {
        setSize(leveys, korkeus);
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        alustaIlmoitus();
        alustaPauseNappi();
        
        kentta.setBackground(Color.BLACK);
        kentta.add(ilmoitus, BorderLayout.CENTER);
        kentta.add(pause, BorderLayout.SOUTH);
        add(kentta);
        setVisible(true);
        
//        animoija.aloitaPeli();
    }
    
    public void alustaIlmoitus() {        
        ilmoitus.setFont(new Font("", Font.PLAIN, 20)); //Fontin nimen muuttaminen ei vaikuta
        ilmoitus.setHorizontalAlignment(SwingConstants.CENTER);  
        ilmoitus.setVerticalAlignment(SwingConstants.NORTH);
        ilmoitus.setForeground(Color.WHITE);
        
    }
    
    public void alustaPauseNappi() {
        pause.setHorizontalAlignment(SwingConstants.RIGHT);
        pause.setForeground(Color.WHITE);  
        pause.setVisible(false);

    }  
        
}
