import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.3_03.06.2016
 * The Game class displays game graphics, and sets up necessary mechanics.
 * <b> ~ TOTAL TIME SPENT ~ </b>
 * Ryan: 48h30m (9.75, 12.25, 12, 14.5)
 * Kevin: 32h (8, 8, 7, 9)
 * Max: 32h30m (8.5, 7.5, 8.5, 8)
 * <p><b> Instance variables </b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> difficulty </b> (static) The level difficulty.
 * <p><b> num1 </b> (private) The first number in the equation.
 * <p><b> num2 </b> (private) The second number in the equation.
 * <p><b> isAddition </b> (private) Whether the equation contains a + or -.
 * <p><b> p </b> (private) The player used in animations.
 * <p><b> score </b> (private) The player's score.
 * <p><b> answer </b> (private) The answer to the equation.
 * <p><b> lives </b> (private) The number of lives.
 * <p><b> n,n1,n2 </b> (private) The NumberBalls across the screen.
 * <p><b> num3,num4 </b> (private) The values of the incorrect answers.
 * <p><b> xLoc1,xLoc2,xLoc3 </b> (private) The balls' x-coordinates.
 * <p><b> yLoc1,yLoc2,yLoc3 </b> (private) The balls' y-coordinates.
 * <p><b> called </b> (private) Prevents accidental double-printing.
 * <p><b> j </b> (private) The instance of Jump used to control movements.
 * <p><b> paused </b> (static) The current pause-state of the game.
 * <p><b> timer </b> (static) The timer used to fire ActionListeners.
 */
public class Game extends JPanel implements KeyListener, ActionListener{
  
  private Graphics2D g2d;
  static int difficulty;
  private int num1;
  private int num2;
  private boolean isAddition;
  private Player p;
  static int score;
  static int answer;
  private int lives = 3;
  private NumberBall n,n1,n2;
  private int num3,num4;
  private int xLoc1,xLoc2,xLoc3;
  private int yLoc1,yLoc2,yLoc3;
  private boolean called;
  private Jump j;
  static boolean paused;
  static Timer timer;
  /**
   * The class constructor sets up the panel and frame.
   * @param diff The starting game difficulty.
   */
  public Game(int diff) { 
    if (diff >= -1)
    {
    Time.time = 0; 
    difficulty = diff;
    generateEquation();
    randomXLoc();
    randomYLoc();
    num3 = generateNumber();
    num4 = generateNumber();
    n = new NumberBall(answer,xLoc1,yLoc1);
    n1 = new NumberBall(num3,xLoc2,yLoc2);
    n2 = new NumberBall(num4,xLoc3,yLoc3);
    p = new Player();
    MathDash.frame.add(this);
    MathDash.frame.addKeyListener(this);
    timer = new Timer(0, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
    Jump.isRunning = true;
    Jump j = new Jump(17);
    j.start();
    NumberBall.x = 0;
    repaint();
    diff = 100;
    score = 0;
    new Time();
    }
  }
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
    String operator = isAddition?"+":"-";
    Image img1 = Toolkit.getDefaultToolkit().getImage("./resources/Backround.jpg");
    g2d.drawImage(img1, 0, 0, this);
    g2d.finalize();
    g2d.drawString(num1 + operator + num2 + " = " + "?",400,50);
    g2d.drawString("Score: " +score,650,50);
    g2d.drawString("Lives: "+lives,100,50);
    g2d.drawString("Time: " + (time/864),250,50); 
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
    n.draw(g,answer,xLoc1,yLoc1);
    n1.draw(g,num3,xLoc2,yLoc2);
    n2.draw(g,num4,xLoc3,yLoc3);
    p.draw(g);
    /*
    g2d.drawRect(Player.x+18-15,530-Player.height-Player.y+1-15,Player.x+57-(Player.x+18)+30,530-Player.height-Player.y+94-(530-Player.height-Player.y+1)+30);
    g2d.drawRect(Player.x+18,530-Player.height-Player.y+1,Player.x+57-(Player.x+18),530-Player.height-Player.y+94-(530-Player.height-Player.y+1));
    g2d.drawLine(Player.x+18,530-Player.height-Player.y+1,xLoc1-NumberBall.x+15,yLoc1+15);
    g2d.drawLine(Player.x+18,530-Player.height-Player.y+94,xLoc1-NumberBall.x+15,yLoc1+15);
    g2d.drawLine(Player.x+57,530-Player.height-Player.y+1,xLoc1-NumberBall.x+15,yLoc1+15);
    g2d.drawLine(Player.x+57,530-Player.height-Player.y+94,xLoc1-NumberBall.x+15,yLoc1+15);
    */
  }
  /**
   * Generates random x locations for the NumberBalls.
   */
  private void randomXLoc()
  {
    double temp = Math.random();
    if (temp < 0.3)
    {
      xLoc1 = 1200 + (int)(Math.random() * 51);
      xLoc2 = 1000 + (int)(Math.random() * 51);
      xLoc3 = 800 + (int)(Math.random() * 51);
    }
    else if (temp > 0.3 && temp < 0.6)
    {
      xLoc1 = 800 + (int)(Math.random() * 51);
      xLoc2 = 1000 + (int)(Math.random() * 51);
      xLoc3 = 1200 + (int)(Math.random() * 51);
    }
    else
    {
      xLoc1 = 1000 + (int)(Math.random() * 51);
      xLoc2 = 800 + (int)(Math.random() * 51);
      xLoc3 = 1200 + (int)(Math.random() * 51);
    }
  }
  /**
   * Generates random y locations for the NumberBalls.
   */
  private void randomYLoc()
  {
    double temp = Math.random();
    if (temp < 0.3)
    {
      yLoc1 = 300 + (int)(Math.random() * 51);
      yLoc2 = 200 + (int)(Math.random() * 51);
      yLoc3 = 100 + (int)(Math.random() * 51);
    }
    else if (temp > 0.3 && temp < 0.6)
    {
      yLoc1 = 100 + (int)(Math.random() * 51);
      yLoc2 = 200 + (int)(Math.random() * 51);
      yLoc3 = 300 + (int)(Math.random() * 51);
    }
    else
    {
      yLoc1 = 200 + (int)(Math.random() * 51);
      yLoc2 = 100 + (int)(Math.random() * 51);
      yLoc3 = 300 + (int)(Math.random() * 51);
    }
  }
  /**
   * Implements the choices that could be made after the game.
   */
  public void endGame()
  {

      delay(500);
      if (((Time.time/10) - score/(10 + (2 * difficulty)) < 10) && score < 5)
      {
        score += 5 - ((Time.time/10) - score/(10 + (2 * difficulty)));
      }
      if (score >= 5 * (10 + (2 * difficulty)))
      {
        MathDash.prog = difficulty;
      }
      lives = 3;
      //score = 0;
      NumberBall.x = 0;
      timer.stop();
      Jump.isRunning = false;
      MathDash.frame.setVisible(false);
      MathDash.frame.getContentPane().invalidate();
      MathDash.frame.getContentPane().validate();
      MathDash.frame.getContentPane().repaint();
      new MathDash(5);
      repaint();
  }
  /**
   * Generates a random starting coordinate
   * @return A random starting coordinate.
   */
  private int generateCoord()
  {
    return (int)((Math.random() * 500) + 300);
  }
  /**
   * Returns the answer to the pending equation.
   * @return The answer to the pending equation.
   */
  public int getAnswer(){
    if(isAddition){
      return num1+num2;
    }else{
      return num1-num2;
    }
  }
  /**
   * The drawEquation() method draws the incomplete equation to the screen.
   */
  private void drawEquation(Graphics g){
    generateEquation();
    String operator = isAddition?"+":"-";
    g2d.drawString(num1 + operator + num2 + " = " + "?",200,300);
  }
  /**
   * Generates a random starting coordinate
   * @return A random starting coordinate.
   */
  private int generateY()
  {
    return (int)((Math.random() * 300) + 100);
  }
  /**
   * Generates a random number for the balls.
   * @return A random starting number.
   */
  public int generateNumber()
  {
    while (true)
    {
      int temp = (int)(Math.random() * 10);
      if (temp != num3 && temp != num4 && temp != answer)
        return temp;
    }
  }
  /**
   * Generates a random number to be on the ball.
   * @param max The limit to the generation.
   * @return A random number to be on the ball.
   */
  public int generateBallNum(int max)
  {
      return (int) (Math.random() * max);
  }
  /**
   * The generateEquation() method creates a new incomplete equation.
   * It will have a blank, and the player must fill that blank.
   */
  private void generateEquation(){
    //if addition or subtraction
    if (called == false){
      called = true;
      if(Math.random() > 0.5){
        isAddition = true;
        num1 = difficulty*2 + (int)(Math.random() * ((5+difficulty*5) + 1));
        num2 = difficulty*2 + (int)(Math.random() * ((5+difficulty*5) + 1));
        answer = num1 + num2;
      }
      else{
        isAddition = false;
        num1 = difficulty*2 + 5 + (int)(Math.random() * ((5+difficulty*5) + 1));
        num2 = (int)(Math.random() * ((num1)));
        answer = num1 - num2;
      }
    }
  }
  /**
   * The keyPressed method checks if a key is pressed.
   * @param k The instance of KeyEvent used to determine the key pressed.
   */
  public void keyPressed(KeyEvent k){
    if(k.getKeyChar()==' ' &&Player.y==0){
      Jump.stop = false;
       Player.y = 1;
      Jump j = new Jump(0);
      j.start();
    }
    else
    {
      if (Player.y == 1)
        Player.y = 0;
    }
    if (k.getKeyChar() == 'p')
    {
        timer.stop();
        int x = NumberBall.x;
        double y = Jump.x;
        int temp = JOptionPane.showConfirmDialog(null,"Would you like to go back to the main menu?","Paused",JOptionPane.YES_NO_OPTION);
        if (temp ==JOptionPane.YES_OPTION)
        {
          NumberBall.x = 0;
          timer.stop();
          Jump.isRunning = false;
          MathDash.frame.setVisible(false);
          MathDash.frame.getContentPane().invalidate();
          MathDash.frame.getContentPane().validate();
          MathDash.frame.getContentPane().repaint();
          new MathDash(4);
          repaint();
        }
        else
        {
          timer.start();
          NumberBall.x = x;
          Jump.x = y;
          repaint();
        }
    }
    if(k.getKeyChar() == 'h')
    {
      timer.stop();
      int x = NumberBall.x;
      double y = Jump.x;
      try 
      { 
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler Help.chm");
      } 
      catch (Exception ex) 
      { 
      }
      JOptionPane.showMessageDialog(null,"Press OK when you want to resume the game.");
      timer.start();
      NumberBall.x = x;
      Jump.x = y;
      repaint();
    }
  }
  /**
   * The keyReleased method checks if a key is released.
   * @param k The instance of KeyEvent used to determine the key released.
   */
  public void keyReleased(KeyEvent k){
    if(Jump.x>0&&Jump.x<Math.PI/2)
      Jump.x+=(Math.PI/2-Jump.x)*2;
  }
  /**
   * The keyTyped method checks if a key is typed. NOT USED.
   * @param k The instance of KeyEvent used to determine the key typed.
   */
  public void keyTyped(KeyEvent k){}
  /**
   * Pauses for a certain number of milliseconds.
   * @param delay The delay time, in milliseconds.
   */
  static void delay(int delay)
  {
    try{
      Thread.sleep(delay);
    }
    catch(InterruptedException e){}
  }
  /**
   * Receives any fired ActionEvents.
   * @param a The ActionEvent fired.
   */
  public void actionPerformed(ActionEvent a) {
    repaint();
    if(NumberBall.distance(100+(Player.width / 2),((530-Player.height)) -Player.y,xLoc1-NumberBall.x+15,yLoc1+15))
    {
      num3 = 0;
      num4 = 0;
      called = false;
      score+= 10+difficulty*2;
      NumberBall.x = 0;
      generateEquation();
      randomXLoc();
      randomYLoc();
      repaint();
      timer.restart();
      num4 = generateNumber();
      repaint();
  }
  if ((NumberBall.distance(100+(Player.width / 2),((530-Player.height)) - Player.y,xLoc2-NumberBall.x+15,yLoc2+15)) || ((NumberBall.distance(100+(Player.width / 2),((530-Player.height)) -Player.y,xLoc3-NumberBall.x+15,yLoc3+15))))
  {
       num3 = 0;
      num4 = 0;
      called = false;
      lives--;
      if (lives <= 0)
      {
        endGame();
        return;
      }
      NumberBall.x = 0;
      generateEquation();
      randomXLoc();
      randomYLoc();
      repaint();
      timer.restart();
      num3 = generateNumber();
      num4 = generateNumber();
      repaint();
      called = false;
  }
  if (NumberBall.x > 1200)
  {
       num3 = 0;
      num4 = 0;
      called = false;
      NumberBall.x = 0;
      generateEquation();
      randomXLoc();
      randomYLoc();
      repaint();
      timer.restart();
      num3 = generateNumber();
      num4 = generateNumber();
      repaint();
  }
}
}
