package irene.pong.komponentit;

import java.awt.Rectangle;
import java.util.Random;

public class Este {
    private int x;
    private int y;
    private int leveys;
    private int korkeus;
    private int varoitusaika;
    private final Random random;
    private boolean poistettava;
    private boolean tormattava;
    
    public Este(int x, int y) {
        /**
         * Luo uuden satunnaisen esteen annettuihin koordinaatteihin. Pallo voi törmätä esteeseen vasta 100 kierrosta sen luomisen jälkeen.
         */
        random = new Random();
        leveys = random.nextInt(40) + 20;
        korkeus = random.nextInt(40) + 20;
        this.x = x;
        this.y = y;
        poistettava = false;
        tormattava = false;
        varoitusaika = 100;
    }

    public boolean isPoistettava() {
        return poistettava;
    }

    public void setPoistettava(boolean poistettava) {
        this.poistettava = poistettava;
    }

    public boolean isNakyvissa() {
        return tormattava;
    }

    public void setNakyvissa(boolean nakyvissa) {
        this.tormattava = nakyvissa;
    }

    public int getVaroitusaika() {
        return varoitusaika;
    }

    public void setVaroitusaika(int varoitusaika) {
        this.varoitusaika = varoitusaika;
    }
    
    public void vahennaVaroitusaikaa() {
        varoitusaika--;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    public int getLeveys() {
        return leveys;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
    
    public Rectangle getRajat() {
        /**
         * Palauttaa esteen rajat Rectangle-oliona, jonka avulla voidaan tarkistaa, osuuko pallo esteeseen.
         */
        return new Rectangle(getX(), getY(), getLeveys(), getKorkeus());
    }
    
}
