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

    public ArrayList<Card> getHand() {
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
        int numCount = 1;
        for (int cardCount : cardCountArray) {
            if (cardCount == 1) {
                numCount ++;
            }
            else {
                numCount = 0;
            }
        }
        if (numCount >= 5) {
            return true;
        }
        return false;
    }

    public boolean haveSequenceOfPairs() {
        int numCount = 1;
        for (int cardCount : cardCountArray) {
            if ((cardCount == 2) && () && ()) {
                numCount ++;
            }
            else {
                numCount = 0;
            }
        }
        if (numCount >= 3) {
            return true;
        }
        return false;
    }

    public boolean haveSequenceOfTriplets() {
        int numCount = 1;
        for (int cardCount : cardCountArray) {
            if (cardCount == 3) {
                numCount ++;
            }
            else {
                numCount = 0;
            }
        }
        if (numCount >= 2) {
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
        for (Card card : tripletArray) {
            if (card.getValue() - 1 == previousVal) {
                array.add(card);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = card.getValue();
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
        for (Card card : tripletArray) {
            if (card.getValue() - 1 == previousVal) {
                array.add(card);
            }
            else {
                array = new ArrayList<Card>();
            }
            previousVal = card.getValue();
        }
        if (array.size() >= 2) {
            int count = 0;
            for (Card card : pairArray) {
                for (Card c : array) {
                    if ((card.getValue() != c.getValue()) && (card.getValue() != )) {
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
