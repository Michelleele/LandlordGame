import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class DrawPanel extends JPanel implements MouseListener {

    private Rectangle placeCardsButton;
    private Rectangle exitCombinationErrorButton;
    private Rectangle skipTurnButton;
    private Game game;
    private Player user;
    private Bot bot1;
    private ArrayList<Card> lastCardsPlaced;
    private ArrayList<Card> selected;
    private int currentCombination;
    private boolean displayCombinationError;
    private boolean validCombination;
    private boolean showBotCards;
    private boolean showUserCards;
    private boolean botThink;
    private boolean botSkippedTurn;


    public DrawPanel() {
        placeCardsButton = new Rectangle(70, 400, 160, 26);
        exitCombinationErrorButton = new Rectangle(490, 185, 130, 40);
        skipTurnButton = new Rectangle(250, 400, 160, 26);
        this.addMouseListener(this);
        newGame();
    }

    public void newGame() {
        game = new Game();
        user = game.getUser();
        bot1 = game.getBot1();
        lastCardsPlaced = new ArrayList<Card>();
        selected = new ArrayList<Card>();
        displayCombinationError = false;
        showBotCards = false;
        showUserCards = false;
        botThink = false;
        botSkippedTurn = false;
    }

    public int playCard(ArrayList<Card> selected) {
        ArrayList<Card> sorted;
        if (selected.size() == 1) {
            return 1;
        }
        if (selected.size() == 2) {
            if (selected.get(0).getValue() == selected.get(1).getValue()) {
                return 2;
            }
        }
        if (selected.size() == 3) {
            if ((selected.get(0).getValue() == selected.get(1).getValue()) && (selected.get(1).getValue() == selected.get(2).getValue())) {
                return 3;
            }
        }
        if (selected.size() == 4) {
            return 4;
        }
        if (selected.size() >= 5) {
            sorted = sortCards(selected);
            for (int x = 0; x < sorted.size() - 1; x++) {
                if (!(sorted.get(x).getValue() + 1 == sorted.get(x + 1).getValue())) {
                    return 0;
                }
            }
            return 5;
        }
        return 0;
    }

    public boolean playCard(ArrayList<Card> selected, int combinationType) {
        ArrayList<Card> sorted;
        if (combinationType == 1) {
            if (selected.size() == 1) {
                return true;
            }
        }
        else if (combinationType == 2) {
            if (selected.size() == 2) {
                return selected.get(0).getValue() == selected.get(1).getValue();
            }
        }
        else if (combinationType == 3) {
            if (selected.size() == 3) {
                return (selected.get(0).getValue() == selected.get(1).getValue()) && (selected.get(1).getValue() == selected.get(2).getValue());
            }
        }
        else if (combinationType == 4) {
            if (selected.size() == 4) {
                return (selected.get(0).getValue() == selected.get(1).getValue()) && (selected.get(1).getValue() == selected.get(2).getValue()) && (selected.get(2).getValue() == selected.get(3).getValue());
            }
        }
        else {
            if (selected.size() >= 5) {
                sorted = sortCards(selected);
                System.out.print("Selected: [");
                for (Card card : selected) {
                    System.out.print(card.getValue() + ", ");
                }
                System.out.println("]");
                for (int x = 0; x < sorted.size() - 1; x ++) {
                    if (!(sorted.get(x).getValue() + 1 == sorted.get(x + 1).getValue())) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Card> sortCards(ArrayList<Card> listToSort) {
        ArrayList<Card> sorted = new ArrayList<Card>();
        boolean isAdded;
        for (Card card : listToSort) {
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
        return sorted;
    }

    public boolean userWon() {
        if (user.hand.getCardArray().size() == 0) {
            return true;
        }
        return false;
    }

    public boolean botWon() {
        if (bot1.hand.getCardArray().size() == 0) {
            return true;
        }
        return false;
    }

    protected void paintComponent(Graphics g) {
        Card exampleCard = new Card("clubs", "09");
        super.paintComponent(g);
        setBackground(Color.getHSBColor(0.57f, 0.22f, 0.85f));

        try {
            Image botImage = ImageIO.read(new File("images/bot.png"));
            g.drawImage(botImage, 50, 30, null);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (botThink) {
            g.setColor(Color.white);
            g.drawRect(170, 30, 80, 40);
            g.fillRect(170, 30, 80, 40);
            g.setColor(Color.black);
            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("···", 190, 60); //print statement on screen
        }

        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("PLACE CARDS", (int)placeCardsButton.getX() + 20, (int)placeCardsButton.getY() + 20); //print statement on screen
        g.drawRect((int) placeCardsButton.getX(), (int)placeCardsButton.getY(), (int)placeCardsButton.getWidth(), (int)placeCardsButton.getHeight());

        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("SKIP TURN", (int)skipTurnButton.getX() + 20, (int)skipTurnButton.getY() + 20); //print statement on screen
        g.drawRect((int) skipTurnButton.getX(), (int)skipTurnButton.getY(), (int)skipTurnButton.getWidth(), (int)skipTurnButton.getHeight());

        if (showUserCards) {
            int x = 30;
            int y;
            for (Card card : lastCardsPlaced) {
                y = 200;
                Card c = card;
                c.setRectangleLocation(x, y);
                //draws the image
                g.drawImage(c.getImage(), x, y, null);
                x += c.getImage().getWidth();
            }
        }

        if (showBotCards) {
            int x = 200;
            int y;
            for (Card card : lastCardsPlaced) {
                y = 80;
                Card c = card;
                c.setRectangleLocation(x, y);
                //draws the image
                g.drawImage(c.getImage(), x, y, null);
                x += c.getImage().getWidth();
            }
        }

        if (botSkippedTurn) {
            g.setColor(Color.white);
            g.drawRect(170, 30, 80, 40);
            g.fillRect(170, 30, 80, 40);
            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.setColor(Color.black);
            g.drawString("Skip", 190, 60); //print statement on screen
        }

        if (displayCombinationError) {
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawRect(70, 175, 600, 100);
            g.setColor(Color.getHSBColor(0.08f, 0.22f, 0.85f));
            g.fillRect(70, 175, 600, 100);
            g.setColor(Color.BLACK);
            g.drawString("INVALID COMBINATION!", 80, 210);
            g.drawString("PLEASE SELECT A NEW COMBINATION", 80, 260);
            //print statement on screen

            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawRect(490, 185, 130, 40);
            g.setColor(Color.getHSBColor(0.08f, 0.22f, 0.85f));
            g.fillRect(490, 185, 130, 40);
            g.setColor(Color.BLACK);
            g.drawString("Got it", 500, 215);
        }

        g.setColor(Color.getHSBColor(0.14f, 0.30f, 0.95f));
        g.fillRect(30, 280, 50 + (user.hand.getCardArray().size() * exampleCard.getImage().getWidth()), 100);
        int x = 50;
        int y;
        for (Card card : user.getHand().getCardArray()) {
            y = 300;
            Card c = card;
            if (c.isSelected()) {
                y -= 20;
            }
            c.setRectangleLocation(x, y);
            //draws the image
            g.drawImage(c.getImage(), x, y, null);
            x += c.getImage().getWidth();
        }
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        for (int i = user.hand.getCardArray().size() - 1; i > -1; i--) {
            Rectangle box = user.hand.getCardArray().get(i).getCardBox();
            if (box.contains(clicked)) {
                if (user.hand.getCardArray().get(i).isSelected()) {
                    user.hand.getCardArray().get(i).setSelected(false);
                    selected.remove(user.hand.getCardArray().get(i));
                    System.out.print("Selected: [");
                    for (Card card : selected) {
                        System.out.print(card.getValue() + ", ");
                    }
                    System.out.println("]");
                    break;
                }
                else {
                    user.hand.getCardArray().get(i).setSelected(true);
                    selected.add(user.hand.getCardArray().get(i));
                    System.out.print("Selected: [");
                    for (Card card : selected) {
                        System.out.print(card.getValue() + ", ");
                    }
                    System.out.println("]");
                    break;
                }
            }
        }

        if (placeCardsButton.contains(clicked)) {
            System.out.print("Selected: [");
            for (Card card : selected) {
                System.out.print(card.getValue() + ", ");
            }
            System.out.println("]");
            if (currentCombination == 0) {
                currentCombination = playCard(selected);
                if (currentCombination != 0) {
                    showUserCards = true;
                    showBotCards = false;
                    displayCombinationError = false;
                    user.removeCards(selected);
                    lastCardsPlaced = selected;
                    //place the cards and display
                    botSkippedTurn = !botPlay();
                    if (botSkippedTurn) {
                        currentCombination = 0;
                    }
                }
                else {
                    showBotCards = true;
                    showUserCards = false;
                    displayCombinationError = true;
                }
            }
            else {
                validCombination = playCard(selected, currentCombination);
                if (validCombination) {
                    showUserCards = true;
                    showBotCards = false;
                    displayCombinationError = false;
                    user.removeCards(selected);
                    lastCardsPlaced = selected;
                    botSkippedTurn = !botPlay();
                    if (botSkippedTurn) {
                        currentCombination = 0;
                    }

                }
                else {
                    showBotCards = true;
                    showUserCards = false;
                    displayCombinationError = true;
                }
            }
            selected = new ArrayList<Card>();

        }

        if (exitCombinationErrorButton.contains(clicked)) {
            displayCombinationError = false;
        }

        if (skipTurnButton.contains(clicked)) {
            botThink = true;
            showBotCards = false;
            repaint();
            ArrayList<Card> botCards = playerSkippedTurn();
            bot1.removeCards(botCards);
            if (botCards != null) {
                new javax.swing.Timer(3000, evt -> {
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();
                }).start();
            }
        }
    }

    public ArrayList<Card> playerSkippedTurn() {
        if (bot1.sequenceOfSingles.size() > 0) {
            return bot1.getSequenceCard();
        }
        else if (bot1.bomb.size() > 0) {
            return bot1.getQuadCard();
        }
        else if (bot1.triplets.size() > 0) {
            return bot1.getTrpiletCard();
        }
        else if (bot1.pairs.size() > 0) {
            return bot1.getPairCard();
        }
        else {
            return bot1.getSingleCard();
        }
    }

    public boolean botPlay() {
        ArrayList<Card> botCards;
        botThink = true;
        showBotCards = false;
        repaint();

        if (currentCombination == 1) {
            botCards = bot1.getSingleCard(lastCardsPlaced);
            if (botCards != null) {
                bot1.removeCards(botCards);
                new javax.swing.Timer(3000, evt -> {
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();  // stop the timer
                }).start();
                return true;
            }
            else {
                return false;
            }
        }
        else if (currentCombination == 2) {
            botCards = bot1.getPairCard(lastCardsPlaced);
            if (botCards != null) {

                bot1.removeCards(botCards);
                new javax.swing.Timer(3000, evt -> {
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();  // stop the timer
                }).start();
                return true;
            }
            else {
                return false;
            }
        }
        else if (currentCombination == 3) {
            botCards = bot1.getTripletCard(lastCardsPlaced);
            if (botCards != null) {
                bot1.removeCards(botCards);
                // Step 2: Start a timer to switch after 3 seconds
                new javax.swing.Timer(3000, evt -> {
                    // Hide popup A and show popup B
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();  // stop the timer
                }).start();
                return true;
            }
            else {
                return false;
            }
        }
        else if (currentCombination == 4){
            botCards = bot1.getQuadCard(lastCardsPlaced);
            if (botCards != null) {

                bot1.removeCards(botCards);
                // Step 2: Start a timer to switch after 3 seconds
                new javax.swing.Timer(3000, evt -> {
                    // Hide popup A and show popup B
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();  // stop the timer
                }).start();
                return true;
            }
            else {
                return false;
            }
        }
        else {
            botCards = bot1.getSequenceCard(lastCardsPlaced);
            if (botCards != null) {

                bot1.removeCards(botCards);
                // Step 2: Start a timer to switch after 3 seconds
                new javax.swing.Timer(3000, evt -> {
                    // Hide popup A and show popup B
                    botThink = false;
                    showBotCards = true;
                    showUserCards = false;
                    lastCardsPlaced = botCards;
                    bot1.removeCards(botCards);
                    repaint();
                    ((javax.swing.Timer) evt.getSource()).stop();  // stop the timer
                }).start();
                return true;
            }
            else {
                return false;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

}