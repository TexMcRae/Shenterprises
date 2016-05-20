import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> pos </b> (static) The current y position.
 * <p><b> HEIGHT </b> (static, final) The height of the game board.
 * <p><b> lives </b> (private) The player's lives.
 * <p><b> input </b> (private) The player's current input.
 * <p><b> y </b> (static) The player's current height, up to Player.HEIGHT.
 * <p><b> timer </b> (private) The timer used to continuously draw to the screen.
 */
public class Player extends JPanel implements ActionListener{
  
  private Graphics2D g2d;
  static int pos;
  static final int HEIGHT = 200;
  private int lives;
  private char input;
  static int y;
  private Timer timer;
  /**
   * The constructor assigns the Player its difficulty and sets up other properties.
   * @param difficulty The difficulty sent in from MathDash and Game.
   */
  public Player(int difficulty){ 
    MathDash.frame.add(this);
    timer = new Timer(0, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
  }
  /**
   * The paintComponent draws simple graphics to the screen.
   * @param g The instance of Graphics used to draw on the JPanel.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.fillOval(100,400-y,30,30);
    g2d.drawLine(0,430,MathDash.frame.getWidth(),430);
  }
  /**
   * The actionPerformed method repaints the screen whenever the timer is fired.
   * @param a The instance of ActionEvent used to determine its origin.
   */
  public void actionPerformed(ActionEvent a) {
    repaint();
  }
  
}