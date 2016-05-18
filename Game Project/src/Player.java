import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.0_13.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> pos </b> (static) The current y position.
 * <p><b> HEIGHT </b> (static, final) The height of the game board.
 * <p><b> lives </b> (private) The player's lives.
 * <p><b> input </b> (private) The player's current input.
 */
public class Player extends JPanel implements ActionListener{
  
  private Graphics2D g2d;
  static int pos;
  static final int HEIGHT = 200; //temporarily
  private int lives;
  private char input;
  static int x,y;
  private Timer timer;
  
  /**
   * The constructor assigns the Player its difficulty and sets up other properties.
   * @param difficulty The difficulty sent in from MathDash and Game.
   */
  public Player(int difficulty){ 
    System.out.println("player");
    MathDash.frame.add(this);
    timer = new Timer(100, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    System.out.print(1);
    super.paintComponent(g);
    System.out.println(2);
    Graphics2D g2d = (Graphics2D) g;
    g2d.fillOval(300+y,400-x,30,30);
    g2d.drawLine(300,430,330,430);
  }
  
  public void actionPerformed(ActionEvent e) {
    System.out.print(0);
    repaint();//you need this
  }
  
}