/**
 * Luokka toimii lähennä säiliönä pelin komponenteille, eli pallolle ja mailoille.
 */

package irene.pong.komponentit;

import irene.pong.komponentit.Maila;
import irene.pong.komponentit.Pallo;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class Kentta {
    
    private final int leveys = 500;
    private final int korkeus = 600;
    private final Pallo pallo;
    private final Maila vasen;
    private final Maila oikea;
    private final List<Este> esteet;
    private final Random random;
    
    public Kentta() {  
        /**
         * Luokan vastuulla on luoda kentän komponentit, eli pallo ja mailat.
         * 
         * @see Pallo
         * @see Maila
         */
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
    
    public void lisaaEste() {
        /**
         * Lisää kentän keskiosaan uuden esteen varmistaen ensin, että uutta estettä ei luoda vanhan päälle.
         * 
         * @see Este
         */
        Este uusi;
        while(true) {
            boolean osuuEsteeseen = false;
            int x = random.nextInt(50) + getLeveys()/3;
            int y = random.nextInt(getKorkeus()-20) + 20;
            uusi = new Este(x, y);
            for (Este este: esteet) {
                if (este.getRajat().intersects(uusi.getRajat())) {
                    osuuEsteeseen = true;
                }
            }
            if (!osuuEsteeseen) {
                break;
            }
        }
            
        esteet.add(uusi);
    }
    
    public void poistaPoistettavat() {
        /**
         * Poistaa esteistä ne, joihin pallo on osunut.
         */
        List<Este> poistettavat = esteet.stream().filter(este -> este.isPoistettava()).collect(Collectors.toList());
        esteet.removeAll(poistettavat);
    }
    
    public void alustaKentta() {
        /**
         * Palauttaa kentän alun tilanteeseen, eli palauttaa pallon nopeuden, poistaa kaikki esteet ja tyhjentää mailojen vanhat suunnat.
         * 
         * @see Pallo
         * @see Maila
         */
        pallo.palautaNopeus();
        oikea.nollaaSuunnat();
        vasen.nollaaSuunnat();  
        esteet.clear();
    }
}
