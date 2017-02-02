package irene.pong.kali;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PainallusAlas extends AbstractAction {

    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    public PainallusAlas(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Painoit alas");
        if (kirjain.equals("S")) {
            kontrolleri.getPelaaja1().setKiihdytaAlas(true);
        }

        if (kirjain.equals("DOWN")) {
            kontrolleri.getPelaaja2().setKiihdytaAlas(true);
        }
    }
}
