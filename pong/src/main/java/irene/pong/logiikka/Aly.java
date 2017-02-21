/**
 * Yksinpelissä vasemmanpuoleista mailaa liikuttava tekoäly.
 */
package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;

public class Aly {

    private Kentta kentta;

    public Aly(Kentta k) {
        /**
         * Aly saa konstruktorissaan kentän, jonka vasemmanpuoleista mailaa se liikuttaa.
         * Alyn on tunnettava koko kenttä, jotta se voi tarkkaila sekä pallon, että vastapuolen pelaajan liikettä.
         */
        kentta = k;
    }

    public void liikutaMailaa() {
        /**
         * Liikuttaa vasemmanpuoleista mailaa. 
         * Jos pallo on vasemmanpuoleisen pelaajan puolella, maila liikkuu palloa kohti.
         * Jos pallo on toisen pelaajan puolella, maila pyrkii vastakkaiseen suuntaan, kuin oikeanpuoleinen pelaaja.
         * 
         * @see komponentit.Pallo
         * @see komponentit.Maila
         */
        if (kentta.getPallo().getSuuntaX() == Suunta.VASEN) {
            if (palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.YLOS || palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaYlos(true);
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
            } else if (!palloYlapuolella() && kentta.getPallo().getSuuntaY() == Suunta.ALAS || !palloYlapuolella() && palloOmallaPuolella()) {
                kentta.getVasenPelaaja().setKiihdytaAlas(true);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            } else {
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            }
        } else if (oikeaPelaajaKentanAlapuolella()) {
            kentta.getVasenPelaaja().setKiihdytaAlas(false);
            kentta.getVasenPelaaja().setKiihdytaYlos(true);
        } else if (!oikeaPelaajaKentanAlapuolella()) {
            kentta.getVasenPelaaja().setKiihdytaYlos(false);
            kentta.getVasenPelaaja().setKiihdytaAlas(true);
        }
    }
    
    private boolean palloYlapuolella() {
        return kentta.getPallo().getY() + kentta.getPallo().getHalkaisija() < kentta.getVasenPelaaja().getY() + kentta.getVasenPelaaja().getKorkeus()/2 ;
    }
    
    private boolean oikeaPelaajaKentanAlapuolella() {
        return kentta.getOikeaPelaaja().getY() >= kentta.getKorkeus() / 2;
    }
    
    private boolean palloOmallaPuolella() {
        return kentta.getPallo().getX() < kentta.getLeveys() / 2;
    }
}
