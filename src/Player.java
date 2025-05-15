import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Hand hand;
    private int cashAmt;
    private boolean isLandlord;

    public void printHand() {
        ArrayList<Card> h = hand.getHand();
        System.out.print("[");
        for (Card a : h) {
            System.out.print(a.getValue() + ", ");
        }
        System.out.println("]");
    }

    public Player(ArrayList<Card> h) {
        hand = new Hand(h);
        cashAmt = 100;
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
