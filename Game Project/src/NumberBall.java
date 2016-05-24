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
public class NumberBall extends JPanel{
  private Graphics2D g2d;
  private int [] pos = new int [2];
  static int x;
  private int value;
  private int coord;
  private Timer timer;
   /**
   * The constructor sets up the balls' properties.
   */
  public NumberBall(int num, int coord){
    value = num;
    this.coord = coord;
    System.out.println(value);
  }
  //@Override
  public void draw(Graphics g,int value,int coord) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(Color.white);
    g2d.fillOval(coord-x,100,30,30);
    g2d.setPaint(Color.black);
    g2d.drawString("" + value,(coord + 10)-x,115);
  }
  /**
   * The distance() method checks the distance between the player and the balls.
   * @return The distance between the player and the balls.
   */
  private double distance()
  {
    return 0.0;
  }
  public void setCoord(int coord)
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
  }
  /**
   * Draws the balls.
   */
  public void drawBall(){
    
  }
   //public void actionPerformed(ActionEvent a) {
    //repaint();
  //}
}