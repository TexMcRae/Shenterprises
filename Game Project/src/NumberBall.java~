import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to dra
 * w all graphics to the screen.
 * <p><b> value </b> (private) Various positions and values for multiple balls.
 */
public class NumberBall{
  private Graphics2D g2d;
  private int [] pos = new int [2];
  private int value;
  /**
   * The constructor sets up the balls' properties.
   */
  public NumberBall(int num){
    value = num;
    timer = new Timer(0, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.fillOval(800-x,400,30,30);
  }
  /**
   * The distance() method checks the distance between the player and the balls.
   * @return The distance between the player and the balls.
   */
  private double distance()
  {
    return 0.0;
  }
  /**
   * Draws the balls.
   */
  public void drawBall(){
    
  }
}