/**
 * Pelissä käytetty pallo. Tarjoaa metodin liikuttamiseen, sekä rajojen tutkimiseen.
 */

package irene.pong.komponentit;

import irene.pong.logiikka.Suunta;
import java.awt.Rectangle;

public class Pallo {

    private final int halkaisija = 20;
    private int nopeus = 3;
    private int x;
    private int y;
    private Suunta suuntaY;
    private Suunta suuntaX;
    private boolean paikallaan;

    public Pallo() {
        this.suuntaX = Suunta.OIKEA;
        this.suuntaY = Suunta.ALAS;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHalkaisija() {
        return halkaisija;
    }

    public int getNopeus() {
        return nopeus;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Suunta getSuuntaY() {
        return suuntaY;
    }

    public void setSuuntaY(Suunta suuntaY) {
        this.suuntaY = suuntaY;
    }

    public Suunta getSuuntaX() {
        return suuntaX;
    }

    public void setSuuntaX(Suunta suuntaX) {
        this.suuntaX = suuntaX;
    }
    
    public void liiku() {
//        System.out.println("SuuntaX " + suuntaX);
//        System.out.println("SuuntaY " + suuntaY);

        switch (suuntaY) {
            case YLOS:
                y -= nopeus;
                break;
            case ALAS:
                y += nopeus;
                break;
        }

        switch (suuntaX) {
            case OIKEA:
                x += nopeus;
                break;
            case VASEN:
                x -= nopeus;
                break;
        }
        
    }

    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getHalkaisija(), getHalkaisija());
    }

}
