package irene.pong.komponentit;

public class Maila{
    private int x;
    private int y;
    private final int leveys = 20;
    private final int korkeus = 90;
    
    
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
        this.x = x - leveys;
    }

    public void setY(int y) {
        this.y = y - korkeus/2;
    }
    
    public void liikuta(int yLiike) {
        y += yLiike;
    }
}
