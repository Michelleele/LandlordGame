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
    private int[] cardCountArray;

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
        cardCountArray = new int[]
                {numOf3, numOf4, numOf5, numOf6, numOf7, numOf8, numOf9, numOf10,
                        numOfJ, numOfQ, numOfK, numOfA, numOf2, numOfJoker};
    }

    public ArrayList<Card> quadsList() {
        ArrayList<Card> array = new ArrayList<>();
        int count;
        for (int index = 0; index < cardCountArray.length; index ++) {
            if (cardCountArray[index] == 4) {
                count = 0;
                for (int x = 0; x < index; x ++) {
                    count += cardCountArray[x];
                }
                array.add(hand.get(count));
            }
        }
//        System.out.println("test run triplets list");
//        System.out.print("[");
//        for (Card card : array) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("]");
        return array;
    }

    public ArrayList<Card> singlesList() {
        ArrayList<Card> array = new ArrayList<>();
        int count;
        for (int index = 0; index < cardCountArray.length; index ++) {
            if (cardCountArray[index] == 1) {
                count = 0;
                for (int x = 0; x < index; x ++) {
                    count += cardCountArray[x];
                }
                array.add(hand.get(count));
            }
        }
//
//        System.out.println("test run singles list");
//        System.out.print("[");
//        for (Card card : array) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("]");

        return array;
    }

    public ArrayList<Card> pairsList() {
        ArrayList<Card> array = new ArrayList<>();
        int count;
        for (int index = 0; index < cardCountArray.length; index++) {
            if (cardCountArray[index] == 2 && index != 13) {
                count = 0;
                for (int x = 0; x < index; x++) {
                    count += cardCountArray[x];
                }
                array.add(hand.get(count));
            }
        }
//        System.out.println("test run pairs list");
//        System.out.print("[");
//        for (Card card : array) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("]");
        return array;
    }

    public ArrayList<Card> tripletsList() {
        ArrayList<Card> array = new ArrayList<>();
        int count;
        for (int index = 0; index < cardCountArray.length; index ++) {
            if (cardCountArray[index] == 3) {
                count = 0;
                for (int x = 0; x < index; x ++) {
                    count += cardCountArray[x];
                }
                array.add(hand.get(count));
            }
        }
//        System.out.println("test run triplets list");
//        System.out.print("[");
//        for (Card card : array) {
//            System.out.print(card.getValue() + ", ");
//        }
//        System.out.println("]");
        return array;
    }

    public boolean haveSingles() {
        ArrayList<Card> array = singlesList();
        if (array.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean havePairs() {
        ArrayList<Card> array = pairsList();
        if (array.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean haveTriplets() {
        ArrayList<Card> array = tripletsList();
        if (array.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean haveTripletWithAttachedCard() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> singleArray = singlesList();
        for (Card card : tripletArray) {
            for (Card c : singleArray) {
                if (card.getValue() != c.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean haveTripletWithAttachedPair() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> pairArray = pairsList();
        for (Card card : tripletArray) {
            for (Card c : pairArray) {
                if (card.getValue() != c.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean haveSequence() {
        ArrayList<Card> singlesList = singlesList();
        ArrayList<Card> array = new ArrayList<Card>();
        int previousVal = singlesList.get(0).getValue();
        Card current;
        array.add(singlesList.get(0));
        for (int index = 1; index < singlesList.size(); index ++) {
            current = singlesList.get(index);
            if ((current.getValue() - 1 == previousVal) && (current.getValue() != 15) && (current.getValue() != 16) && (current.getValue() != 17)) {
                array.add(current);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = current.getValue();
        }

        if (array.size() >= 5) {
            return true;
        }
        return false;
    }

    public boolean haveSequenceOfPairs() {
        ArrayList<Card> pairList = pairsList();
        ArrayList<Card> array = new ArrayList<Card>();
        int previousVal = pairList.get(0).getValue();
        Card current;
        array.add(pairList.get(0));
        for (int index = 1; index < pairList.size(); index ++) {
            current = pairList.get(index);
            if ((current.getValue() - 1 == previousVal) && (current.getValue() != 15) && (current.getValue() != 16) && (current.getValue() != 17)) {
                array.add(current);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = current.getValue();
        }

        if (array.size() >= 3) {
            return true;
        }
        return false;
    }

    public boolean haveSequenceOfTriplets() {
        ArrayList<Card> tripletList = tripletsList();
        ArrayList<Card> array = new ArrayList<Card>();
        int previousVal = tripletList.get(0).getValue();
        Card current;
        array.add(tripletList.get(0));
        for (int index = 1; index < tripletList.size(); index ++) {
            current = tripletList.get(index);
            if ((current.getValue() - 1 == previousVal) && (current.getValue() != 15)) {
                array.add(current);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = current.getValue();
        }

        if (array.size() >= 2) {
            return true;
        }
        return false;
    }

    public boolean haveSequenceOFTripletWithAttachedCard() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> singleArray = singlesList();
        ArrayList<Card> array = new ArrayList<Card>();

        int previousVal = tripletArray.get(0).getValue();
        array.add(tripletArray.get(0));
        Card current;

        for (int index = 1; index < tripletArray.size(); index ++) {
            current = tripletArray.get(index);
            if ((current.getValue() - 1 == previousVal) && (current.getValue() != 15)) {
                array.add(current);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = current.getValue();
        }

        if (array.size() >= 2) {
            int count = 0;
            for (Card card : singleArray) {
                for (Card c : array) {
                    if (card.getValue() != c.getValue()) {
                        count ++;
                    }
                }
            }
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    public boolean haveSequenceOFTripletWithAttachedPair() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> pairArray = pairsList();
        ArrayList<Card> array = new ArrayList<Card>();

        int previousVal = tripletArray.get(0).getValue();
        array.add(tripletArray.get(0));
        Card current;

        for (int index = 1; index < tripletArray.size(); index ++) {
            current = tripletArray.get(index);
            if ((current.getValue() - 1 == previousVal) && (current.getValue() != 15)) {
                array.add(current);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = current.getValue();
        }

        if (array.size() >= 2) {
            int count = 0;
            for (Card card : pairArray) {
                for (Card c : array) {
                    if ((card.getValue() != c.getValue()) && (card.getValue() != 17) && (card.getValue() != 18)) {
                        count ++;
                    }
                }
            }
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    public boolean haveBomb() {
        ArrayList<Card> array = quadsList();
        if (array.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean haveRocket() {
        return numOfJoker == 2;
    }

    public boolean haveQuadWith2AttachedCards() {
        ArrayList<Card> quadArray = quadsList();
        ArrayList<Card> singleArray = singlesList();
        boolean jokerSelected = false;
        int count = 0;
        for (Card card : quadArray) {
            for (Card c : singleArray) {
                if (card.getValue() != c.getValue()) {
                    if ((card.getValue() == 16) || (card.getValue() == 17)) {
                        if (!jokerSelected) {
                            jokerSelected = true;
                            count ++;
                        }
                    }
                    else {
                        count ++;
                    }
                }
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

//    public void testCombinationMethods() {
//        System.out.println();
//        System.out.println("If player have single cards: " + haveSingles());
//        System.out.println("If player have paired cards: " + havePairs());
//        System.out.println("If player have triplet cards: " + haveTriplets());
//
//        System.out.println("If player have triplet with single: " + haveTripletWithAttachedCard());
//        System.out.println("If player have triplet with pair: " + haveTripletWithAttachedPair());
//
//        System.out.println("If player have sequence: " + haveSequence());
//        System.out.println("If player have sequence of pairs: " + haveSequenceOfPairs());
//        System.out.println("If player have sequence of triplets: " + haveSequenceOfTriplets());
//
//        System.out.println("If player have sequence of triplets with attached card: " + haveSequenceOFTripletWithAttachedCard());
//        System.out.println("If player have sequence of triplets with attached pair: " + haveSequenceOFTripletWithAttachedPair());
//        System.out.println("If player have bomb: " + haveBomb());
//        System.out.println("If player have rocket: " + haveRocket());
//        System.out.println("If player have quad with 2 singles attached: " + haveQuadWith2AttachedCards());
//        System.out.println("If player have quad with 2 pairs attached: " + haveQuadWith2AttachedPair());
//        System.out.println();
//    }

    public boolean haveQuadWith2AttachedPair() {
        ArrayList<Card> quadArray = quadsList();
        ArrayList<Card> pairArray = pairsList();
        boolean jokerSelected = false;
        int count = 0;
        for (Card card : quadArray) {
            for (Card c : pairArray) {
                if (card.getValue() != c.getValue()) {
                    if ((card.getValue() == 16) || (card.getValue() == 17)) {
                        if (!jokerSelected) {
                            jokerSelected = true;
                            count ++;
                        }
                    }
                    else {
                        count ++;
                    }
                }
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
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
