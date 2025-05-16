import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class Card {

    private String suit;
    private String val;
    private String imageFileName;
    private BufferedImage image;
    private Rectangle cardBox;
    private int value;

    public Card(String suit, String value) {
        this.suit = suit;
        val = value;
        this.imageFileName = "images/card_"+suit+"_"+value+".png";

        //one buffer Image object associated with card
        //which image should I show with the card
        //front or back image
        this.image = readImage();
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        setIntVal();
    }

    public int getValue() {
        return value;
    }

    public void setIntVal() {
        if (val.equals("02")) {
            value = 15;
            return;
        }
        try {
            value = Integer.parseInt(val);
        }
        catch (NumberFormatException e) {
            if (val.equals("A")) {
                value = 14;
            }
            else if (val.equals("J")) {
                value = 11;
            }
            else if (val.equals("K")) {
                value = 13;
            }
            else if (val.equals("Q")) {
                value = 12;
            }
            else if (val.equals("red")) {
                value = 17;
            }
            else if (val.equals("black")) {
                value = 16;
            }
        }
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
