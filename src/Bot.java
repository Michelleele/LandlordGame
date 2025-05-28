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
    public static boolean foundInArray(ArrayList<Card[]> array, Card card) {
        for (Card[] subArray : array) {
            for (Card element : subArray) {
                if (element.getValue() == card.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    //adds all the singles (if cardArray = [3, 3, 3], method updates singles = [3]
    public void setSingles() {
        singles = new ArrayList<Card[]>();
        Card[] single;

        for (Card card : hand.getCardArray()) {
            single = new Card[] {card};
             if (!foundInArray(singles, card)) {
                 singles.add(single);
             }
        }
    }

    //adds all the pairs (if cardArray = [3, 3, 4, 4], method updates pairs = [3, 4]
    public void setPairs() {
        pairs = new ArrayList<Card[]>();
        Card[] onePair;
        ArrayList<Card> cardArray = hand.getCardArray();
        Card currentCard;
        Card nextCard;

        for (int currentIndex = 0; currentIndex < cardArray.size(); currentIndex ++) {
            onePair = new Card[2];
            currentCard = cardArray.get(currentIndex);

            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                nextCard = cardArray.get(nextIndex);
                if (currentCard.getValue() == nextCard.getValue()) {
                    onePair[0] = currentCard;
                    onePair[1] = nextCard;
                    nextIndex = cardArray.size();
                    currentIndex ++;
                }
            }

            try {
                if (!foundInArray(pairs, onePair[0])) {
                    pairs.add(onePair);
                }
            }
            catch (NullPointerException e) {}
        }
    }

    public void setTriplets() {
        triplets = new ArrayList<Card[]>();
        Card[] oneTriplet;
        ArrayList<Card> cardArray = hand.getCardArray();
        Card currentCard;
        Card nextCard;

        for (int currentIndex = 0; currentIndex < cardArray.size(); currentIndex ++) {
            oneTriplet = new Card[3];
            currentCard = cardArray.get(currentIndex);

            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                nextCard = cardArray.get(nextIndex);
                if (currentCard.getValue() == nextCard.getValue()) {
                    onePair[0] = currentCard;
                    onePair[1] = nextCard;
                    nextIndex = cardArray.size();
                }
            }

            try {
                if (!foundInArray(pairs, onePair[0])) {
                    pairs.add(onePair);
                }
            }
            catch (NullPointerException e) {}
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
