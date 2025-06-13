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
    private int numOfJoker;

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getCardArray() {
        return hand;
    }

    public Hand(ArrayList<Card> hand) {
        this.hand = hand;
        //an array which each element represent the number of cards we have for each card type
        numOf3 = 0;
        numOf4 = 0;
        numOf5 = 0;
        numOf6 = 0;
        numOf7 = 0;
        numOf8 = 0;
        numOf9 = 0;
        numOf10 = 0;
        numOfJ = 0;
        numOfQ = 0;
        numOfK = 0;
        numOfA = 0;
        numOf2 = 0;
        numOfJoker = 0;
        sortCards();
        countCards();
    }

    public void countCards() {
        for (Card card : hand) {
            if (card.getValue() == 3) {
                numOf3++;
            }
            else if (card.getValue() == 4) {
                numOf4++;
            }
            else if (card.getValue() == 5) {
                numOf5++;
            }
            else if (card.getValue() == 6) {
                numOf6++;
            }
            else if (card.getValue() == 7) {
                numOf7++;
            }
            else if (card.getValue() == 8) {
                numOf8++;
            }
            else if (card.getValue() == 9) {
                numOf9++;
            }
            else if (card.getValue() == 10) {
                numOf10++;
            }
            else if (card.getValue() == 11) {
                numOfJ++;
            }
            else if (card.getValue() == 12) {
                numOfQ++;
            }
            else if (card.getValue() == 13) {
                numOfK++;
            }
            else if (card.getValue() == 14) {
                numOfA++;
            }
            else if (card.getValue() == 15) {
                numOf2++;
            }
            else if (card.getValue() == 16) {
                numOfJoker++;
            }
            else if (card.getValue() == 17) {
                numOfJoker++;
            }
        }
    }

    public void sortCards() {
        ArrayList<Card> sorted = new ArrayList<Card>();
        boolean isAdded;
        for (Card card : hand) {
            isAdded = false;
            if (sorted.size() == 0) {
                sorted.add(card);
            }
            else {
                for (int x = 0; x < sorted.size(); x ++) {
                    if (card.getValue() <= sorted.get(x).getValue()) {
                        sorted.add(x, card);
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
