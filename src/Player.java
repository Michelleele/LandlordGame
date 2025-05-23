import java.util.ArrayList;

public class Player {

    protected Hand hand;
    private int cashAmt;
    protected boolean isLandlord;

    public void printHand() {
        ArrayList<Card> h = hand.getCardArray();
        System.out.println("New player:");
        int count = 1;
        for (Card card : h) {
            System.out.println("card #" + count + ": " + card.getValue());
            count ++;
        }
        System.out.println();
    }

    public Hand getHand() {
        return hand;
    }

    public Player(ArrayList<Card> h) {
        hand = new Hand(h);
        cashAmt = 100;
    }

    public int bidForLandlord() {
        return 0;
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



    }

}
