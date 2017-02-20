package irene.pong.komponentit;

import irene.pong.logiikka.Tormattava;
import java.awt.Rectangle;
import java.util.Random;

public class Este implements Tormattava{
    private int x;
    private int y;
    private int leveys;
    private int korkeus;
    private int varoitusaika;
    private final Random random;
    private boolean poistettava;
    private boolean nakyvissa;
    
    public Este(int x, int y) {
        random = new Random();
        leveys = random.nextInt(80) + 20;
        korkeus = random.nextInt(80) + 20;
        this.x = x;
        this.y = y;
        poistettava = false;
        nakyvissa = false;
        varoitusaika = 100;
    }

    public boolean isPoistettava() {
        return poistettava;
    }

    public void setPoistettava(boolean poistettava) {
        this.poistettava = poistettava;
    }

    public boolean isNakyvissa() {
        return nakyvissa;
    }

    public void setNakyvissa(boolean nakyvissa) {
        this.nakyvissa = nakyvissa;
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

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getLeveys() {
        return leveys;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    @Override
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
