import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 2.0_10.06.2016
 * <b> ~ TOTAL TIME SPENT ~ </b>
 * Ryan: 48h30m (9.75, 12.25, 12, 14.5)
 * Kevin: 32h (8, 8, 7, 9)
 * Max: 32h30m (8.5, 7.5, 8.5, 8)
 * <p><b>Instance Variables:</b>
 * <p><b> x </b> (private) The ball's x-cooordinate.
 * <p><b> y </b> (private) The ball's y-cooordinate.
 * <p><b> value </b> (private) Value of the ball.
 * <p><b> timer </b> (private) The timer used to fire ActionListeners.
 */
public class NumberBall extends JPanel{
  static int x;
  private int y;
  private int value;
  private Timer timer;
   /**
   * The constructor sets up the balls' properties.
   * @param num The value of the ball.
   * @param coordX The ball's x-coordinate.
   * @param coordY The ball's y-coordinate.
   */
  public NumberBall(int num, int coordX,int coordY){
    value = num;
    x = coordX;
    y = coordY;
  } 
  /**
   * Draws this ball to the screen.
   * @param g The instance of graphics used to draw.
   * @param value The value of the ball.
   * @param coordX The ball's x-coordinate.
   * @param coordY The ball's y-coordinate.
   */
  public void draw(Graphics g,int value,int coordX,int coordY) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(Color.white);
    g2d.fillOval(coordX-x,coordY,30,30);
    g2d.setPaint(Color.black);
    g2d.drawString("" + value,(coordX + 10)-x,coordY + 15);
  }
  /**
   * The distance() method checks the distance between the player and the balls.
   * @return The distance between the player and the balls.
   * @param x1 The x-coordinate of the Player.
   * @param y1 The y-coordinate of the Player.
   * @param x2 The x-coordinate of the ball.
   * @param y2 The y-coordinate of the ball.
   */
  static boolean distance(int x1, int y1, int x2, int y2) //in NumberBall
  {//18, 1 & 57, 94 |||
    x2+=15;
    y2+=15;
    if(x2>=x1+3&&x2<=x1+67&&y2>=y1-11&&y2<=y1+104)//changed
      return true;
    int d1 = Math.abs(x1+23-x2);
    int d2 = Math.abs(y1+6-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+52-x2);
    d2 = Math.abs(y1+6-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+52-x2);
    d2 = Math.abs(y1+89-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+23-x2);
    d2 = Math.abs(y1+89-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10||x2>=x1+41&&x2<=x1+87&&y2>=y1+6&&y2<=y1+61)
      return true;
    d1 = Math.abs(x1+61-x2);
    d2 = Math.abs(y1+26-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+61-x2);
    d2 = Math.abs(y1+41-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+67-x2);
    d2 = Math.abs(y1+26-y2);
    if(Math.sqrt(d1*d1+d2*d2)<10)
      return true;
    d1 = Math.abs(x1+67-x2);
    d2 = Math.abs(y1+41-y2);
    return Math.sqrt(d1*d1+d2*d2)<10;
  }
}