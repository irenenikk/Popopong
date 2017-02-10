/**
 * Luokka edustaa pelaajaa. Säilöö omat pisteensä, sekä oman mailansa.
 */

package irene.pong.komponentit;

public class Pelaaja {
    private final int id;
    private Maila maila;
    private int pisteet;

    public Pelaaja(int i) {
        id = i;
        pisteet = 0;
        this.maila = new Maila();
    }

    public String getId() {
        return Integer.toString(id);
    }

    public Maila getMaila() {
        return maila;
    }

    public void setMaila(Maila maila) {
        this.maila = maila;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }
    
    public void lisaaPiste() {
        this.pisteet++;
    }
}
