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
        if (kentta.getPallo().getX() < kentta.getLeveys() / 2) {
            if (kentta.getPallo().getY() + kentta.getPallo().getHalkaisija() < kentta.getVasenPelaaja().getY()) {
                kentta.getVasenPelaaja().setKiihdytaYlos(true);
                kentta.getVasenPelaaja().setKiihdytaAlas(false);
            } else if (kentta.getPallo().getY() >= kentta.getVasenPelaaja().getY() + kentta.getVasenPelaaja().getKorkeus()) {
                kentta.getVasenPelaaja().setKiihdytaAlas(true);
                kentta.getVasenPelaaja().setKiihdytaYlos(false);
            }
        } else if (kentta.getOikeaPelaaja().getY() >= kentta.getKorkeus() / 2 && kentta.getVasenPelaaja().getY() >= kentta.getKorkeus() / 2) {
            kentta.getVasenPelaaja().setKiihdytaAlas(false);
            kentta.getVasenPelaaja().setKiihdytaYlos(true);
        } else if (kentta.getOikeaPelaaja().getY() < kentta.getKorkeus() / 2 && kentta.getVasenPelaaja().getY() < kentta.getKorkeus() / 2) {
            kentta.getVasenPelaaja().setKiihdytaYlos(false);
            kentta.getVasenPelaaja().setKiihdytaAlas(true);
        }
    }
}
