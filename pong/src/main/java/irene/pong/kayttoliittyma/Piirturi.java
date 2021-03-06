/**
 * Luokka piirtää pelikentän sisällön.
 */
package irene.pong.kayttoliittyma;

import irene.pong.komponentit.Este;
import irene.pong.logiikka.Paivitettava;
import irene.pong.logiikka.Pong;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;

public class Piirturi extends JPanel implements Paivitettava {

    private final Pong logiikka;
    private HashMap<Este, Color> esteidenVarit;
    private final Random r;

    /**
     * @param pong peli, jonka kentän sisältö ja pisteet luokan tulee piirtää.
     */
    public Piirturi(Pong pong) {
        logiikka = pong;
        r = new Random();
        esteidenVarit = new HashMap();
    }

    public void tyhjennaEsteidenVarit() {
        esteidenVarit.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(Integer.toString(logiikka.getTilasto().vasemmanPelaajanPisteet()), 50, 50);
        g.drawString(Integer.toString(logiikka.getTilasto().oikeanPelaajanPisteet()), getWidth() - 100, 50);

        if (logiikka.getKentta().getEsteet().size() > 0) {
            logiikka.getKentta().getEsteet().stream().forEach((este) -> {
                if (!este.isNakyvissa()) {
                    g.setColor(Color.DARK_GRAY);
                } else {
                    if (!esteidenVarit.containsKey(este)) {
                        esteidenVarit.put(este, new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
                    }
                    g.setColor(esteidenVarit.get(este));
                }
                g.fillRect(este.getX(), este.getY(), este.getLeveys(), este.getKorkeus());
            });
        }

        g.setColor(Color.WHITE);

        g.fillRect(logiikka.getKentta().getVasenPelaaja().getX(), logiikka.getKentta().getVasenPelaaja().getY(), logiikka.getKentta().getVasenPelaaja().getLeveys(), logiikka.getKentta().getVasenPelaaja().getKorkeus());
        g.fillRect(logiikka.getKentta().getOikeaPelaaja().getX(), logiikka.getKentta().getOikeaPelaaja().getY(), logiikka.getKentta().getOikeaPelaaja().getLeveys(), logiikka.getKentta().getOikeaPelaaja().getKorkeus());
        
        g.fillOval(logiikka.getKentta().getPallo().getX(), logiikka.getKentta().getPallo().getY(), logiikka.getKentta().getPallo().getHalkaisija(), logiikka.getKentta().getPallo().getHalkaisija());
        
        if (logiikka.getKontrolleri().getPalloMerkkiX()!=-1 && logiikka.getKontrolleri().getPalloMerkkiY()!=-1) {
            System.out.println(logiikka.getKontrolleri().getPalloMerkkiX() + ", " + logiikka.getKontrolleri().getPalloMerkkiY());
            g.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
            g.fillOval(logiikka.getKontrolleri().getPalloMerkkiX(), logiikka.getKontrolleri().getPalloMerkkiY(), logiikka.getKentta().getPallo().getHalkaisija(), logiikka.getKentta().getPallo().getHalkaisija());
        }
        getToolkit().sync();
    }

    @Override
    public void paivita() {
        this.repaint();
    }

}
