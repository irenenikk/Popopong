/**
 * Luokka edustaa pelissä käytettyjä mailoja. Tarjoaa metodin liikuttamiseen ja rajojen tutkimiseen.
 */

package irene.pong.komponentit;

import irene.pong.logiikka.Tormattava;
import java.awt.Rectangle;

public class Maila implements Tormattava{

    private int x;
    private int y;
    private boolean kiihdytaYlos;
    private boolean kiihdytaAlas;
    private final int nopeus = 5;
    private final int leveys = 20;
    private final int korkeus = 100;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getLeveys() {
        return leveys;
    }

    @Override
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
    
    public void nollaaSuunnat() {
        /**
         * Nollaa mailan suunnat.
         */
        this.kiihdytaAlas = false;
        this.kiihdytaYlos = false;
    }

    public void liiku() { 
        /**
         * Muuttaa mailan y-koordinaatin arvoa riippuen suunnista.
         */
        if (kiihdytaAlas) {
            y += nopeus;
        } else if (kiihdytaYlos) {
            y -= nopeus;
        }
    }

    public Rectangle getRajat() {
        /**
         * Palauttaa mailan rajat rectangle-oliona, joka helpottaa törmäysten tarkistamista.
         */
        return new Rectangle(getX(), getY(), getLeveys(), getKorkeus());
    }
    
    public Rectangle getYlapaa() {
        /**
         * Palauttaa mailan yläpään rajat.
         */
        return new Rectangle(getX() + 5, getY() - 5, getLeveys() - 5, 5);
    }
    
    public Rectangle getAlapaa() {
        /**
         * Palauttaa mailan alapään rajat.
         */
        return new Rectangle(getX() + 5, getY() + getKorkeus() - 5, getLeveys() - 5, 5);
    }

}
