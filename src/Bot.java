import java.util.ArrayList;

public class Bot extends Player {

    private ArrayList<Card[]> singles;
    private ArrayList<Card[]> pairs;
    private ArrayList<Card[]> triplets;
    private ArrayList<Card[]> bomb;
    private ArrayList<ArrayList<Card>> sequenceOfSingles;
    private ArrayList<Card[]> rocket;

    public Bot(ArrayList<Card> h) {
        super(h);
        refreshLists();
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
        System.out.println();
        System.out.println("quad size: " + bomb.size());
        if (bomb.size() >= 1) {
            System.out.println("quad array:\n[");
            for (Card[] array : bomb) {
                System.out.print(" [");
                for (Card card : array) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.print("],");
            }
        }
        System.out.println();
        System.out.println("sequence size: " + sequenceOfSingles.size());
        if (sequenceOfSingles.size() >= 1) {
            System.out.println("sequence array:\n[");
            for (ArrayList<Card> array : sequenceOfSingles) {
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
        setSingles();
        setPairs();
        setTriplets();
        setQuads();
        setSequenceOfSingles();
    }

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

            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                nextCard = cardArray.get(nextIndex);
                if (currentCard.getValue() == nextCard.getValue()) {
                    onePair[0] = currentCard;
                    onePair[1] = nextCard;
                    currentIndex ++;
                    nextIndex = cardArray.size();
                    if ((!foundInArray(pairs, onePair[0]))) {
                        pairs.add(onePair);
                    }
                }
            }
        }
    }

    public void setTriplets() {
        triplets = new ArrayList<Card[]>();
        Card[] oneTriplet;
        ArrayList<Card> cardArray = hand.getCardArray();
        Card currentCard;
        Card firstCard;
        Card secondCard;

        for (int currentIndex = 0; currentIndex < cardArray.size(); currentIndex ++) {
            oneTriplet = new Card[3];
            currentCard = cardArray.get(currentIndex);
            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                if (nextIndex + 1 >= cardArray.size()) {
                    nextIndex = cardArray.size();
                }
                else {
                    firstCard = cardArray.get(nextIndex);
                    secondCard = cardArray.get(nextIndex + 1);
                    if ((currentCard.getValue() == firstCard.getValue()) && (firstCard.getValue() == secondCard.getValue())) {
                        oneTriplet[0] = currentCard;
                        oneTriplet[1] = firstCard;
                        oneTriplet[2] = secondCard;
                        if (!foundInArray(triplets, oneTriplet[0])) {
                            triplets.add(oneTriplet);
                        }
                        nextIndex = cardArray.size();
                    }
                }
            }
        }
    }

    public void setQuads() {
        bomb = new ArrayList<Card[]>();
        Card[] oneQuad;
        ArrayList<Card> cardArray = hand.getCardArray();
        Card currentCard;
        Card firstCard;
        Card secondCard;
        Card thirdcard;

        for (int currentIndex = 0; currentIndex < cardArray.size(); currentIndex ++) {
            oneQuad = new Card[4];
            currentCard = cardArray.get(currentIndex);
            for (int nextIndex = currentIndex + 1; nextIndex < cardArray.size(); nextIndex ++) {
                if (nextIndex + 2 >= cardArray.size()) {
                    nextIndex = cardArray.size();
                }
                else {
                    firstCard = cardArray.get(nextIndex);
                    secondCard = cardArray.get(nextIndex + 1);
                    thirdcard = cardArray.get(nextIndex + 2);
                    if ((currentCard.getValue() == firstCard.getValue()) && (firstCard.getValue() == secondCard.getValue()) && (secondCard.getValue() == thirdcard.getValue())) {
                        oneQuad[0] = currentCard;
                        oneQuad[1] = firstCard;
                        oneQuad[2] = secondCard;
                        oneQuad[3] = thirdcard;
                        if (!foundInArray(bomb, oneQuad[0])) {
                            bomb.add(oneQuad);
                        }
                        nextIndex = cardArray.size();
                    }
                }
            }
        }
    }

    public ArrayList<Card> getSequence(ArrayList<Card> array) {
//        System.out.println("array.size(j) = " + array.size());
//        System.out.print("array = [");
//        for (Card card : array) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("] \n\n");
        ArrayList<Card> oneSequence = new ArrayList<Card>();
        int nextIndex;
        Card currentCard;
        Card nextCard;
        Boolean wasNextIndexChanged = false;
//        currentIndex = nextIndex
        for (int currentIndex = 0; currentIndex + 1 < array.size(); currentIndex ++) {
//            System.out.println("------------------------------------------------------");
//            System.out.println("current index = " + currentIndex);
            currentCard = array.get(currentIndex);
//            System.out.println("Current card value: " + currentCard.getValue());
            nextIndex = currentIndex + 1;
//            System.out.println("next index = " + nextIndex);
            nextCard = array.get(currentIndex + 1);
//            System.out.println("Next Card value: " + nextCard.getValue());
//            System.out.println();
            while (nextCard.getValue() == currentCard.getValue()) {
//                System.out.println("We're in the while loop");
                nextIndex ++;
                nextCard = array.get(nextIndex);
                wasNextIndexChanged = true;
//                System.out.println("Next Card value: " + nextCard.getValue());
            }
            if (wasNextIndexChanged) {
                currentIndex = nextIndex - 1;
            }
//            System.out.println();
//            System.out.println((currentCard.getValue() + 1) + " == " + nextCard.getValue());
            if (currentCard.getValue() + 1 == nextCard.getValue()) {
//                System.out.println(currentCard.getValue() + " added to oneSequence");
                oneSequence.add(currentCard);
                array.remove(currentCard);
                currentIndex --;
//                System.out.println("index " + nextIndex + " == index " + (array.size() - 1));
                if (nextIndex == array.size() - 1) {
//                    System.out.println(nextCard.getValue() + " added to oneSequence");
                    oneSequence.add(nextCard);
                    array.remove(nextCard);
                }
            }
        }

//        System.out.println("oneSequence.size(j) = " + oneSequence.size());
//        System.out.print("oneSequence = [");
//        for (Card card : oneSequence) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("] \n\n");

        return oneSequence;
    }

    public void setSequenceOfSingles() {
        sequenceOfSingles = new ArrayList<ArrayList<Card>>();
        ArrayList<Card> cardArray = new ArrayList<Card>();
        for (Card card : hand.getCardArray()) {
            cardArray.add(card);
        }
        ArrayList<Card> oneSequence = getSequence(cardArray);
        System.out.print("Card Array: [");
        for (Card card : cardArray) {
            System.out.print(card.getValue() + ", ");
        }
        System.out.println("]");
        System.out.println("------------------------------------------------------------------------------------------");

        while (oneSequence.size() >= 5) {
            sequenceOfSingles.add(oneSequence);
            oneSequence = getSequence(cardArray);
            System.out.print("Card Array: [");
            for (Card card : cardArray) {
                System.out.print(card.getValue() + ", ");
            }
            System.out.println("]");
        }

        
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
