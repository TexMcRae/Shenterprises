import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.3_03.06.2016
 * <p><b>Instance Variables:</b>
 * <p><b> pos </b> (static) The current y position.
 * <p><b> HEIGHT </b> (static, final) The height of the game board.
 * <p><b> y, width, height </b> (static) The player's current elevation, width, and height in pixels (The image width+height).
 * <p><b> timer </b> (private) The timer used to continuously draw to the screen.
 */
public class Player extends JPanel{
  static int pos;
  static final int HEIGHT = 200;
  static int y,width,height;
  static Timer timer;
  /**
   * The paintComponent draws simple graphics to the screen.
   * @param g The instance of Graphics used to draw on the JPanel.
   */
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img2 = Toolkit.getDefaultToolkit().getImage("./resources/Player.png");
    height = img2.getHeight(null);
    width = img2.getWidth(null);
    g2d.drawImage(img2, 100, (530-height) - y, this);
    g2d.finalize();
    g2d.setPaint(Color.black);
  }
  
}