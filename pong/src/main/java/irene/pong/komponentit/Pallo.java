package irene.pong.komponentit;

public class Pallo {
    private final int halkaisija = 20;
    private int x;
    private int y;
    
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getHalkaisija() {
        return halkaisija;
    }

    public void setX(int x) {
        this.x = x-halkaisija/2;
    }

    public void setY(int y) {
        this.y = y-halkaisija/2;
    }
    
    public void liikuta(int xLiike, int yLiike) {
        x += xLiike;
        y += yLiike;
    }
    
}
