import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.2_27.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to dra
 * w all graphics to the screen.
 * <p><b> value </b> (private) Various positions and values for multiple balls.
 */
public class NumberBall extends JPanel{
  private Graphics2D g2d;
  private int [] pos = new int [2];
  static int x;
  private int value;
  static int coordX;
  static int coordY;
  private Timer timer;
   /**
   * The constructor sets up the balls' properties.
   * @param num The value to be given to the NumberBall.
   * @param coordX The NumberBall's x-coordinate.
   * @param coordY The NumberBall's y-coordinate.
   */
  public NumberBall(int num, int coordX,int coordY){
    value = num;
    this.coordX = coordX;
    this.coordY = coordY;
    System.out.println(value);
  }
  /**
   * The paintComponent method draws simple graphics to the screen.
   * @param g The instance of Graphics used to draw on the JPanel.
   * @param value The value held by the NumberBall.
   * @param coordX The NumberBall's x-coordinate.
   * @param coordY The NumberBall's y-coordinate.
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
   * @param x1 The x-coordinate of the player.
   * @param y1 The x-coordinate of the player.
   * @param x2 The x-coordinate of the ball.
   * @param y2 The x-coordinate of the ball.
   * @return The distance between the player and the balls.
   */
  static double distance(int x1, int y1, int x2, int y2)
  {
    int d1 = Math.abs(x1-x2);
    int d2 = Math.abs(y1-y2);
    return Math.sqrt(d1*d1+d2*d2);
  }
  
  /*public void setCoord(int coord)
  {
    this.coord = coord;
  }
  public int getCoord()
  {
    return coord;
  }
  public int getValue()
  {
    return value;
  }
  public void setValue(int num)
  {
    value = num;
  }*/
}