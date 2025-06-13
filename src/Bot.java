import java.util.ArrayList;

public class Bot extends Player {

    public Bot(ArrayList<Card> h) {
        super(h);
        isBot = true;
    }

    public ArrayList<Card> getSingleCard(Card[] singlePlaced) {
        Card previousPlacedCard = singlePlaced[0];
        Card card;
        for (Card[] cardArray : singles) {
           card = cardArray[0];
           if (card.getValue() > previousPlacedCard.getValue()) {
               ArrayList<Card> n = new ArrayList<Card>();
               n.add(card);
               return n;
           }
        }
        return null;
    }

    public ArrayList<Card> getPairCard(Card[] pairPlaced) {
        Card[] previousPlacedCard = pairPlaced;
        Card card;
        for (Card[] cardArray : pairs) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard[0].getValue()) {
                ArrayList<Card> n = new ArrayList<Card>();
                for (Card c : cardArray) {
                    n.add(c);
                }
                return n;
            }
        }
        return null;
    }

    public ArrayList<Card> getTripletCard(Card[] tripletPlaced) {
        Card[] previousPlacedCard = tripletPlaced;
        Card card;
        for (Card[] cardArray : triplets) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard[0].getValue()) {
                ArrayList<Card> n = new ArrayList<Card>();
                for (Card c : cardArray) {
                    n.add(c);
                }
                return n;
            }
        }
        return null;
    }

    public ArrayList<Card> getQuadCard(Card[] quadPlaced) {
        Card[] previousPlacedCard = quadPlaced;
        Card card;
        for (Card[] cardArray : bomb) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard[0].getValue()) {
                ArrayList<Card> n = new ArrayList<Card>();
                for (Card c : cardArray) {
                    n.add(c);
                }
                return n;
            }
        }
        return null;
    }

    public ArrayList<Card> getSequenceCard(ArrayList<Card> sequencePlaced) {
        Card firstOfPlaced = sequencePlaced.get(0);
        ArrayList<Card> foundArray;
        for (ArrayList<Card> sequence : sequenceOfSingles) {
            foundArray = new ArrayList<Card>();
            for (Card card : sequence) {
                if ((card.getValue() > firstOfPlaced.getValue()) && (foundArray.size() < sequencePlaced.size())) {
                    foundArray.add(card);
                }
            }
            if (foundArray.size() == sequencePlaced.size()) {
                return foundArray;
            }
        }
        return null;
    }

    public int landlord() {
        if (bomb.size() >= 1) {
            return 3;
        }
        else {
            return (int) (Math.random() * 2) + 1;
        }
    }


}