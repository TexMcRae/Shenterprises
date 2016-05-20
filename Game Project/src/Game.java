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
public class Game implements KeyListener{
  
  private Graphics2D g2d;
  static int difficulty;
  private int num1;
  private int num2;
  private int answer;
  private boolean isAddition;
  private Player p;
  private int score;
  /**
   * The class constructor sets up the panel and frame.
   * @param diff The starting game difficulty.
   */
  public Game(int diff) { 
    difficulty = diff;
    MathDash.frame.addKeyListener(this);
    p = new Player(diff);
    n = new NumberBall();
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
  private void drawEquation(){
    generateEquation();
    String operator = isAddition?"+":"-";
    g2d.drawString(num1 + " =- " + operator + " = " + "?",200,300);
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
  
  public void generateBallNum(int max)
  {
      return (int) Math.random() * max;
  }
  /**
   * The generateEquation() method creates a new incomplete equation.
   * It will have a blank, and the player must fill that blank..
   */
  private void generateEquation(){
    
    //if addition or subtraction
    if(Math.random() < 0.5){
      isAddition = true;
      num1 = (int)Math.random() * 10 + 1;
      num2 = (int)Math.random() * 10 + 1;
      
    }else{
      isAddition = false;
      num1 = 10 + (int)(Math.random() * ((20 - 10) + 1));
      num2 = 0 + (int)(Math.random() * ((10) + 1));
    }
    
  }
  /**
   * The keyPressed method checks if a key is pressed.
   * @param k The instance of KeyEvent used to determine the key pressed.
   */
  public void keyPressed(KeyEvent k){
    if(k.getKeyChar()==' '&&Player.y==0){
      Jump j = new Jump(0);
      j.start();
    }
  }
  /**
   * The keyReleased method checks if a key is released. NOT USED.
   * @param k The instance of KeyEvent used to determine the key released.
   */
  public void keyReleased(KeyEvent k){}
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
}
