import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//try{Thread.sleep(1000);}catch(Exception e){}
public class MathDash extends JPanel implements ActionListener {
  private Graphics2D g2d;
  private JFrame frame;
  private int type;
  static MouseEvent m;
  //static Scores score;
  
  public MathDash(int type) { 
    super ();
    frame = new JFrame("MathDash");
    this.type = type;
    JMenuBar bar = new JMenuBar();
    frame.add(bar);
    frame.add(this);
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(this);//change?
    frame.setVisible(true);
  }
  @Override
  public void paintComponent(Graphics g) {
    g2d = (Graphics2D) g;
    g2d.setPaint(new Color (40,200,60));//change to image eventually
    g2d.fillRect(0,0,frame.getWidth(),frame.getHeight());
    if (type==0) {//base menu
      JButton[] btn = {new JButton("Play"),new JButton("Instructions"),new JButton("High Scores"),new JButton("Quit")};
      for(int x=0;x<4;x++)
        add(btn[x]);
      ((FlowLayout)getLayout()).setHgap(100);
      ((FlowLayout)getLayout()).setVgap(175);//leaves room for title
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
          //print shit
        } } );
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(0);
        } } );
    }
    else if(type == 4){
      //play menu
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
          new Game(0);
        } 
        
      });
      btn[1].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          new Game(1);
        } 
        
      });
      btn[2].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          new Game(2);
        } 
        
      });
        
      btn[3].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent a) {
          frame.setVisible(false);
          new MathDash(0);
        } 
        
      });
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
  public void actionPerformed (ActionEvent a) {
    
  }
  
  public static void main (String[] args) {
    new MathDash(0);
  }
}
