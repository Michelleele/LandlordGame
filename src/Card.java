import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Card {

    private String suit;
    private String value;
    private String imageFileName;
    private BufferedImage image;
    private Rectangle cardBox;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.imageFileName = "images/card_"+suit+"_"+value+".png";

        //one buffer Image object associated with card
        //which image should I show with the card
        //front or back image
        this.image = readImage();
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
    }

    public BufferedImage getImage() {
        return image;
    }

    //a BufferedImage object is an object that represents an image file
    public BufferedImage readImage() {
        try {
            BufferedImage image;
            // if show, show front of card
            //else, show back of card
            image = ImageIO.read(new File(imageFileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public void setRectangleLocation(int x, int y) {
        cardBox.setLocation(x, y);
    }

}
