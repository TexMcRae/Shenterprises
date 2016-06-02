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
  static int coordX;
  static int coordY;
  private Timer timer;
   /**
   * The constructor sets up the balls' properties.
   */
  public NumberBall(int num, int coordX,int coordY){
    value = num;
    this.coordX = coordX;
    this.coordY = coordY;
    System.out.println(value);
  }
  //@Override
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
    /*
    if(x2<x1+57&&y2>y1+)
      return 0;
    return 50;*/
  /*int d1 = Math.abs(x1-x2);
    int d2 = Math.abs(y1-y2);
    return Math.sqrt(d1*d1+d2*d2);*/
  }
  /*public void setCoord(int coord)
  {
    this.coord = coord;
  }
  public int getCoord()
  {
    return coord;
  }*/
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