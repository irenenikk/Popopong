
package irene.pong.grafiikka;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class Pong extends JFrame {
    private final int leveys = 500;
    private final int korkeus = 600;
    private Kentta kentta;

    public int getLeveys() {
        return leveys;
    }
    
    public int getKorkeus() {
        return korkeus;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setKentta(Kentta kentta) {
        this.kentta = kentta;
    }
    
    public void aloita() {
        setSize(leveys, korkeus);
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        kentta = new Kentta();
        kentta.setBackground(Color.BLACK);
        add(kentta, BorderLayout.CENTER);
        
        setVisible(true);
        kentta.aloita();
    }
}
