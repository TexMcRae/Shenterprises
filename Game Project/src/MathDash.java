import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MathDash extends JFrame implements ActionListener {
  private Graphics2D g2d;
  static MouseEvent m;
  static Scores score;
  public MathDash() { 
    super ("MathDash");
    JMenuBar bar = new JMenuBar();
    JMenu file = new JMenu();
  }
  public void actionPerformed (ActionEvent a) {
    
  }
  public static void main (String[] args) {
    new MathDash();
  }
}