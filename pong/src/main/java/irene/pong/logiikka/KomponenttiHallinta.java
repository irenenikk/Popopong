package irene.pong.logiikka;

import irene.pong.grafiikka.Kentta;
import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;

public class KomponenttiHallinta {

    Kentta kentta;
    Maila pelaaja1;
    Maila pelaaja2;
    Pallo pallo;

    public KomponenttiHallinta(Kentta kentta) {
        this.kentta = kentta;
        pallo = kentta.getPallo();
        pelaaja1 = kentta.getPelaaja1();
        pelaaja2 = kentta.getPelaaja2();
               
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setKentta(Kentta kentta) {
        this.kentta = kentta;
    }

    public Maila getPelaaja1() {
        return pelaaja1;
    }

    public void setPelaaja1(Maila pelaaja1) {
        this.pelaaja1 = pelaaja1;
    }

    public Maila getPelaaja2() {
        return pelaaja2;
    }

    public void setPelaaja2(Maila pelaaja2) {
        this.pelaaja2 = pelaaja2;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public void setPallo(Pallo pallo) {
        this.pallo = pallo;
    }

    public void aloita() {
        asetaPalloKeskelle();
        asetaPelaaja1Keskelle();
        asetaPelaaja2Keskelle();
    }

    private void asetaPalloKeskelle() {
        pallo.setX(kentta.getWidth() / 2 - pallo.getHalkaisija() / 2);
        pallo.setY(kentta.getHeight() / 2 - pallo.getHalkaisija() / 2);
    }

    private void asetaPelaaja1Keskelle() {
        pelaaja1.setX(0);
        pelaaja1.setY(kentta.getHeight() / 2 - kentta.getPelaaja1().getKorkeus() / 2);
    }

    private void asetaPelaaja2Keskelle() {
        pelaaja2.setX(kentta.getWidth() - pelaaja2.getLeveys());
        pelaaja2.setY(kentta.getHeight() / 2 - pelaaja2.getKorkeus() / 2);
    }

    public void paivita() {
        pallo.liiku();
        pelaaja1.liiku();
        pelaaja2.liiku();
        tarkistaOsuukoMailaReunaan();
        tarkistaKimpoaakoPallo();
        tarkistaMaali();
        tarkistaOsuukoPalloMailaan();
    }
//    
//    public void pelaaja2Kiihdyttaa(boolean kiihdyta, int suunta) {
//        if (kiihdyta && suunta > 0) {
//            pelaaja2.setKiihdytaYlos(true);
//        }
//        
//        if (kiihdyta && suunta < 0) {
//            pelaaja2.setKiihdytaAlas(true);
//        }
//        
//        if (!kiihdyta && suunta > 0) {
//            pelaaja2.setKiihdytaYlos(false);
//        }
//        
//        if (!kiihdyta && suunta < 0) {
//            pelaaja2.setKiihdytaAlas(false);
//        }
//        pelaaja2.liiku();
//        
//        tarkistaOsuukoMailaReunaan();
//    }
//    
//    public void pelaaja1Kiihdyttaa(boolean kiihdyta, int suunta) {
//        if (kiihdyta && suunta > 0) {
//            pelaaja1.setKiihdytaYlos(true);
//        }
//        
//        if (kiihdyta && suunta < 0) {
//            pelaaja1.setKiihdytaAlas(true);
//        }
//        
//        if (!kiihdyta && suunta > 0) {
//            pelaaja1.setKiihdytaYlos(false);
//        }
//        
//        if (!kiihdyta && suunta < 0) {
//            pelaaja1.setKiihdytaAlas(false);
//        }
//        pelaaja1.liiku();
//        
//        tarkistaOsuukoMailaReunaan();
//    }
//    

    public void tarkistaKimpoaakoPallo() {
//        System.out.println("PalloY: " + pallo.getY());
        if (pallo.getY() <= 0) {
//            System.out.println("Pallo yläraeunassa");
            pallo.setSuuntaY(1);
            pallo.setY(pallo.getY() + pallo.getNopeus());
        }

        if (pallo.getY() + pallo.getHalkaisija() >= kentta.getHeight()) {
//            System.out.println("Pallo alareunassa");
            pallo.setSuuntaY(-1);
            pallo.setY(pallo.getY() - pallo.getNopeus());
        }
    }

    public void tarkistaMaali() {
        if (pallo.getX() + pallo.getHalkaisija() <= 0) {
            pelaaja2.lisaaPiste();
            asetaPalloKeskelle();
        }

        if (pallo.getX() >= kentta.getWidth()) {
            pelaaja1.lisaaPiste();
            asetaPalloKeskelle();
        }
    }

    public void tarkistaOsuukoPalloMailaan() {
        if (pallo.getRajat().intersects(pelaaja1.getRajat())) {
            pallo.setSuuntaX(1);
            pallo.setX(pallo.getX() + pallo.getNopeus());
        }
        
        if (pallo.getRajat().intersects(pelaaja2.getRajat())) {
            pallo.setSuuntaX(-1);
            pallo.setX(pallo.getX() - pallo.getNopeus());
        }
    }
    
    public void tarkistaOsuukoMailaReunaan() {
        if (pelaaja1.getY() <= 0) {
            pelaaja1.setY(0);
        }
        
        if (pelaaja1.getY() + pelaaja1.getKorkeus() >= kentta.getHeight()) {
            pelaaja1.setY(kentta.getHeight() - pelaaja1.getKorkeus());
        }
        
        if (pelaaja2.getY() <= 0) {
            pelaaja2.setY(0);
        }
        
        if (pelaaja2.getY() + pelaaja1.getKorkeus() >= kentta.getHeight()) {
            pelaaja2.setY(kentta.getHeight() - pelaaja2.getKorkeus());
        }

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        paivita();
//        kentta.repaint();
//    }


}
