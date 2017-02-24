package irene.pong.komponentit;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class EsteTest {
    private Este este;
    
        
    @Before
    public void setUp() {
        este = new Este(1, 1);
    }
    

    @Test
    public void esteAluksiEiPoistettavaJaEiTormattava() {
        assertFalse(este.isNakyvissa());
        assertFalse(este.isPoistettava());
    }
    
    @Test
    public void esteenLeveysJaKorkeusMin20Max60() {
        List<Este> esteet = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            esteet.add(new Este(1, 1));
        }
        List<Este> vaarat = esteet.stream().filter(este -> este.getLeveys() < 20 || este.getKorkeus()> 60).collect(Collectors.toList());
        assertEquals(0, vaarat.size());
    }
    
    @Test
    public void varoitusAikaAluksiSata() {
        assertEquals(100, este.getVaroitusaika());
    }
    
    @Test
    public void vahennaVaroitusaikaaVahentaaYhdella() {
        Este este = new Este(1, 1);
        este.vahennaVaroitusaikaa();
        assertEquals(99, este.getVaroitusaika());
    }
    
    @Test
    public void rajatPalauttaaRectanglet() {
        Assert.assertEquals(new Rectangle().getClass(), este.getAlarajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), este.getYlarajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), este.getVasenRajat().getClass());
        Assert.assertEquals(new Rectangle().getClass(), este.getOikeaRajat().getClass());
    }
}
