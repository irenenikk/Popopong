/**
 * Pelissä käytetty pallo. Tarjoaa metodin liikuttamiseen, sekä rajojen tutkimiseen.
 */

package irene.pong.komponentit;

import irene.pong.logiikka.Suunta;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Pallo {

    private final int halkaisija = 20;
    private double dX; //testailua pallon liikeperiaatteen vaihtamiseksi
    private double dY;
    private double nopeus = 3;
    private int x;
    private int y;
    private Suunta suuntaY;
    private Suunta suuntaX;
    private boolean odottaa;

    public Pallo() {
        this.suuntaX = Suunta.VASEN;
        this.suuntaY = Suunta.ALAS;
        odottaa = false;
//        dX = 3.5;
//        dY = 2;
        
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

    public double getNopeus() {
        return nopeus;
    }
    
    public void nopeuta() {
        nopeus += 0.01;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getdX() {
        return dX;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
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

    public boolean isPaikallaan() {
        return odottaa;
    }

    public void setPaikallaan(boolean paikallaan) {
        this.odottaa = paikallaan;
    }
    
    public void liiku() {
        /**
         * Liikuttaa palloa, jos sitä ei ole asetettu odottamaan maalin jälkeen.
         */
        if (!odottaa) {
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
//            x += (int) dXs;
//            System.out.println("X: " + x);
//            y += (int) dY;
//            System.out.println("Y: " + y);
        }
    }
    
    public void vaihdaSuuntaaSatunnaisesti() {
        /**
         * Vaihtaa pallon suuntaa. Kutsutaan maalin jälkeen, jotta pallon lähtosuuntaa ei voi ennakoida.
         */
        int randomX = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomX == 0) {
            suuntaX = Suunta.VASEN;
        } else if (randomX == 1) {
            suuntaX = Suunta.OIKEA;
        }
        
        int randomY = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomY == 0) {
            suuntaY = Suunta.YLOS;
        } else if (randomX == 1) {
            suuntaY = Suunta.ALAS;
        }

    }

    public Rectangle getRajat() {
        /**
         * Palauttaa pallon rajat Rectangle-oliona, jonka avulla voidaan tarkistaa, osuuko pallo mailaan.
         */
        return new Rectangle(getX(), getY(), getHalkaisija(), getHalkaisija());
    }
    
}
