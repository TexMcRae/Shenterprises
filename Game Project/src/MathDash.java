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
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");
    JMenuItem pause = new JMenuItem("Pause");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem instructions = new JMenuItem("Instructions");
    JMenuItem credits = new JMenuItem("Credits");
  }
  public void actionPerformed (ActionEvent a) {
    
  }
  public static void main (String[] args) {
    new MathDash();
  }
}