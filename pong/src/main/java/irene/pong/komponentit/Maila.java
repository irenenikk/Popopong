package irene.pong.komponentit;

import java.awt.Color;
import javax.swing.JPanel;

public class Maila{
    private int x;
    private int y;
    private int leveys;
    private int korkeus;
    
    public Maila(int leveys, int korkeus) {
        
    }
       
    public void liikuta(int yLiike) {
        y += yLiike;
    }
}
