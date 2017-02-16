/**
 * Luokka piirtää pelikentän sisällön.
 */

package irene.pong.kayttoliittyma;

import irene.pong.logiikka.Paivitettava;
import irene.pong.logiikka.Pong;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirturi extends JPanel implements Paivitettava {
    private final Pong logiikka;
    
    public Piirturi(Pong pong) {
        /**
         * @param pong peli, jonka kentän sisältö ja pisteet luokan tulee piirtää.
         */
        logiikka = pong;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 50)); 
        g.drawString(Integer.toString(logiikka.getTilasto().vasemmanPelaajanPisteet()), 50, 50);
        g.drawString(Integer.toString(logiikka.getTilasto().oikeanPelaajanPisteet()), getWidth() - 100, 50);
        
        g.setColor(Color.WHITE);

        g.fillOval(logiikka.getKentta().getPallo().getX(), logiikka.getKentta().getPallo().getY(), logiikka.getKentta().getPallo().getHalkaisija(), logiikka.getKentta().getPallo().getHalkaisija());
        g.fillRect(logiikka.getKentta().getVasenPelaaja().getX(), logiikka.getKentta().getVasenPelaaja().getY(), logiikka.getKentta().getVasenPelaaja().getLeveys(), logiikka.getKentta().getVasenPelaaja().getKorkeus());
        g.fillRect(logiikka.getKentta().getOikeaPelaaja().getX(), logiikka.getKentta().getOikeaPelaaja().getY(), logiikka.getKentta().getOikeaPelaaja().getLeveys(), logiikka.getKentta().getOikeaPelaaja().getKorkeus());
        
        getToolkit().sync();
    }    

    @Override
    public void paivita() {
        this.repaint();
    }
    
}
