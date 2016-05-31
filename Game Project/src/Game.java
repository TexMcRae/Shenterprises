import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * The Game class displays game graphics, and sets up necessary mechanics.
 * <p><b> Instance variables </b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> difficulty </b> (static) The level difficulty.
 * <p><b> num1 </b> (private) The first number in the equation.
 * <p><b> num2 </b> (private) The second number in the equation.
 * <p><b> isAddition </b> (private) Whether the equation contains a + or -.
 * <p><b> p </b> (private) The player used in animations.
 * <p><b> score </b> (private) The player's score.
 */
public class Game extends JPanel implements KeyListener, ActionListener{
  
  private Graphics2D g2d;
  static int difficulty;
  private int num1;
  private int num2;
  static  int answer;
  private int lives = 1;
  private boolean isAddition;
  private Player p;
  private NumberBall n,n1,n2;
  private int num3,num4;
  private int score;
  private int xLoc1,xLoc2,xLoc3;
  private int yLoc1,yLoc2,yLoc3;
  boolean called;
  Jump j;
  static boolean paused;
  static Timer timer;
  /**
   * The class constructor sets up the panel and frame.
   * @param diff The starting game difficulty.
   */
  public Game(int diff) { 
    if (diff >= -1)
    {
    System.out.println("WHAT");
    difficulty = diff;
    generateEquation();
    randomXLoc();
    randomYLoc();
    System.out.print(num1);
    System.out.println("Answer:" + answer);
    num3 = generateNumber();
    num4 = generateNumber();
    n = new NumberBall(answer,xLoc1,yLoc1);
    n1 = new NumberBall(num3,xLoc2,yLoc2);
    n2 = new NumberBall(num4,xLoc3,yLoc3);
    p = new Player(difficulty);
    MathDash.frame.add(this);
    MathDash.frame.addKeyListener(this);
    timer = new Timer(0, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
    Jump.isRunning = true;
    Jump j = new Jump(17);
    j.start();
    repaint();
    diff = 100;
    }
  }
  public Game()
  {
  
  }
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
    String operator = isAddition?"+":"-";
    Image img1 = Toolkit.getDefaultToolkit().getImage("./resources/Backround.jpg");
    g2d.drawImage(img1, 10, 10, this);
    g2d.finalize();
    //System.out.print(num1);
    g2d.drawString(num1 + operator + num2 + " = " + "?",200,50);
    g2d.drawString("" +score,600,50);
    g2d.drawString(""+lives,100,50);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
    n.draw(g,answer,xLoc1,yLoc1);
    n1.draw(g,num3,xLoc2,yLoc2);
    n2.draw(g,num4,xLoc3,yLoc3);
    p.draw(g);
    g2d.drawLine(Player.x+(Player.width / 2),((530-Player.height) + (Player.height/2)) -Player.y,xLoc2-NumberBall.x+15,yLoc2+15);
  }
  private void randomXLoc()
  {
    double temp = Math.random();
    System.out.println(temp);
    if (temp < 0.3)
    {
      xLoc1 = 600 + (int)(Math.random() * 51);
      xLoc2 = 500 + (int)(Math.random() * 51);
      xLoc3 = 400 + (int)(Math.random() * 51);
    }
    else if (temp > 0.3 && temp < 0.6)
    {
      xLoc1 = 400 + (int)(Math.random() * 51);
      xLoc2 = 500 + (int)(Math.random() * 51);
      xLoc3 = 600 + (int)(Math.random() * 51);
    }
    else
    {
      xLoc1 = 500 + (int)(Math.random() * 51);
      xLoc2 = 400 + (int)(Math.random() * 51);
      xLoc3 = 600 + (int)(Math.random() * 51);
    }
  }
  private void randomYLoc()
  {
    double temp = Math.random();
    System.out.println(temp);
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
  public void endGame()
  {

      delay(500);
      lives = 3;
      score = 0;
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
  private int generateCoord()
  {
    return (int)((Math.random() * 500) + 300);
  }
  public int getNumOne(){
    return num1;
  }
  
  public int getNumTwo(){
    return num2;
  }
  
  public int getAnswer(){
    if(isAddition){
      return num1+num2;
    }else{
      return num1-num2;
    }
  }
  
  /**
   * The drawEquation() method draws the incomplete equation to the screen.
   * TO BE FIXED: Relocate method to a timer-based class, or simplify.
   */
  private void drawEquation(Graphics g){
    generateEquation();
    String operator = isAddition?"+":"-";
    g2d.drawString(num1 + operator + num2 + " = " + "?",200,300);
  }
  private int generateY()
  {
    return (int)((Math.random() * 300) + 100);
  }
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
   * The drawLives() method draws the current number of lives to the screen.
   * TO BE FIXED: Relocate method to a timer-based class, or simplify.
   */
  private void drawLives(){
    
  }
  /**
   * The drawScore() method draws the player's current score to the screen.
   * TO BE FIXED: Relocate method to a timer-based class, or simplify.
   */
  private void drawScore(){
    
  }
  /**
   * The playGame() method draws the player's current score to the screen.
   * TO BE FIXED: Simplify, add in Timer-based classes.
   */
  private void playGame(){
    
  }
  
  public int generateBallNum(int max)
  {
      return (int) (Math.random() * max);
  }
  /**
   * The generateEquation() method creates a new incomplete equation.
   * It will have a blank, and the player must fill that blank..
   */
  private void generateEquation(){
    //if addition or subtraction
    if (called == false){
      called = true;
      System.out.println("HEY");
      if(Math.random() > 0.5){
        isAddition = true;
        num1 = difficulty*2 + (int)(Math.random() * ((5+difficulty*5) + 1));
        System.out.println(num1);
        num2 = difficulty*2 + (int)(Math.random() * ((5+difficulty*5) + 1));
        System.out.println(num2);
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
       System.out.println("STARTED JUMP");
       Player.y = 1;
      Jump j = new Jump(0);
      j.start();
    }
    if (k.getKeyChar() == 'p')
    {
      if (paused == false)
      {
        timer.stop();
        paused = true;
      }
      else
      {
        System.out.print("a");
        timer.start();
        paused = false;
        System.out.println("b");
      }
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
   * The playGame() method draws the player's current score to the screen.
   * @param delay The delay time, in milliseconds.
   */
  static void delay(int delay)
  {
    try{
      Thread.sleep(delay);
    }
    catch(InterruptedException e){}
  }
    public void actionPerformed(ActionEvent a) {
    repaint();
//    if ((Player.y >= (yLoc1 - 30)))
//    {
//      System.out.print("h");
//    }
    if(NumberBall.distance(Player.x+(Player.width / 2),((530-Player.height)) -Player.y,xLoc1-NumberBall.x+15,yLoc1+15)<30)
    {
      called = false;
      System.out.println("You won!");
      System.out.println(Player.y);
      System.out.println(yLoc1 - 30);
      score+= 10+difficulty*2;
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
  else if ((NumberBall.distance(Player.x+(Player.width / 2),((530-Player.height)) - Player.y,xLoc2-NumberBall.x+15,yLoc2+15)<30) || ((NumberBall.distance(Player.x+(Player.width / 2),((530-Player.height)) -Player.y,xLoc3-NumberBall.x+15,yLoc3+15)<30)))
  {
      called = false;
      System.out.println("You Lost!");
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
  else if (NumberBall.x > 600)
  {
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
