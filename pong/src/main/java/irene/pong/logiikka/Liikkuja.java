package irene.pong.logiikka;

/**
 * Rajapinta tarjoaa metodit olion koordinaattien ja rajojen seuraamiseksi ja
 * muuttamiseksi.
 */
public interface Liikkuja {

    /**
     * Palauttaa olion x-koordinaatin.
     * @return x-koordinaatti
     */
    public int getX();

    /**
     * Muuttaa olion x-koordinaattia.
     * @param x uusi x-koordinaatti
     */
    public void setX(int x);

    /**
     * Palauttaa olion y-koordinaatin.
     * 
     * @return y-koordinaatti
     */
    public int getY();

    /**
     * Muuttaa olion y-koordinaattia.
     * 
     * @param y uusi y-koordinaatti
     */
    public void setY(int y);

    /**
     * Palauttaa olion korkeuden.
     *
     * @return olion korkeus
     */
    public int getKorkeus();

    /**
     * Palauttaa olion leveyden.
     * 
     * @return olion leveys
     */
    public int getLeveys();
}
