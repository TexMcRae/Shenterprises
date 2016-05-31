import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * The MathDash class sets up the base layout for the main menu, including the game menu, high scores and exit GUI.
 * It accesses multiple manually-coded classes.
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> frame </b> (static) The frame used for all game action, as this class extends JPanel.
 * <p><b> type </b> (private) The case of MathDash to be called.
 * <p><b> score </b> (static) The high scores of multple players, which are stored in a data class.
 */
public class MathDash extends JPanel implements ActionListener {
  private Graphics2D g2d;
  static JFrame frame;
  private int type;
  static Scores score;
  static int i;
  /**
   * The class constructor sets up the panel and frame.
   * It also controls which part of paintComponent() is accessed.
   * @param type The case of MathDash to be called.
   */
  public MathDash(int type) { 
    super ();
    frame = new JFrame("MathDash");
    this.type = type;
    JMenuBar bar = new JMenuBar();
    frame.add(bar);
    frame.add(this);
    frame.setSize(800,600);
    frame.setResizable(false);
    frame.setLocationRelativeTo(this);//change?
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
  }
  /**
   * The paintComponent() method draws graphics to the screen.
   * It also makes JButtons and assigns them their ActionListeners.
   * @param g The base graphics variable provided, which is cast to Graphics2D
   */
  @Override
  public void paintComponent(Graphics g) {
    g2d = (Graphics2D) g;
    Image img = Toolkit.getDefaultToolkit().getImage("./resources/Opening screen.jpg");
    g2d.drawImage(img, 10, 10, this);
    g2d.finalize();
    if (type == -1) {//Spalsh screen
      //do a thing here
      //Image img2 = Toolkit.getDefaultToolkit().getImage("Logo.png");
      //g2d.drawImage(img, 800-i, 300, this);
      //g2d.finalize();
      //if (i > 799)
      //{
      frame.setVisible(false);
      new MathDash(0);
      //}
    }
    else if (type==0) {//base menu
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
      g2d.drawString("MathDash",345,30);
      JButton[] btn = {new JButton("Play"),new JButton("Instructions"),new JButton("High Scores"),new JButton("Quit")};
      for(int x=0;x<4;x++)
        add(btn[x]);
      ((FlowLayout)getLayout()).setHgap(800);
      ((FlowLayout)getLayout()).setVgap(85);//leaves room for title
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);//tricks into "reopening" it
          new MathDash(4);
        } 
      });
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);//tricks into "reopening" it
          new MathDash(1);
        } } );
      btn[2].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(2);
        } } );
      btn[3].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(3);
        } } );
    }
    else if (type==1) {//instructions here
      g2d.setPaint(Color.black);
      g2d.drawString("The instructions go here!",50,30);
      g2d.drawString("And here.",50,45);
      g2d.drawString("And every 15 pixels until the button.",50,60);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton btn = new JButton("Got it");
      add(btn);
      btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(0);
        } } );
    }
    else if (type==2) {//scores here
      g2d.setPaint(Color.black);
      g2d.drawString("This is a blank section!",50,30);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton[] btn = {new JButton("Print my failure"),new JButton("Nvm, back")};
      for(int x=0;x<2;x++)
        add(btn[x]);
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          //printing
        } } );
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(0);
        } } );
    }
    else if(type == 4){//play menu
      g2d.setPaint(Color.black);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
      g2d.drawString("MathDash",50,30);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton[] btn = {new JButton("Easy"),new JButton("Medium"),new JButton("Hard"),new JButton("Back")};
      for(int x=0;x<4;x++){
        add(btn[x]);
      }
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          System.out.println("M");
          new MathDash(6);
        } 
      });
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          System.out.println("A");
          new MathDash(7);
        }
      });
      btn[2].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          System.out.println("X");
          new MathDash(8);
        } 
      });
      btn[3].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          System.out.println("T");
          new MathDash(0);
        } 
      });
    }
    else if (type == 5)
    {
      g2d.setPaint(Color.black);
      g2d.drawString("MathDash",50,30);
      g2d.drawString("Sorry you lost",50,100);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton[] btn = {new JButton("Main Menu"),new JButton("Try Again")};
      for(int x=0;x<2;x++){
        add(btn[x]);
      }
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(4);
        } 
      });
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(6);
          frame.repaint();
        }
      });
      
    }
    else if (type >= 6 && type <= 8){
      System.out.println("HER");
      g2d.setPaint(Color.black);
      new Game(type-6);
      //return; 
      type = 10;
    }
    else {//exit here
      g2d.setPaint(Color.black);
      g2d.drawString("This is a bad outro.",50,30);
      g2d.drawString("Just go already.",50,45);
      g2d.drawString("Or not ;)",50,60);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton[] btn = {new JButton("Ok, quit"),new JButton("Nvm, back")};
      for(int x=0;x<2;x++)
        add(btn[x]);
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          System.exit(0);
        } } );
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(0);
        } } );
    }
  }
  /**
   * The actionPerformed() method is called when an ActionListener is fired.
   * @param a The event 'description' for the current fired event.
   */
  public void actionPerformed (ActionEvent a) {
    
  }
  /**
   * The main method creates a MathDash object.
   * @param args Command-line args (Not used in MathDash).
   */
  public static void main (String[] args) {
    new MathDash(-1);
  }
}
