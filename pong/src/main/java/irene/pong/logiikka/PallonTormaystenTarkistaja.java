package irene.pong.logiikka;

import irene.pong.komponentit.*;

/**
 * Luokka tarkistaa, osuuko pallo johonkin Tormattava-rajapinnan toteuttavaan
 * komponenttiin kentällä, ja aiheuttaa sen kimpoamisen siitä.
 * 
 * @see Kentta
 * @see Tormattava
 * 
 */
public class PallonTormaystenTarkistaja {

    private Kentta kentta;
    
    /**
     * Luokka saa parametrinään kentän, jonka kautta se saa tietoonsa myös pallon.
     * 
     * @param k Pelin kenttä
     */

    public PallonTormaystenTarkistaja(Kentta k) {
        kentta = k;
    }

    /**
     * Palauttaa, osuiko pallo törmättävään olioon, ja siinä tapauksessa
     * aiheuttaa pallon siitä kimpoamisen.
     * 
     * @param t Tormattava-rajapinnan toteuttava Maila.
     * @return osuiko pallo kyseiseen komponenttiin.
     */
    public boolean palloTormaa(Tormattava t) {
        if (kentta.getPallo().getRajat().intersects(t.getOikeaRajat())) {
            kentta.getPallo().setSuuntaX(Suunta.OIKEA);
            kentta.getPallo().setX(kentta.getPallo().getX() + (int) kentta.getPallo().getNopeus());
            return true;
        }
        if (kentta.getPallo().getRajat().intersects(t.getVasenRajat())) {
            kentta.getPallo().setSuuntaX(Suunta.VASEN);
            kentta.getPallo().setX(kentta.getPallo().getX() - (int) kentta.getPallo().getNopeus());
            return true;
        }
        if (kentta.getPallo().getRajat().intersects(t.getYlarajat())) {
            kentta.getPallo().setSuuntaY(Suunta.YLOS);
            kentta.getPallo().setY(kentta.getPallo().getY() - (int) kentta.getPallo().getNopeus());
            return true;
        }
        if (kentta.getPallo().getRajat().intersects(t.getAlarajat())) {
            kentta.getPallo().setSuuntaY(Suunta.ALAS);
            kentta.getPallo().setY(kentta.getPallo().getY() + (int) kentta.getPallo().getNopeus());
            return true;
        }
        return false;
    }
    
    
    /**
     * Tarkistaa, osuiko pallo mihinkään kentällä näkyvissä olevista esteistä
     * ja siinä tapauksessa asettaa esteen poistettavaksi.
     */
    public void tarkistaOsuukoPalloEsteeseen() {
        kentta.getEsteet().stream().forEach((Este este) -> {
            if (este.isNakyvissa()) {
                if (palloTormaa(este)) {
                    este.setPoistettava(true);
                }
            }
        });
    }
    
    /**
     * Aiheuttaa pallon kimpoamisen seinästä.
     */
    public void tormaakoPalloSeinaan() {
        if (kentta.getPallo().getY() <= 0) {
            kentta.getPallo().setSuuntaY(Suunta.ALAS);
            kentta.getPallo().setY(kentta.getPallo().getY() + (int) kentta.getPallo().getNopeus());
        }

        if (kentta.getPallo().getY() + kentta.getPallo().getHalkaisija() >= kentta.getKorkeus()) {
            kentta.getPallo().setSuuntaY(Suunta.YLOS);
            kentta.getPallo().setY(kentta.getPallo().getY() - (int) kentta.getPallo().getNopeus());
        }
    }
    
    /**
     * Tarkistaa, saiko jompi kumpi pelaajista maalin, ja palauttaa tämän.
     * Asettaa pallon maalin jälkeen keskelle ja asettaa sen vaihtamaan 
     * suuntaa sekä odottamaan ennen uudelleen liikkeelle lähtemistä.
     * 
     * @return maalin saanut pelaaja.
     */
    public Pelaaja tarkistaMaali() {
        if (kentta.getPallo().getX() + kentta.getPallo().getHalkaisija() <= 0) { 
            asetaPalloKeskelle();
            kentta.getPallo().vaihdaSuuntaaSatunnaisesti();
            kentta.getPallo().setPaikallaan(true);
            return Pelaaja.OIKEA;
        }
        
        if (kentta.getPallo().getX() >= kentta.getLeveys()) {
            asetaPalloKeskelle();
            kentta.getPallo().vaihdaSuuntaaSatunnaisesti();
            kentta.getPallo().setPaikallaan(true);
            return Pelaaja.VASEN;
        }
        return null;
    }
    
    private void asetaPalloKeskelle() {
        kentta.getPallo().setX(kentta.getLeveys() / 2 - kentta.getPallo().getHalkaisija() / 2);
        kentta.getPallo().setY(kentta.getKorkeus() / 2 - kentta.getPallo().getHalkaisija() / 2);
    }

}
