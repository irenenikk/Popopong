/**
 * Luokka toimii lähennä säiliönä pelin komponenteille, eli pallolle ja mailoille. 
 * Kenttä myös toteuttaa varsinaisen osien maalaamisen.
 */

package irene.pong.grafiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import irene.pong.komponentit.Pelaaja;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Kentta extends JPanel { //ei ok, että luo Pallon ja Pelaajat --> logiikkaa. Tärkein työ maalaaminen
    
    private final Pallo pallo;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    
    public Kentta() {        
        this.pallo = new Pallo();
        this.pelaaja1 = new Pelaaja(1);
        this.pelaaja2 = new Pelaaja(2); 
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }
        
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 50)); 
        g.drawString(Integer.toString(pelaaja1.getPisteet()), 50, 50);
        g.drawString(Integer.toString(pelaaja2.getPisteet()), getWidth() - 100, 50);
        
        g.setColor(Color.WHITE);

        g.fillOval(pallo.getX(), pallo.getY(), pallo.getHalkaisija(), pallo.getHalkaisija());
        g.fillRect(pelaaja1.getMaila().getX(), pelaaja1.getMaila().getY(), pelaaja1.getMaila().getLeveys(), pelaaja1.getMaila().getKorkeus());
        g.fillRect(pelaaja2.getMaila().getX(), pelaaja2.getMaila().getY(), pelaaja2.getMaila().getLeveys(), pelaaja2.getMaila().getKorkeus());
        
        getToolkit().sync();
    }
}
