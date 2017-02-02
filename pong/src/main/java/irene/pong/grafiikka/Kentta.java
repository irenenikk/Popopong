package irene.pong.grafiikka;

import irene.pong.kali.PainallusYlos;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Kentta extends JPanel {
    
    private final Pallo pallo;
    private final Maila pelaaja1;
    private final Maila pelaaja2;
    
    public Kentta() {        
        this.pallo = new Pallo();
        this.pelaaja1 = new Maila();
        this.pelaaja2 = new Maila();
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Maila getPelaaja1() {
        return pelaaja1;
    }

    public Maila getPelaaja2() {
        return pelaaja2;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(pallo.getX(), pallo.getY(), pallo.getHalkaisija(), pallo.getHalkaisija());
        g.fillRect(pelaaja1.getX(), pelaaja1.getY(), pelaaja1.getLeveys(), pelaaja1.getKorkeus());
        g.fillRect(pelaaja2.getX(), pelaaja2.getY(), pelaaja2.getLeveys(), pelaaja2.getKorkeus());
        getToolkit().sync();
    }
}
