import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
  static Scores scores;
  static int i;
  private Game game;
  boolean stuff; 
  /**
   * The class constructor sets up the panel and frame.
   * It also controls which part of paintComponent() is accessed.
   * @param type The case of MathDash to be called.
   */
  public MathDash(int type) { 
    super ();
    frame = new JFrame("MathDash");
    this.type = type;
    scores = new Scores();
    scores.loadFromFile();
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
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
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
    if (type == -1) {//Splash screen
      //do a thing here
      Image img2 = Toolkit.getDefaultToolkit().getImage("Logo.png");
      g2d.drawImage(img2, 0+i/4, 300, this);
      repaint();
      //g2d.finalize();
      Jump j = new Jump(1);
      j.start();
      if (i > 3199)
      {
      frame.setVisible(false);
      new MathDash(0);
      }
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
      g2d.drawString("Top Ten",50,30);
      g2d.drawString("Name: Score",50,45);
      int yValue = 50;
      try{
        BufferedReader reader = new BufferedReader(new FileReader("./highscores.shen"));
        if(reader.readLine().equals(Scores.header)){
          String line = reader.readLine();

          while (line != null) {
              g2d.drawString(line.split(",")[0] + ":  " + line.split(",")[1], 50, yValue);
              yValue += 20;
              line = reader.readLine();
          }
        }
      }catch(Exception e){
        System.out.println("File error");
      }
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JButton[] btn = {new JButton("Print my failure"),new JButton("Nvm, back")};
      for(int x=0;x<2;x++)
        add(btn[x]);
      btn[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          scores.printHighscores();
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
      JButton[] btn = {new JButton("MainMenu"),new JButton("Try Again")};
      JTextField text = new JTextField(10);
      JButton button = new JButton("Submit");
      g2d.drawString("MathDash",50,30);
      g2d.drawString("Sorry you lost",50,100);
      ((FlowLayout)getLayout()).setVgap(300);
      ((FlowLayout)getLayout()).setHgap(50);
      ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEADING);
      JLabel label = new JLabel("Enter your username: ");
      if (stuff == false)
      {
         add(label);
         add(text);
         add(button);
         System.out.println("LABEL");
         stuff = true;
      }
      add(btn[0]);
      btn[0].setEnabled(false);
      btn[0].setVisible(false);
      add(btn[1]);
      btn[1].setVisible(false);
      btn[1].setEnabled(false);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          String temp = text.getText();
          System.out.println(game);
          int score = Game.score;
          System.out.println(score);
          scores.add(new Score(temp, score));
          scores.writeToFile();
          
          remove(text);
          remove(button);
          remove(label);
          MathDash.frame.repaint();
          btn[0].setEnabled(true);
          btn[0].setVisible(true);
          btn[1].setEnabled(true);
          btn[1].setVisible(true);
          MathDash.frame.repaint();
        }
        });
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
      repaint();
      
    }
    else if (type >= 6 && type <= 8){
      System.out.println("HER");
      g2d.setPaint(Color.black);
      game = new Game(type-6);
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