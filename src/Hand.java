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
    private boolean[] combinationArray;

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
        combinationArray = new boolean[14];
        sortCards();
        countCards();
        setCombinationArray();
    }

    public void setCombinationArray() {
        haveSingles();
        havePairs();
        haveTriplets();
        haveTripletWithAttachedCard();
        haveTripletWithAttachedPair();
        haveSequence();
        haveSequenceOfPairs();
        haveSequenceOfTriplets();
        haveSequenceOFTripletWithAttachedCard();
        haveSequenceOFTripletWithAttachedPair();
        haveBomb();
        haveRocket();
        haveQuadWith2AttachedCards();
        haveQuadWith2AttachedPair();
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

    public void haveSingles() {
        ArrayList<Card> array = singlesList();
        if (array.size() > 0) {
            combinationArray[0] = true;
        }
    }

    public void havePairs() {
        ArrayList<Card> array = pairsList();
        if (array.size() > 0) {
            combinationArray[1] = true;
        }
    }

    public void haveTriplets() {
        ArrayList<Card> array = tripletsList();
        if (array.size() > 0) {
            combinationArray[2] = true;
        }
    }

    public void haveTripletWithAttachedCard() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> singleArray = singlesList();
        for (Card card : tripletArray) {
            for (Card c : singleArray) {
                if (card.getValue() != c.getValue()) {
                    combinationArray[3] = true;
                }
            }
        }
    }

    public void haveTripletWithAttachedPair() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> pairArray = pairsList();
        for (Card card : tripletArray) {
            for (Card c : pairArray) {
                if (card.getValue() != c.getValue()) {
                    combinationArray[4] = true;
                }
            }
        }
    }

    public void haveSequence() {
        ArrayList<Card> singlesList = singlesList();
        ArrayList<Card> array = new ArrayList<Card>();
        try {
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
                combinationArray[5] = true;
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }

    public void haveSequenceOfPairs() {
        ArrayList<Card> pairList = pairsList();
        ArrayList<Card> array = new ArrayList<Card>();
        try {
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
                combinationArray[6] = true;
            }
        }
        catch (IndexOutOfBoundsException e) {
        }

    }

    public void haveSequenceOfTriplets() {
        ArrayList<Card> tripletList = tripletsList();
        ArrayList<Card> array = new ArrayList<Card>();
        try {
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
                combinationArray[7] = true;
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }

    public void haveSequenceOFTripletWithAttachedCard() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> singleArray = singlesList();
        ArrayList<Card> array = new ArrayList<Card>();

        try {
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
                    combinationArray[8] = true;
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }

    public void haveSequenceOFTripletWithAttachedPair() {
        ArrayList<Card> tripletArray = tripletsList();
        ArrayList<Card> pairArray = pairsList();
        ArrayList<Card> array = new ArrayList<Card>();

        try {
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
                    combinationArray[9] = true;
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
        }

    }

    public void haveBomb() {
        ArrayList<Card> array = quadsList();
        if (array.size() > 0) {
            combinationArray[10] = true;
        }
    }

    public void haveRocket() {
        combinationArray[11] = numOfJoker == 2;
    }

    public void haveQuadWith2AttachedCards() {
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
            combinationArray[12] = true;
        }
    }

    public void testCombinationMethods() {
        System.out.println();
        System.out.println("If player have single cards: " + combinationArray[0]);
        System.out.println("If player have paired cards: " + combinationArray[1]);
        System.out.println("If player have triplet cards: " + combinationArray[2]);

        System.out.println("If player have triplet with single: " + combinationArray[3]);
        System.out.println("If player have triplet with pair: " + combinationArray[4]);

        System.out.println("If player have sequence: " + combinationArray[5]);
        System.out.println("If player have sequence of pairs: " + combinationArray[6]);
        System.out.println("If player have sequence of triplets: " + combinationArray[7]);

        System.out.println("If player have sequence of triplets with attached card: " + combinationArray[8]);
        System.out.println("If player have sequence of triplets with attached pair: " + combinationArray[9]);
        System.out.println("If player have bomb: " + combinationArray[10]);
        System.out.println("If player have rocket: " + combinationArray[11]);
        System.out.println("If player have quad with 2 singles attached: " + combinationArray[12]);
        System.out.println("If player have quad with 2 pairs attached: " + combinationArray[13]);
        System.out.println();
    }

    public boolean[] getCombinationArray() {
        return combinationArray;
    }

    public void haveQuadWith2AttachedPair() {
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
            combinationArray[13] = true;
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
