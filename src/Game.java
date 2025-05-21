import java.util.ArrayList;

public class Game {

    private ArrayList<Card> deck;
    private Player user;
    private Player bot1;
    private Player bot2;
    private Card[] bottomCards;

    public Game() {
        deck = new ArrayList<Card>();
        buildDeck();
        splitCards();

    }

    public void buildDeck() {
        ArrayList<Card> array = new ArrayList<Card>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"03", "04", "05", "06", "07", "08", "09", "10", "J", "Q", "K", "A", "02"};
        for (String s : suits) {
            for (String v : values) {
                Card c = new Card(s, v);
                array.add(c);
            }
        }
        array.add(new Card("joker", "black"));
        array.add(new Card("joker", "red"));

        int rand;
        int numOfCards = array.size();
        for (int x = 0; x < numOfCards; x ++) {
            rand = (int) (Math.random() * array.size());
            deck.add(array.remove(rand));
        }

    }

    public static ArrayList<Card> createHand(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int x = 0; x < 17; x ++) {
            hand.add(deck.remove(0));
        }
        return hand;
    }

    public void splitCards() {
        ArrayList<Card> hand1 = createHand(deck);
        ArrayList<Card> hand2 = createHand(deck);
        ArrayList<Card> hand3 = createHand(deck);
        bottomCards = new Card[3];

        for (int x = 0; x < 3; x ++) {
            bottomCards[x] = deck.remove(0);
        }

        user = new Player(hand1);
        bot1 = new Player(hand2);
        bot2 = new Player(hand3);

        System.out.println("___________________________________________________________________");
        user.printHand();
//        System.out.println();
//        user.getCardArray().testCombinationMethods();
        System.out.println("___________________________________________________________________");
        bot1.printHand();
//        System.out.println();
//        bot1.getCardArray().testCombinationMethods();
        System.out.println("___________________________________________________________________");
        bot2.printHand();
//        System.out.println();
//        bot2.getCardArray().testCombinationMethods();
        System.out.println("___________________________________________________________________");

    }

}
