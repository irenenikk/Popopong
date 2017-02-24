package irene.pong.logiikka;

import java.awt.Rectangle;

/**
 * Rajapinta, joka tarjoaa metodin komponentin rajojen tarkastelemiseen.
 * 
 */
public interface Tormattava {
    
    /**
     * Palauttaa olion ylarajat.
     * 
     * @return Rectangle olio, joka on komponentin yläosan kokoinen.
     */
    public Rectangle getYlarajat();
    
    /**
     * Palauttaa olion alarajat.
     * 
     * @return Rectangle olio, joka on komponentin alaosan kokoinen.
     */
    public Rectangle getAlarajat();
    
    /**
     * Palauttaa olion vasemmat rajat.
     * 
     * @return Rectangle olio, joka on komponentin vasemmalla puolella.
     */
    public Rectangle getVasenRajat();

    /**
     * Palauttaa olion oikeat rajat.
     * 
     * @return Rectangle olio, joka on komponentin oikealla puolella.
     */
    public Rectangle getOikeaRajat();
    
}
