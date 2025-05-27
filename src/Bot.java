import java.util.ArrayList;

public class Bot extends Player {

    private ArrayList<Card[]> singles;
    private ArrayList<Card[]> pairs;
    private ArrayList<Card[]> triplets;
    private ArrayList<Card[]> tripletWithAttachedCard;
    private ArrayList<Card[]> tripletWithAttachedPair;
    private ArrayList<Card[]> sequence;
    private ArrayList<Card[]> sequenceOfPairs;
    private ArrayList<Card[]> sequenceOfTriplets;
    private ArrayList<Card[]> sequenceOFTripletWithAttachedCard;
    private ArrayList<Card[]> sequenceOFTripletWithAttachedPair;
    private ArrayList<Card[]> bomb;
    private ArrayList<Card[]> rocket;
    private ArrayList<Card[]> QuadWith2AttachedCards;
    private ArrayList<Card[]> QuadWith2AttachedPair;

    public Bot(ArrayList<Card> h) {
        super(h);
    }

    public void refreshLists() {

    }

    //should I have a card variable as the parameter or a card array
    // use card!!!
    public static void foundInArray(ArrayList<Card[]> array, Card cards) {


    }

    //adds all the singles (if cardArray = [3, 3, 3], method updates singles = [3]
    public void setSingles() {
        singles = new ArrayList<Card[]>();
        Card[] single;
        for (Card card : hand.getCardArray()) {
            single = new Card[] {card};
             if () {
                 singles.add(card);
             }
        }
    }

    //adds all the pairs (if cardArray = [3, 3, 4, 4], method updates pairs = [3, 4]
    public void setPairs() {
        pairs = new ArrayList<Card>();
        int count;
        for (int current = 0; current < hand.getCardArray().size(); current ++) {
            count = 0;
            for (int next = current + 1; next < hand.getCardArray().size(); next ++) {
                if (hand.getCardArray().get(current).getValue() == hand.getCardArray().get(next).getValue()) {
                    count ++;
                }
            }
            if ((count >= 2) && (!contains(pairs, hand.getCardArray().get(current)))) {
                pairs.add(hand.getCardArray().get(current));
            }
        }
    }

    public void setTriplets() {
        triplets = new ArrayList<Card>();
        int count;
        for (int current = 0; current < hand.getCardArray().size(); current ++) {
            count = 0;
            for (int next = current + 1; next < hand.getCardArray().size(); next++) {
                if (hand.getCardArray().get(current).getValue() == hand.getCardArray().get(next).getValue()) {
                    count++;
                }
            }
            if ((count >= 3) && (!contains(triplets, hand.getCardArray().get(current)))) {
                triplets.add(hand.getCardArray().get(current));
            }
        }
    }

    public void setTripletWithAttachedCard

    public ArrayList<Card> getMatchingCards(int combinationIndex) {
        ArrayList<Card> array = new ArrayList<Card>();
        // have: boolean array with all the possible combinations, an int array of all the number of cards we have for each card type
        if (hand.getCombinationArray()[combinationIndex]) {
            //get the next greatest cards of that combination
        }
        return array;
    }



    public int bidForLandlord() {
        if (hand.getCombinationArray()[11] || hand.getCombinationArray()[10]) {
            return 3;
        }
        else {
            return (int) (Math.random() * 2) + 1;
        }
    }


}
