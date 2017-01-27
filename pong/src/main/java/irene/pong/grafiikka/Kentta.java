
package irene.pong.grafiikka;


import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Kentta extends JPanel{
    private Pallo pallo;
    private Maila pelaaja1;
    private Maila pelaaja2;
    
    public Kentta(int leveys, int korkeus) {
        pallo = new Pallo();
        pallo.setX(leveys/2);
        pallo.setY(korkeus/2);
        
        pelaaja1 = new Maila();
        pelaaja1.setX(pelaaja1.getLeveys());
        pelaaja1.setY(korkeus/2);
        
        pelaaja2 = new Maila();
        pelaaja2.setX(leveys);
        pelaaja2.setY(korkeus/2);
    }
        
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(pallo.getX(), pallo.getY(), pallo.getHalkaisija(), pallo.getHalkaisija());
        g.fillRect(pelaaja1.getX(), pelaaja1.getY(), pelaaja1.getLeveys(), pelaaja1.getKorkeus());
        g.fillRect(pelaaja2.getX(), pelaaja2.getY(), pelaaja2.getLeveys(), pelaaja2.getKorkeus());    }
            
}
