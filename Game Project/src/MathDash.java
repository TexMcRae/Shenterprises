import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.0_13.05.2016
 * The MathDash class sets up the base layout for the main menu, including the game menu, high scores and exit GUI.
 * It accesses multiple manually-coded classes.
 * <p><b>Instance Variables:</b>
 * <p><b> g2d </b> (private) The variable used to draw all graphics to the screen.
 * <p><b> frame </b> (static) The frame used for all game action, as this class extends JPanel.
 * <p><b> type </b> (private) The case of MathDash to be called.
 * <p><b> score </b> (static) The high scores of multple players, which are stored in a data class.
 */
public class MathDash extends JPanel implements ActionListener, KeyListener {
  private Graphics2D g2d;
  private boolean alt = false;
  static JFrame frame;
  private int type;
  static Scores score;
  static MathDash dash;
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
    frame.addKeyListener(this);
    frame.add(bar);
    frame.add(this);
    frame.setSize(800,600);
    frame.setResizable(false);
    frame.setLocationRelativeTo(this);//change?
    frame.setVisible(true);
  }
  /**
   * The paintComponent() method draws graphics to the screen.
   * It also makes JButtons and assigns them their ActionListeners.
   * @param g The base graphics variable provided, which is cast to Graphics2D
   */
  @Override
  public void paintComponent(Graphics g) {
    g2d = (Graphics2D) g;
    g2d.setPaint(new Color (40,200,60));//change to image eventually
    g2d.fillRect(0,0,frame.getWidth(),frame.getHeight());
    if (type == -1) {//Spalsh screen
      //do a thing here
      frame.setVisible(false);
      dash = new MathDash(0);
    }
    else if (type==0) {//base menu
      JButton[] btn = {new JButton("Play"),new JButton("Instructions"),new JButton("High Scores"),new JButton("Quit")};
      for(int x=0;x<4;x++)
        add(btn[x]);
      ((FlowLayout)getLayout()).setHgap(800);
      ((FlowLayout)getLayout()).setVgap(85);//leaves room for title
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);//tricks into "reopening" it
          dash = new MathDash(4);
        } 
      });
      btn[1].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        frame.setVisible(false);//tricks into "reopening" it
        dash = new MathDash(1);
      } } );
      btn[2].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        frame.setVisible(false);
        dash = new MathDash(2);
      } } );
      btn[3].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        frame.setVisible(false);
        dash = new MathDash(3);
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
          dash = new MathDash(0);
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
          //print shit
        } } );
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          dash = new MathDash(0);
        } } );
    }
    else if(type == 4){//play menu
      g2d.setPaint(Color.black);
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
          dash = new MathDash(5);
        } 
      });
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          dash = new MathDash(6);
        }
      });
      btn[2].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          dash = new MathDash(7);
        } 
      });
      btn[3].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          dash = new MathDash(0);
        } 
      });
    }
    else if (type >= 5 && type <= 7){
      g2d.setPaint(Color.black);
      new Game(type-5);
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
          dash = new MathDash(0);
      } } );
    }
  }
  /**
   * The actionPerformed() method is called when an ActionListener is fired.
   * @param a The event 'description' for the current fired event.
   */
  public void actionPerformed (ActionEvent a) {
    
  }
  
  //comments
  public void keyPressed(KeyEvent k){
    if(k.getKeyChar()==' '&&Player.x==0){
      Jump j = new Jump(0);
      j.start();
    }
  }
  public void keyReleased(KeyEvent k){}
  public void keyTyped(KeyEvent k){}
  
  
  /**
   * The main method creates a MathDash object.
   * @param args Command-line args (Not used in MathDash).
   */
  public static void main (String[] args) {
    dash = new MathDash(-1);
  }
}
