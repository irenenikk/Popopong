package irene.pong.komponentit;

public class Pallo {
    private int x;
    private int y;
    private int halkaisija;
    
    public Pallo(int halkaisija) {
        this.halkaisija = halkaisija;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getHalkaisija() {
        return halkaisija;
    }
    
    public void liikuta(int xLiike, int yLiike) {
        x += xLiike;
        y += yLiike;
    }
    
}
