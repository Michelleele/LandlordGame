import java.util.ArrayList;

public class Bot extends Player {

    //singles, doubles, triplets, quads, sequence
    ArrayList<Card> cardArray;

    public Bot(ArrayList<Card> h) {
        super(h);
        cardArray = hand.getCardArray();
    }

    public boolean isFound(ArrayList<Card> array, int value) {
        for (Card card : array) {
            if (card.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public Card get(ArrayList<Card> array, int value) {
        for (Card card : array) {
            if (card.getValue() == value) {
                return card;
            }
        }
        return null;
    }

    public ArrayList<Card> getSequence(ArrayList<Card> array) {

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

                if (oneSequence.size() >= 5) {
                    return oneSequence;
                }
                else {
                    System.out.println("returned empty here 1");
                    oneSequence = new ArrayList<Card>();
                }
            }
        }
        if (oneSequence.size() >= 5) {
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

}
