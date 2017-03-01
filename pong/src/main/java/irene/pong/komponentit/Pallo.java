package irene.pong.komponentit;

import irene.pong.logiikka.Suunta;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;
import irene.pong.logiikka.Liikkuja;

/**
 * Pelissä käytetty pallo. Tarjoaa metodin liikuttamiseen, sekä rajojen tutkimiseen.
 */
public class Pallo implements Liikkuja {

    private final int halkaisija = 20;
    private double nopeus = 3;
    private int x;
    private int y;
    private Suunta suuntaY;
    private Suunta suuntaX;
    private boolean odottaa;

    /**
     * Palloa luodessa sille asetetaan aloitussuunta, ja se asetetaan odottamaan.
     */
    public Pallo() {
        suuntaY = Suunta.ALAS;
        suuntaX = Suunta.OIKEA;
        odottaa = false;        
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getHalkaisija() {
        return halkaisija;
    }

    public double getNopeus() {
        return nopeus;
    }
    
    /**
     * Lisää pallon nopeutta.
     */
    public void nopeuta() {
        nopeus += 0.5;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
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

    public boolean isPaikallaan() {
        return odottaa;
    }

    public void setPaikallaan(boolean paikallaan) {
        this.odottaa = paikallaan;
    }
    
    /**
     * Palauttaa pallon nopeuden takaisin alkuperäiseksi.
     */
    public void palautaNopeus() {
        nopeus = 3;
    }
    
    /**
     * Liikuttaa palloa, jos sitä ei ole asetettu odottamaan maalin jälkeen.
     */
    public void liiku() {
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
        }
    }
    
    /**
     * Vaihtaa pallon suuntaa. Kutsutaan maalin jälkeen, jotta pallon lähtosuuntaa ei voi ennakoida.
     */
    public void vaihdaSuuntaaSatunnaisesti() {
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

    /**
     * Palauttaa pallon rajat Rectangle-oliona, jonka avulla voidaan tarkistaa, osuuko pallo mailaan.
     * 
     * @return Palauttaa pallon rajan Rectangle oliona, jonka avulla voidaan
     * tarkistaa, törmääkö se johonkin kentän komponenttiin.
     */
    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getHalkaisija(), getHalkaisija());
    }
    
    /**
     * Palauttaa pallon rajat sen ollessa keskellä kenttää.
     * 
     * @return Pallon rajat sen ollessa keskellä kenttää.
     */
    public Rectangle getRajatKeskella() {
        return new Rectangle(getLeveys() / 2 - getHalkaisija(), getKorkeus() / 2 - getHalkaisija(), getHalkaisija(), getHalkaisija());
    }

    @Override
    public int getKorkeus() {
        return this.halkaisija;
    }

    @Override
    public int getLeveys() {
        return this.halkaisija;
    }
}
