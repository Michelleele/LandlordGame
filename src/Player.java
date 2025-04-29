import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private int cashAmt;
    private boolean isLandlord;


    public Player(ArrayList<Card> h) {
        hand = h;
        cashAmt = 100;
    }

    public boolean isLandlord() {
        return isLandlord;
    }

    public void setLandlord(boolean landlord) {
        isLandlord = landlord;
    }

    public void dealCard(ArrayList<Card> target) {
        for (Card card : target) {
            hand.remove(target);
        }
    }

}
