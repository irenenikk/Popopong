package irene.pong.kali;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class PainallusYlos extends AbstractAction {

    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    public PainallusYlos(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Painoit ylös");
        if (kirjain.equals("W")) {
            kontrolleri.getPelaaja1().setKiihdytaYlos(true);
        }

        if (kirjain.equals("UP")) {
            kontrolleri.getPelaaja2().setKiihdytaYlos(true);
        }
    }
}