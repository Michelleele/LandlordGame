import java.util.ArrayList;

public class Player {

    protected Hand hand;
    private int cashAmt;
    protected boolean isLandlord;
    protected ArrayList<Card[]> singles;
    protected ArrayList<Card[]> pairs;
    protected ArrayList<Card[]> triplets;
    protected ArrayList<Card[]> bomb;
    protected ArrayList<ArrayList<Card>> sequenceOfSingles;

    public Player(ArrayList<Card> h) {
        hand = new Hand(h);
        cashAmt = 100;
        refreshLists();
    }

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

    public ArrayList<Card> getSequence(ArrayList<Card> array, int targetLength) {
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

            while ((previous.getValue() == current.getValue()) && (currentIndex + 1 < array.size())) {
                currentIndex ++;
                current = array.get(currentIndex);
            }

            if (previous.getValue() + 1 == current.getValue()) {
                if (oneSequence.size() == 0) {
                    array.remove(previous);
                    oneSequence.add(previous);
                }
                currentIndex = array.indexOf(current) - 1;
                array.remove(current);
                oneSequence.add(current);
            }
            else {
                if (oneSequence.size() >= targetLength) {
                    return oneSequence;
                }
                else {
                    oneSequence = new ArrayList<Card>();
                }
            }
        }

        if (oneSequence.size() >= targetLength) {
            return oneSequence;
        }
        else {
            return new ArrayList<Card>();
        }
    }

    public void setSequenceOfSingles() {
        sequenceOfSingles = new ArrayList<ArrayList<Card>>();
        ArrayList<Card> cardArray = new ArrayList<Card>();
        for (Card card : hand.getCardArray()) {
            cardArray.add(card);
        }
        ArrayList<Card> oneSequence = getSequence(cardArray, 5);

        while (oneSequence.size() != 0) {
            sequenceOfSingles.add(oneSequence);
            oneSequence = getSequence(cardArray, 5);
        }


    }

    public Hand getHand() {
        return hand;
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



}
