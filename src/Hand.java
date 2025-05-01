import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    private int numOf2;
    private int numOfA;
    private int numOfK;
    private int numOfQ;
    private int numOfJ;
    private int numOf10;
    private int numOf9;
    private int numOf8;
    private int numOf7;
    private int numOf6;
    private int numOf5;
    private int numOf4;
    private int numOf3;

    public Hand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void sortCards() {
        ArrayList<Card> sorted = new ArrayList<Card>();
        boolean isAdded;
        for (Card card : hand) {
            isAdded = false;
            if (sorted.size() == 0) {
                sorted.add(card);
                isAdded = true;
            }
            else {
                for (int x = 0; x <= sorted.size(); x ++) {
                    if (card.getValue() < sorted.get(x).getValue()) {
                        sorted.add(0, card);
                        isAdded = true;
                        x = sorted.size();
                    }
                }
                if (!isAdded) {
                    sorted.add(card);
                }
            }
        }
        hand = sorted;
    }

}
