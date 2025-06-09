import java.util.ArrayList;

public class BotRunner {
    public static void main(String[] args) {
        Card c1 = new Card("clubs", "03");
        Card c2 = new Card("clubs", "05");
        Card c3 = new Card("clubs", "06");
        Card c4 = new Card("clubs", "07");
        Card c5 = new Card("clubs", "08");
        Card c6 = new Card("clubs", "09");
        Card c7 = new Card("clubs", "09");
        Card c8 = new Card("clubs", "09");
        Card c9 = new Card("clubs", "10");
        Card c10 = new Card("clubs", "10");
        Card c11 = new Card("clubs", "J");
        Card c12 = new Card("clubs", "J");
        Card c13 = new Card("clubs", "Q");
        Card c14 = new Card("clubs", "K");
        Card c15 = new Card("clubs", "A");
        Card c16 = new Card("clubs", "A");
        Card c17 = new Card("clubs", "A");

        ArrayList<Card> h = new ArrayList<Card>();
        h.add(c1);
        h.add(c2);
        h.add(c3);
        h.add(c4);
        h.add(c5);
        h.add(c6);
        h.add(c7);
        h.add(c8);
        h.add(c9);
        h.add(c10);
        h.add(c11);
        h.add(c12);
        h.add(c13);
        h.add(c14);
        h.add(c15);
        h.add(c16);
        h.add(c17);

        Bot b = new Bot(h);
        b.printHand();
        b.testArrays();
    }

}
