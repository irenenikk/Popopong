package irene.pong.komponentit;

import java.awt.Rectangle;

public class Pallo {

    private final int halkaisija = 20;
    private final int nopeus = 3;
    private int x;
    private int y;
    private int suuntaY;
    private int suuntaX;

    public Pallo() {
        this.suuntaX = 1;
        this.suuntaY = -1;
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

    public int getNopeus() {
        return nopeus;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSuuntaY() {
        return suuntaY;
    }

    public void setSuuntaY(int suuntaY) {
        this.suuntaY = suuntaY;
    }

    public int getSuuntaX() {
        return suuntaX;
    }

    public void setSuuntaX(int suuntaX) {
        this.suuntaX = suuntaX;
    }

    public void liiku() {
//        System.out.println("SuuntaX " + suuntaX);
//        System.out.println("SuuntaY " + suuntaY);
        if (suuntaY > 0) {
            y += nopeus;
        } else {
            y -= nopeus;
        }

        if (suuntaX > 0) {
            x += nopeus;
        } else {
            x -= nopeus;
        }
    }

    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getHalkaisija(), getHalkaisija());
    }
}
