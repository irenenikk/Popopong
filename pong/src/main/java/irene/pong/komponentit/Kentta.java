/**
 * Luokka toimii lähennä säiliönä pelin komponenteille, eli pallolle ja mailoille.
 */
package irene.pong.komponentit;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.util.*;
import java.util.stream.Collectors;

public class Kentta {

    private final int leveys = 500;
    private final int korkeus = 600;
    private final Pallo pallo;
    private final Maila vasen;
    private final Maila oikea;
    private final List<Este> esteet;
    private final Random random;

    /**
     * Luokan vastuulla on luoda kentän komponentit, eli pallo, mailat ja
     * esteet.
     *
     * @see Pallo
     * @see Maila
     */
    public Kentta() {
        pallo = new Pallo();
        vasen = new Maila();
        oikea = new Maila();
        esteet = new ArrayList();
        random = new Random();
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Maila getVasenPelaaja() {
        return vasen;
    }

    public Maila getOikeaPelaaja() {
        return oikea;
    }

    public List<Este> getEsteet() {
        return esteet;
    }

    /**
     * Lisää kentän keskiosaan uuden esteen varmistaen ensin, että uutta estettä
     * ei luoda vanhan päälle, tai keskelle kenttää, josta pallo lähtee.
     *
     * @see Este
     */
    public void lisaaEste() {
        Este uusi;
        while (true) {
            boolean osuuKomponenttiin = false;
            int x = random.nextInt(50) + getLeveys() / 3;
            int y = random.nextInt(getKorkeus() - 70) + 10;
            uusi = new Este(x, y);
            for (Este este : esteet) {
                if (este.getRajat().intersects(uusi.getRajat()) || uusi.getRajat().intersects(pallo.getRajatKeskella())) {
                    System.out.println("Olisi osunut esteeseen");
                    osuuKomponenttiin = true;
                }
            }
            if (!osuuKomponenttiin) {
                break;
            }
        }
        esteet.add(uusi);
    }

    /**
     * Poistaa esteistä ne, joihin pallo on osunut.
     */
    public void poistaPoistettavatEsteet() {
        List<Este> poistettavat = esteet.stream().filter(este -> este.isPoistettava()).collect(Collectors.toList());
        esteet.removeAll(poistettavat);
    }

    /**
     * Palauttaa kentän alun tilanteeseen, eli palauttaa pallon nopeuden,
     * poistaa kaikki esteet ja tyhjentää mailojen vanhat suunnat.
     *
     * @see Pallo
     * @see Maila
     */
    public void alustaKentta() {
        pallo.palautaNopeus();
        oikea.nollaaSuunnat();
        vasen.nollaaSuunnat();
        esteet.clear();
    }
}
