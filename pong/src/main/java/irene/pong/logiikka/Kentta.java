/**
 * Luokka toimii lähennä säiliönä pelin komponenteille, eli pallolle ja mailoille. 
 * Kenttä myös toteuttaa varsinaisen osien maalaamisen.
 */

package irene.pong.logiikka;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class Kentta { 
    
    private final Pallo pallo;
    private final Maila vasen;
    private final Maila oikea;
    private final int leveys = 500;
    private final int korkeus = 600;
    
    public Kentta() {        
        this.pallo = new Pallo();
        this.vasen = new Maila();
        this.oikea = new Maila(); 
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Maila getVasenPelaaja() {
        return vasen;
    }

    public Maila getOikeaPelaaja() {
        return oikea;
    }
}
