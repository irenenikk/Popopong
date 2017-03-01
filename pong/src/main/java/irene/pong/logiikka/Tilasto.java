
package irene.pong.logiikka;

/**
 * Luokka lisää pelaajille pisteitä ja tarkistaa voittajan.
 * 
 */
public class Tilasto {

    private final int voittoPisteet = 15;
    private int vasenPisteet;
    private int oikeaPisteet;
    
    /**
     * Aluksi molempien pelaajien pisteet ovat nolla.
     */
    public Tilasto() {
        vasenPisteet = 0;
        oikeaPisteet = 0;
    }
    
    /**
     * Lisää pelaajalle pisteen.
     * 
     * @param p Pelaaja, jolle piste annetaan
     */
    public void lisaaPistePelaajalle(Pelaaja p) {
        if (p == Pelaaja.VASEN) {
            vasenPisteet++;
        } else if (p == Pelaaja.OIKEA) {
            oikeaPisteet++;
        }
    }
    
    /**
     * Palauttaa vasemmanpuoleisen pelaajan pisteet.
     * @return Vasemmanpuoleisen pelaajan pisteet.
     */
    public int vasemmanPelaajanPisteet() {
        return vasenPisteet;
    }
    
    /**
     * Palauttaa oikeanpuoleisenpelaajan pisteet.
     * @return Oikeanpuoleisen pelaajan pisteet.
     */
    public int oikeanPelaajanPisteet() {
        return oikeaPisteet;
    }    

    public void setVasenPisteet(int vasenPisteet) {
        this.vasenPisteet = vasenPisteet;
    }

    public void setOikeaPisteet(int oikeaPisteet) {
        this.oikeaPisteet = oikeaPisteet;
    }

    /**
     * Vertailee pelaajien pisteitä keskenään ja palauttaa voittajan.
     * 
     * @return Voittanut pelaaja
     */
    public Pelaaja voittaja() {
        if (vasenPisteet >= voittoPisteet) {
            return Pelaaja.VASEN;
        } else if (oikeaPisteet >= voittoPisteet) {
            return Pelaaja.OIKEA;
        } else {
            return null;
        }
    }
    
    /**
     * Nollaa pisteet. Kutsutaan, kun aloitetaan uusi peli.
     */
    public void nollaaPisteet() {
        vasenPisteet = 0;
        oikeaPisteet = 0;
    }
}
