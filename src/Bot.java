import java.util.ArrayList;

public class Bot extends Player {

    public Bot(ArrayList<Card> h) {
        super(h);
    }

    public ArrayList<Card> play(int combinationIndex) {
        ArrayList<Card> array = new ArrayList<Card>();
        // have: boolean array with all the possible combinations, an int array of all the number of cards we have for each card type

        if () {

        }
        return array;
    }

    public int bidForLandlord() {
        if (hand.getCombinationArray()[11] || hand.getCombinationArray()[10]) {
            return 3;
        }
        else {
            return (int) (Math.random() * 2) + 1;
        }
    }


}
