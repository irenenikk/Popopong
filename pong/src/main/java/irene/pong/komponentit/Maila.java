package irene.pong.komponentit;

import java.awt.Rectangle;

public class Maila {

    private int x;
    private int y;
    private boolean kiihdytaYlos;
    private boolean kiihdytaAlas;
    private final int nopeus;
    private final int leveys = 20;
    private final int korkeus = 100;
    private int pisteet;

    public Maila() {
        pisteet = 0;
        nopeus = 5;
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

    public boolean isKiihdytaYlos() {
        return kiihdytaYlos;
    }

    public void setKiihdytaYlos(boolean kiihdytaYlos) {
        this.kiihdytaYlos = kiihdytaYlos;
    }

    public boolean isKiihdytaAlas() {
        return kiihdytaAlas;
    }

    public void setKiihdytaAlas(boolean kiihdytaAlas) {
        this.kiihdytaAlas = kiihdytaAlas;
    }

    public void liiku() { 
        if (kiihdytaAlas) {
            y += nopeus;
        } else if (kiihdytaYlos) {
            y -= nopeus;
        }
    }

    public void lisaaPiste() {
        pisteet++;
    }

    public Rectangle getRajat() {
        return new Rectangle(getX(), getY(), getLeveys(), getKorkeus());
    }
}
