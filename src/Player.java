import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Hand hand;
    private int cashAmt;
    private boolean isLandlord;

    public Player(ArrayList<Card> h) {
        hand = new Hand(h);
        cashAmt = 100;
    }

    public void bidForLandlord() {
    }

    public boolean isLandlord() {
        return isLandlord;
    }

    public int getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(int cashAmt) {
        this.cashAmt = cashAmt;
    }

    public void setLandlord(boolean landlord) {
        isLandlord = landlord;
    }

    public void dealCards(ArrayList<Card> target) {
        for (Card card : target) {
            hand.getHand().remove(target);
        }
    }

}
