/**
 * Luokka lisää pelaajille pisteitä ja tarkistaa voittajan.
 * 
 */

package irene.pong.logiikka;

public class Tilasto {

//    private Kentta kentta;
    private final int voittoPisteet = 15;
    private int vasenPisteet;
    private int oikeaPisteet;
    
    public Tilasto() {
//        kentta = k;
        vasenPisteet = 0;
        oikeaPisteet = 0;
    }
    
    public void lisaaPistePelaajalle(Pelaaja p) {
        /**
         * Lisää pelaajalle pisteen.
         * 
         * @param p Pelaaja, jolle piste annetaan
         */
        if (p == Pelaaja.VASEN) {
            vasenPisteet++;
        } else if(p == Pelaaja.OIKEA) {
            oikeaPisteet++;
        }
    }
    
    public int vasemmanPelaajanPisteet() {
        return vasenPisteet;
    }
    
    public int oikeanPelaajanPisteet() {
        return oikeaPisteet;
    }    

    public void setVasenPisteet(int vasenPisteet) {
        this.vasenPisteet = vasenPisteet;
    }

    public void setOikeaPisteet(int oikeaPisteet) {
        this.oikeaPisteet = oikeaPisteet;
    }

    public Pelaaja voittaja() {
        /**
         * Vertailee pelaajien pisteitä keskenään ja palauttaa voittajan.
         * 
         * @return Voittanut pelaaja
         */
        if (vasenPisteet >= voittoPisteet) {
            return Pelaaja.VASEN;
        } else if (oikeaPisteet >= voittoPisteet) {
            return Pelaaja.OIKEA;
        } else {
            return null;
        }
    }
    
    public void nollaaPisteet() {
        /**
         * Nollaa pisteet. Kutsutaan, kun aloitetaan uusi peli.
         */
        vasenPisteet = 0;
        oikeaPisteet = 0;
    }
}
