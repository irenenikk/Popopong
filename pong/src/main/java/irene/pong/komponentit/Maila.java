package irene.pong.komponentit;

import irene.pong.logiikka.Tormattava;
import java.awt.Rectangle;
import irene.pong.logiikka.Liikkuja;

/**
 * Luokka edustaa pelissä käytettyjä mailoja. Tarjoaa metodin liikuttamiseen ja rajojen tutkimiseen.
 */
public class Maila implements Tormattava, Liikkuja {

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
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
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
    
    /**
     * Nollaa mailan suunnat.
     */
    public void nollaaSuunnat() {
        this.kiihdytaAlas = false;
        this.kiihdytaYlos = false;
    }

    /**
     * Muuttaa mailan y-koordinaatin arvoa riippuen suunnista.
     */
    public void liiku() { 
        if (kiihdytaAlas) {
            y += nopeus;
        } else if (kiihdytaYlos) {
            y -= nopeus;
        }
    }

    @Override
    public Rectangle getYlarajat() {
        return new Rectangle(getX(), getY() - 5, getLeveys(), 5);
    }

    @Override
    public Rectangle getAlarajat() {
        return new Rectangle(getX(), getY() + getKorkeus(), getLeveys(), 5);
    }

    @Override
    public Rectangle getVasenRajat() {
        return new Rectangle(getX(), getY(), 5, getKorkeus());
    }

    @Override
    public Rectangle getOikeaRajat() {
        return new Rectangle(getX() + getLeveys(), getY(), 5, getKorkeus());
    }
}
