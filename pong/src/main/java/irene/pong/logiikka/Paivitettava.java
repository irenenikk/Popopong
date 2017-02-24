package irene.pong.logiikka;

/**
 * Rajapinta, jonka metodia päivitä kutsutaan päälogiikkaluokassa, jotta pelin tilamuutokset saadaan näkymään käyttöliittymässä.
 */
public interface Paivitettava {
    
    /**
     * Metodia kutsutaan, kun halutaan käyttöliittymän päivittyvän vastaamaan pelin tilaa.
     */
   
    public void paivita();
    
}
