package irene.pong.grafiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Kentta extends JPanel {
    private Pallo pallo;
    private Maila pelaaja1;
    private Maila pelaaja2;
    
    public Pallo getPallo() {
        return pallo;
    }
    
    public Maila getPelaaja1() {
        return pelaaja1;
    }
    
    public Maila getPelaaja2() {
        return pelaaja2;
    }    

    public void aloita() {
        pallo = new Pallo();
        asetaPalloKeskelle();
        
        pelaaja1 = new Maila();
        pelaaja1.setX(0);
        pelaaja1.setY(getHeight()/2 - pelaaja1.getKorkeus()/2);
        
        pelaaja2 = new Maila();
        pelaaja2.setX(getWidth() - pelaaja2.getLeveys());
        pelaaja2.setY(getHeight()/2 - pelaaja2.getKorkeus()/2);
    }
    
    public void asetaPalloKeskelle() {
        pallo.setX(getWidth()/2 - pallo.getHalkaisija()/2);
        pallo.setY(getHeight()/2 - pallo.getHalkaisija()/2);        
    }
    
    public void paivita() {
        pallo.liiku();
        
        if (pallo.getY() >= 0) {
            pallo.setSuuntaY(-1);
            pallo.setY(pallo.getY()-1);
        }
        
        if (pallo.getY() <= -getHeight()) {
            pallo.setSuuntaY(1);
            pallo.setY(pallo.getY()+1);
        }
        
        if (pallo.getX() <= 0) {
            pelaaja2.lisaaPiste();
        }
        
        if (pallo.getX() >= getWidth()) {
            pelaaja1.lisaaPiste();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(pallo.getX(), pallo.getY(), pallo.getHalkaisija(), pallo.getHalkaisija());
        g.fillRect(pelaaja1.getX(), pelaaja1.getY(), pelaaja1.getLeveys(), pelaaja1.getKorkeus());
        g.fillRect(pelaaja2.getX(), pelaaja2.getY(), pelaaja2.getLeveys(), pelaaja2.getKorkeus());    
    }
}
