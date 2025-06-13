import java.util.ArrayList;

public class Game {

    private ArrayList<Card> deck;
    private Player user;
    private Bot bot1;
    private Bot bot2;
    private Card[] bottomCards;

    public Game() {
        deck = new ArrayList<Card>();
        buildDeck();
        splitCards();

    }

    public Player getUser() {
        return user;
    }

    public Bot getBot2() {
        return bot2;
    }

    public Bot getBot1() {
        return bot1;
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
        bot1 = new Bot(hand2);
        bot2 = new Bot(hand3);

        System.out.println("___________________________________________________________________");
        bot1.printHand();
        System.out.println("Bot 1 test bot arrays");
        bot1.testArrays();
        System.out.println("___________________________________________________________________");
        bot2.printHand();
        System.out.println("Bot 2 test bot arrays");
        bot2.testArrays();
        System.out.println("___________________________________________________________________");
        user.printHand();
        System.out.println("___________________________________________________________________");

    }

}
