package irene.pong.logiikka;

import irene.pong.komponentit.Kentta;

public class Aly {

    private Kentta kentta;

    public Aly(Kentta k) {
        kentta = k;
    }

    public void liikutaMailaa() {
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
