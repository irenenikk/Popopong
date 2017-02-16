/**
 * Luokka edustaa pelissä käytettyjä mailoja. Tarjoaa metodin liikuttamiseen ja rajojen tutkimiseen.
 */

package irene.pong.komponentit;

import java.awt.Rectangle;

public class Maila {

    private int x;
    private int y;
    private boolean kiihdytaYlos;
    private boolean kiihdytaAlas;
    private final int nopeus = 5;
    private final int leveys = 20;
    private final int korkeus = 100;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isKiihdytaYlos() {
        return kiihdytaYlos;
    }

    public void setKiihdytaYlos(boolean kiihdytaYlos) {
        this.kiihdytaYlos = kiihdytaYlos;
    }

    public boolean isKiihdytaAlas() {
        return kiihdytaAlas;
    }

    public void setKiihdytaAlas(boolean kiihdytaAlas) {
        this.kiihdytaAlas = kiihdytaAlas;
    }

    public void liiku() { 
        if (kiihdytaAlas) {
            y += nopeus;
        } else if (kiihdytaYlos) {
            y -= nopeus;
        }
    }

    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getLeveys(), getKorkeus());
    }
    
    public boolean osuu(int x, int y) {
        if (x <= getX() + getLeveys() && x >= getX() && y <= getY() + getKorkeus() && y >= getY()) {
            return true;
        }
        return false;
    }
}
