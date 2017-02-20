/**
 * Luokka piirtää pelikentän sisällön.
 */

package irene.pong.kayttoliittyma;

import irene.pong.komponentit.Este;
import irene.pong.logiikka.Paivitettava;
import irene.pong.logiikka.Pong;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Piirturi extends JPanel implements Paivitettava {
    private final Pong logiikka;
    private final Random random;
    
    public Piirturi(Pong pong) {
        /**
         * @param pong peli, jonka kentän sisältö ja pisteet luokan tulee piirtää.
         */
        logiikka = pong;
        random = new Random();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.setColor(Color.LIGHT_GRAY);
        
        g.setFont(new Font("Arial", Font.BOLD, 50)); 
        g.drawString(Integer.toString(logiikka.getTilasto().vasemmanPelaajanPisteet()), 50, 50);
        g.drawString(Integer.toString(logiikka.getTilasto().oikeanPelaajanPisteet()), getWidth() - 100, 50);
                
        logiikka.getKentta().getEsteet().stream().forEach((este) -> {
            if (!este.isNakyvissa()) {
                g.setColor(Color.DARK_GRAY);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(este.getX(), este.getY(), este.getLeveys(), este.getKorkeus());
        });

        g.setColor(Color.WHITE);        
        
        g.fillRect(logiikka.getKentta().getVasenPelaaja().getX(), logiikka.getKentta().getVasenPelaaja().getY(), logiikka.getKentta().getVasenPelaaja().getLeveys(), logiikka.getKentta().getVasenPelaaja().getKorkeus());
        g.fillRect(logiikka.getKentta().getOikeaPelaaja().getX(), logiikka.getKentta().getOikeaPelaaja().getY(), logiikka.getKentta().getOikeaPelaaja().getLeveys(), logiikka.getKentta().getOikeaPelaaja().getKorkeus());
        
        g.fillOval(logiikka.getKentta().getPallo().getX(), logiikka.getKentta().getPallo().getY(), logiikka.getKentta().getPallo().getHalkaisija(), logiikka.getKentta().getPallo().getHalkaisija());

        getToolkit().sync();
    }    

    @Override
    public void paivita() {
        this.repaint();
    }
    
}
