import java.util.ArrayList;

public class Game {

    private ArrayList<Card> deck;

    public Game() {
        buildDeck();
        testPrintDeck();
    }

    public void testPrintDeck() {
        for (Card card : deck) {
            System.out.println(card.getValue());
        }
    }

    public void buildDeck() {
        ArrayList<Card> array = new ArrayList<Card>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
        for (String s : suits) {
            for (String v : values) {
                Card c = new Card(s, v);
                array.add(c);
            }
        }

        int rand;
        int numOfCards = array.size();
        for (int x = 0; x < numOfCards; x ++) {
            rand = (int) (Math.random() * array.size());
            deck.add(array.remove(rand));
        }
    }

}
