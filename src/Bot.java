import java.util.ArrayList;

public class Bot extends Player {

    private ArrayList<Card[]> singles;
    private ArrayList<Card[]> pairs;
    private ArrayList<Card[]> triplets;
    private ArrayList<Card[]> bomb;
    private ArrayList<ArrayList<Card>> sequenceOfSingles;

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

    public ArrayList<Card> getSequence(ArrayList<Card> array, int targetlength) {

        System.out.print("array check: [");
        for (Card card : array) {
            System.out.print(card.getValue() + ", ");
        }
        System.out.println("]");

        Card previous;
        Card current = array.get(0);
        ArrayList<Card> oneSequence = new ArrayList<Card>();

        for (int currentIndex = 1; currentIndex < array.size(); currentIndex ++) {

            if (oneSequence.size() == 0) {
                previous = current;
            }
            else {
                previous = oneSequence.get(oneSequence.size() - 1);
            }

            current = array.get(currentIndex);

            System.out.println("currentIndex = " + currentIndex);
            System.out.println("current card = " + current.getValue());
            System.out.println("previous card = " + previous.getValue());
            System.out.println();

            while ((previous.getValue() == current.getValue()) && (currentIndex + 1 < array.size())) {
                currentIndex ++;
                System.out.println("\nindex added to: " + currentIndex + "\n");
                current = array.get(currentIndex);
            }

            if (previous.getValue() + 1 == current.getValue()) {
                if (oneSequence.size() == 0) {
                    currentIndex = array.indexOf(previous) - 1;
                    System.out.println("\nindex changed to: " + currentIndex + "\n");
                    System.out.println("\n" + previous.getValue() + " removed1\n");
                    array.remove(previous);
                    oneSequence.add(previous);
                    System.out.println("\n" + current.getValue() + " removed2\n");
                    array.remove(current);
                    oneSequence.add(current);
                }
                else {
                    currentIndex = array.indexOf(current) - 1;
                    array.remove(current);
                    oneSequence.add(current);
                }

                System.out.print("array check: [");
                for (Card card : array) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.println("]");
            }
            else {
                System.out.print("one1Sequence check: [");
                for (Card card : oneSequence) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.println("]");

                if (oneSequence.size() >= targetlength) {
                    return oneSequence;
                }
                else {
                    System.out.println("returned empty here 1");
                    oneSequence = new ArrayList<Card>();
                }
            }
        }
        if (oneSequence.size() >= targetlength) {
            System.out.print("oneSequence check: [");
            for (Card card : oneSequence) {
                System.out.print(card.getValue() + ", ");
            }
            System.out.println("]");
            return oneSequence;
        }
        else {
            System.out.println("returned empty here 2");
            return new ArrayList<Card>();
        }
    }

//    public boolean containsSequence(ArrayList<Card> array, int targetLength) {
//        int sequenceLength = 0;
//        for (int card1Index = 0; card1Index < array.size(); card1Index ++) {
//            for (int card2Index = card1Index + 1; card2Index < array.size(); card2Index ++) {
//                System.out.println("card1: " + array.get(card1Index).getValue() + ", card2: " + array.get(card2Index).getValue());
//                while ((card2Index < array.size()) && (array.get(card1Index).getValue() == array.get(card2Index).getValue())) {
//                    System.out.println("card2: " + array.get(card2Index).getValue());
//                    card2Index++;
//                }
//                System.out.println("if " + array.get(card1Index).getValue() + " == " + array.get(card2Index).getValue());
//                if ((card2Index < array.size()) && (array.get(card1Index).getValue() + 1 == array.get(card2Index).getValue())) {
//                    System.out.println("yes \n");
//                    sequenceLength++;
//                }
//                else {
//                    sequenceLength = 0;
//                    card2Index = array.size();
//                }
//            }
//        }
//
//        if (sequenceLength >= targetLength) {
//            System.out.println("returned true");
//            return true;
//        }
//        else {
//            System.out.println("returned false");
//            return false;
//        }
//    }

    public void setSequenceOfSingles() {
        sequenceOfSingles = new ArrayList<ArrayList<Card>>();
        ArrayList<Card> cardArray = new ArrayList<Card>();
        for (Card card : hand.getCardArray()) {
            cardArray.add(card);
        }
        ArrayList<Card> oneSequence = getSequence(cardArray, 5);
//        System.out.print("Card Array: [");
//        for (Card card : cardArray) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("]");
//        System.out.println("------------------------------------------------------------------------------------------");


        while (oneSequence.size() != 0) {
            sequenceOfSingles.add(oneSequence);
            oneSequence = getSequence(cardArray, 5);
//            System.out.print("Card Array: [");
//            for (Card card : cardArray) {
//                System.out.print(card.getValue() + ", ");
//            }
//            System.out.println("]");
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
