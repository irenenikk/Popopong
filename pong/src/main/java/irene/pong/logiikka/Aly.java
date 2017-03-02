package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;

/**
 * Yksinpelissä vasemmanpuoleista mailaa liikuttava tekoäly.
 */
public class Aly {

    private Kentta kentta;

    /**
     * Aly saa konstruktorissaan kentän, jonka vasemmanpuoleista mailaa se
     * liikuttaa. Alyn on tunnettava koko kenttä, jotta se voi tarkkaila sekä
     * pallon, että vastapuolen pelaajan liikettä.
     *
     * @param k Pelin kenttä
     */
    public Aly(Kentta k) {
        kentta = k;
    }

    /**
     * Liikuttaa vasemmanpuoleista mailaa. Tekoäly reagoi palloon vain, jos se
     * kulkee vasemmanpuoleista mailaa kohti. Jos pallo on mailan liikkumassa
     * mailasta poispäin y-akselilla, tai on sen kanssa samalla puolella, maila
     * liikkuu palloa kohti.
     *
     */
    public void liikutaMailaa() {
        if (kentta.getPallo().getSuuntaX() == Suunta.VASEN) {
            if (palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.YLOS || palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaYlos(true);
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
            } else if (!palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.ALAS || !palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaAlas(true);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            }
        } else {
            kentta.getVasenPelaaja().setKiihdytaAlas(false);
            kentta.getVasenPelaaja().setKiihdytaYlos(false);
        }
    }

    private boolean palloYlapuolella() {
        return kentta.getPallo().getY() + kentta.getPallo().getHalkaisija() < kentta.getVasenPelaaja().getY() + kentta.getVasenPelaaja().getKorkeus() / 2;
    }

    private boolean palloOmallaPuolella() {
        return kentta.getPallo().getX() < kentta.getLeveys() / 2;
    }
}
