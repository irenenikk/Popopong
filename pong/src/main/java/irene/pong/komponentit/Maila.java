package irene.pong.komponentit;

public class Maila{
    private int x;
    private int y;
    private final int leveys = 20;
    private final int korkeus = 90;
    private int pisteet;
    
    public Maila() {
        pisteet = 0;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }
    
    public void liikuta(int yLiike) {
        y += yLiike;
    }
    
    public void lisaaPiste() {
        pisteet++;
    }
}
