package irene.pong.komponentit;

import irene.pong.logiikka.Tormattava;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Este, johon pallo voi osua kentällä.
 * 
 */
public class Este implements Tormattava {
    private int x;
    private int y;
    private int leveys;
    private int korkeus;
    private int varoitusaika;
    private final Random random;
    private boolean poistettava;
    private boolean tormattava;
    
    /**
     * Luo uuden satunnaisen esteen annettuihin koordinaatteihin. Pallo voi törmätä esteeseen vasta 100 kierrosta sen luomisen jälkeen.
     * 
     * @param x Luotavan esteen x-koordinaatti.
     * @param y Luotavan esteen y-koordinaatti.
     */
    public Este(int x, int y) {
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

    public int getY() {
        return y;
    }
    
    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    
    /**
     * Palauttaa esteen rajat Rectangle-oliona, jonka avulla voidaan tarkistaa, 
     * osuuko pallo törmättävään komponenttiin.
     * 
     * @return Reactangle-olion, jonka avulla voidaan tarkistaa, ollaanko
     * estettä luomassa toisen päälle
     */
    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getLeveys(), getKorkeus());
    }

    @Override
    public Rectangle getYlarajat() {
        return new Rectangle(getX(), getY() - 2, getLeveys(), 5);
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
        return new Rectangle(getX() + getLeveys() - 5, getY(), 5, getKorkeus());
    }    
}
