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
        setSingles();
        setPairs();
        setTriplets();
    }

    public void testArrays() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("singles size: " + singles.size());
        System.out.println("singles array:\n[");
        if (singles.size() >= 1) {
            for (Card[] array : singles) {
                System.out.print(" [");
                for (Card card : array) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.print("],");
            }
        }
        System.out.println();
        System.out.println("pair size: " + pairs.size());
        System.out.println("pair array:\n[");
        if (pairs.size() >= 1) {
            for (Card[] array : pairs) {
                System.out.print(" [");
                for (Card card : array) {
                    System.out.println(card);
                    System.out.print(card.getValue() + ", ");
                }
                System.out.print("],");
            }
        }
        System.out.println();
        System.out.println("triplet size: " + triplets.size());
        if (triplets.size() >= 1) {
            System.out.println("triplets array:\n[");
            for (Card[] array : triplets) {
                System.out.print(" [");
                for (Card card : array) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.print("],");
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------");
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
             if ((!foundInArray(singles, card)) && (card.getValue() != 0)) {
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
//            System.out.println("----- current val: " + currentCard.getValue() + "--------");

            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                nextCard = cardArray.get(nextIndex);
                if (currentCard.getValue() == nextCard.getValue()) {
                    System.out.println("----- new pair found--------");
                    onePair[0] = currentCard;

                    onePair[1] = nextCard;
                    currentIndex ++;
                    nextIndex = cardArray.size();
                }
            }

            try {
                System.out.println("onePair = [" + onePair[0].getValue() + ", " + onePair[1].getValue() + "]");
            }
            catch (NullPointerException e) {
                System.out.println("no elements were added to the array onePair");
            }

            try {
                if ((!foundInArray(pairs, onePair[0]))) {
                    pairs.add(onePair);
                }
            }

            catch (NullPointerException e) {}

            if (pairs.size() > 0) {
                System.out.print("pairs = [");
                for (Card[] pair : pairs) {
                    System.out.print("[");
                    for (Card card : pair) {
                        System.out.print(card.getValue() + " ");
                    }
                    System.out.print("] ");
                }
            }

        }
    }

    public void setTriplets() {
//        System.out.println("-----------------------------------------------------------------------------------------");
        triplets = new ArrayList<Card[]>();
        Card[] oneTriplet;
        ArrayList<Card> cardArray = hand.getCardArray();
        Card currentCard;
        Card firstCard;
        Card secondCard;

        for (int currentIndex = 0; currentIndex < cardArray.size(); currentIndex ++) {
            oneTriplet = new Card[3];
            currentCard = cardArray.get(currentIndex);
//            System.out.println("Current card val: " + currentCard.getValue());

            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                if (nextIndex + 1 >= cardArray.size()) {
                    nextIndex = cardArray.size();
                }
                else {
//                    System.out.println("-----------------------------------------");
                    firstCard = cardArray.get(nextIndex);
//                    System.out.println("first card val: " + firstCard.getValue());
                    secondCard = cardArray.get(nextIndex + 1);
//                    System.out.println("second card val: " + secondCard.getValue());
                    if ((currentCard.getValue() == firstCard.getValue()) && (firstCard.getValue() == secondCard.getValue())) {
                        oneTriplet[0] = currentCard;
//                        System.out.println(oneTriplet[0].getValue());
                        oneTriplet[1] = firstCard;
//                        System.out.println(oneTriplet[1].getValue());
                        oneTriplet[2] = secondCard;
//                        System.out.println(oneTriplet[2].getValue());
                        nextIndex = cardArray.size();
//                        System.out.println("-----------------------------------------");
                    }
                }

            }

            try {
                if (!foundInArray(triplets, oneTriplet[0])) {
//                    System.out.println("added " + oneTriplet[0].getValue() + " to the list");
                    triplets.add(oneTriplet);
                }
            }
            catch (NullPointerException e) {}
        }
//        System.out.println("-----------------------------------------------------------------------------------------");
    }

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
