package irene.pong.kali;

import irene.pong.logiikka.KomponenttiHallinta;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class VapautusYlos extends AbstractAction {
    private final KomponenttiHallinta kontrolleri;
    private final String kirjain;

    
    public VapautusYlos(KomponenttiHallinta kh, String k) {
        kontrolleri = kh;
        kirjain = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Vapautit napin ylös");
        if (kirjain.equals("W")) {
            kontrolleri.getPelaaja1().setKiihdytaYlos(false);
        }
        
        if (kirjain.equals("UP")) {
            kontrolleri.getPelaaja2().setKiihdytaYlos(false);            
        }
    }
    
}
