import java.util.ArrayList;

public class Bot extends Player{

    public Bot(ArrayList<Card> h) {
        super(h);
    }

    public boolean play() {

    }

    public int bidForLandlord() {
        if (hand.haveRocket() || hand.haveBomb()) {
            return 3;
        }
        else {
            return (int) (Math.random() * 2) + 1;
        }

    }

}
