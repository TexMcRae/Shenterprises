import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game extends JPanel implements ActionListener{
  
  private Graphics2D g2d;
  static int difficulty;
  private int num1;
  private int num2;
  private JFrame frame;
  private boolean isAddition;
  private Player p;
  private int score;
  
  public Game(int diff) { 

    super ();
    difficulty = diff;
    p = new Player(diff);
    frame = new JFrame("MathDash");
    frame.add(this);
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(this);
    frame.setVisible(true);
    playGame();
  }
  
  private void paintComponent(Graphics g)
  {
    g2d = (Graphics2D) g;
    g2d.setPaint(new Color (40,200,60));//change to image eventually
    g2d.fillRect(0,0,frame.getWidth(),frame.getHeight());
  }
  private void drawEquation(){
    generateEquation();
    String operator = " " 
    if(isAddition == true)
    {
      operator = "+";
    }
    else
    {
      operator = "-";
    }
    g2d.drawString(num1 + " =- " + operator + " = " + "?",200,300);
    
  }
  
  private void drawLives(){
    
  }
  
  private void drawScore(){
    
  }
   
  
  private void playGame()
  {
    
  }
  
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
  
  static void delay(int delay)
  {
    
  }
}
