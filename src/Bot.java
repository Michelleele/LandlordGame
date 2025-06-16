import java.util.ArrayList;

public class Bot extends Player {

    public Bot(ArrayList<Card> h) {
        super(h);
        isBot = true;
    }

    public ArrayList<Card> getSingleCard() {
        int rand = (int) (Math.random() * singles.size());
        ArrayList<Card> x = new ArrayList<Card>();
        for (Card card : singles.get(rand)) {
            x.add(card);
        }
        return x;
    }

    public ArrayList<Card> getPairCard() {
        int rand = (int) (Math.random() * pairs.size());
        ArrayList<Card> x = new ArrayList<Card>();
        for (Card card : pairs.get(rand)) {
            x.add(card);
        }
        return x;
    }

    public ArrayList<Card> getTrpiletCard() {
        int rand = (int) (Math.random() * triplets.size());
        ArrayList<Card> x = new ArrayList<Card>();
        for (Card card : triplets.get(rand)) {
            x.add(card);
        }
        return x;
    }

    public ArrayList<Card> getQuadCard() {
        int rand = (int) (Math.random() * bomb.size());
        ArrayList<Card> x = new ArrayList<Card>();
        for (Card card : bomb.get(rand)) {
            x.add(card);
        }
        return x;
    }

    public ArrayList<Card> getSequenceCard() {
        int rand = (int) (Math.random() * sequenceOfSingles.size());
        ArrayList<Card> x = new ArrayList<Card>();
        for (Card card : sequenceOfSingles.get(rand)) {
            x.add(card);
        }
        return x;
    }

    public ArrayList<Card> getSingleCard(ArrayList<Card> array) {
        Card previousPlacedCard = array.get(0);
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

    public ArrayList<Card> getPairCard(ArrayList<Card> array) {
        ArrayList<Card> previousPlacedCard = array;
        Card card;
        for (Card[] cardArray : pairs) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard.get(0).getValue()) {
                ArrayList<Card> n = new ArrayList<Card>();
                for (Card c : cardArray) {
                    n.add(c);
                }
                return n;
            }
        }
        return null;
    }

    public ArrayList<Card> getTripletCard(ArrayList<Card> array) {
        ArrayList<Card> previousPlacedCard = array;
        Card card;
        for (Card[] cardArray : triplets) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard.get(0).getValue()) {
                ArrayList<Card> n = new ArrayList<Card>();
                for (Card c : cardArray) {
                    n.add(c);
                }
                return n;
            }
        }
        return null;
    }

    public ArrayList<Card> getQuadCard(ArrayList<Card> array) {
        ArrayList<Card> previousPlacedCard = array;
        Card card;
        for (Card[] cardArray : bomb) {
            card = cardArray[0];
            if (card.getValue() > previousPlacedCard.get(0).getValue()) {
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
                if (card.getValue() > firstOfPlaced.getValue()) {
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