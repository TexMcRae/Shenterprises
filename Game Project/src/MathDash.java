import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MathDash extends JPanel implements ActionListener {
  private Graphics2D g2d;
  private JFrame frame;
  static MouseEvent m;
  static Scores score;
  public MathDash() { 
    super ();
    frame = new JFrame();
    JMenuBar bar = new JMenuBar();
    /*
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem instructions = new JMenuItem("Instructions");
    JMenuItem credits = new JMenuItem("Credits");*/
    frame.add(bar);
    frame.add(this);
  }
  public void actionPerformed (ActionEvent a) {
    
  }
  public static void main (String[] args) {
    new MathDash();
  }
}