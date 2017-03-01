package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;

/**
 * Yksinpelissä vasemmanpuoleista mailaa liikuttava tekoäly.
 */
public class Aly {

    private Kentta kentta;
    private boolean vaikea;

    /**
     * Aly saa konstruktorissaan kentän, jonka vasemmanpuoleista mailaa se
     * liikuttaa. Alyn on tunnettava koko kenttä, jotta se voi tarkkaila sekä
     * pallon, että vastapuolen pelaajan liikettä.
     * 
     * @param k Pelin kenttä
     */
    public Aly(Kentta k) {
        kentta = k;
        vaikea = true;
    }
    
    public void setVaikea(boolean v) {
        vaikea = v;
    }

    /**
     * Liikuttaa vasemmanpuoleista mailaa. Jos pallo on vasemmanpuoleisen
     * pelaajan puolella, maila liikkuu palloa kohti. Jos pallo on toisen
     * pelaajan puolella, maila pyrkii vastakkaiseen suuntaan, kuin
     * oikeanpuoleinen pelaaja.
     *
     * @see komponentit.Pallo
     * @see komponentit.Maila
     */
    public void liikutaMailaa() {
        if (kentta.getPallo().getSuuntaX() == Suunta.VASEN) {
            if (vaikea && palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.YLOS || palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaYlos(true);
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
            } else if (vaikea &&!palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.ALAS || !palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaAlas(true);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            } else if (vaikea){
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            }
        } else if (vaikea && oikeaPelaajaKentanAlapuolella()) {
            kentta.getVasenPelaaja().setKiihdytaAlas(false);
            kentta.getVasenPelaaja().setKiihdytaYlos(true);
        } else if (vaikea && !oikeaPelaajaKentanAlapuolella()) {
            kentta.getVasenPelaaja().setKiihdytaYlos(false);
            kentta.getVasenPelaaja().setKiihdytaAlas(true);
        }
    }

    private boolean palloYlapuolella() {
        return kentta.getPallo().getY() + kentta.getPallo().getHalkaisija() < kentta.getVasenPelaaja().getY() + kentta.getVasenPelaaja().getKorkeus() / 2;
    }

    private boolean oikeaPelaajaKentanAlapuolella() {
        return kentta.getOikeaPelaaja().getY() >= kentta.getKorkeus() / 2;
    }

    private boolean palloOmallaPuolella() {
        return kentta.getPallo().getX() < kentta.getLeveys() / 2;
    }
}
