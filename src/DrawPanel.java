import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

class DrawPanel extends JPanel implements MouseListener {

    private Rectangle placeCardsButton;
    private Game game;
    private Player user;
    private Bot bot1;
    private Bot bot2;

    public DrawPanel() {
        placeCardsButton = new Rectangle(67, 26, 160, 26);
        this.addMouseListener(this);
        game = new Game();
        user = game.getUser();
        bot1 = game.getBot1();
        bot2 = game.getBot2();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("PLAY AGAIN", (int)placeCardsButton.getX() + 20, (int)placeCardsButton.getY() + 20); //print statement on screen
        g.drawRect((int) placeCardsButton.getX(), (int)placeCardsButton.getY(), (int)placeCardsButton.getWidth(), (int)placeCardsButton.getHeight());

        int x = 30;
        for (Card card : user.getHand().getCardArray()) {
            Card c = card;
            c.setRectangleLocation(x, 100);

            //draws the image
            g.drawImage(c.getImage(), x, 100, null);
            x += c.getImage().getWidth() - 50;
        }

    }

    public void mousePressed(MouseEvent e) {

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