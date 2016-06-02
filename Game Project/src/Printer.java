import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

/**
 * 
 */
public class Printer implements Printable {
  
  private Scores scores;
  
  
  public Printer (Scores s) 
  {
    scores = s;
  }
  
  /**
   * The print method is orignally defined in the Printable interface and is defined here. This method begins to setup
   * the print.
   * @param g This instance of the Graphics class is used to pass in the instance of Graphics that the print graphics 
   * are drawn on.
   * @param pf This instance of the PageFormat class is used to pass in the format of the page to be printed.
   * @param page This int is used to pass in th eindex of the page to printed
   * @return This method returns an integer value based on whether there is a page to be printed.
   */
  
  public int print(Graphics g, PageFormat pf, int page) 
  {
    if (page > 0) 
    {
      return NO_SUCH_PAGE;
    }
    Graphics2D g2 = (Graphics2D)g;
    g2.translate(pf.getImageableX(), pf.getImageableY());
    setGraphics (g2);
    return PAGE_EXISTS;
  }
  
  
  /*public void printHighScores() //you can pass in the image or create the image in the print method
  {
    try
    {
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(new Printable() {
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
        {
          if (pageIndex != 0)
            return NO_SUCH_PAGE;
          //BufferedImage image = (BufferedImage)frame.createImage(frame.getContentPane().getSize().width,frame.getContentPane().getSize().height);//creates the image - dont need if passing in
          BufferedImage image = ImageIO.read(new File("./resources/Logo.jpg")); 
          //BufferedImage image = (BufferedImage)frame.createImage(475,500);
          frame.getContentPane().paint(image.getGraphics()); //frame is the JFrame variable
//          try
//          {
//            ImageIO.write(image, "png", new File("testImage.png")); //This creates the image as a file - you probably dont need
//            System.out.println("Image was created");
//          }
//          catch (IOException e)
//          {
//          }
          Graphics2D graphics2 = (Graphics2D)graphics;
          graphics2.translate(pageFormat.getImageableX(),pageFormat.getImageableY()); //needed so nothing is cut off
          graphics.drawImage(image, 0, 0, frame.getContentPane().getSize().width, frame.getContentPane().getSize().height, null); //changes the scale so if you change frame.getContentPane().getSize().width to 50, everything is squished into 50 pixels wide
          return PAGE_EXISTS;
        }});     
      job.print();
    }
    catch (PrinterException p)
    {
    }
  }*/
  
  /**
   * This method sets up the graphics for the page that is being printed. It sets the graphics and draws text for the
   * scores and names.
   * @param g This is used to pass in the instance of Graphics that is used for the page.
   */
  public void setGraphics (Graphics g)
  {
    //Graphics2D g2d = (Graphics2D) g;
    //Image img = Toolkit.getDefaultToolkit().getImage("./resources/Logo.jpg");
    //g2d.drawImage(img, 100, 100, pf);
    /*try
    {
      BufferedImage temp = ImageIO.read(new File("./resources/Logo.jpg")); 
      JLabel logo = new JLabel(new ImageIcon(temp)); 
      pf.add(logo); 
    }
    catch(IOException e)
    {
    }*/
    Graphics2D g2d = (Graphics2D) g;
    try{
      BufferedImage image = ImageIO.read(new File("./resources/Logo.png"));
      g2d.drawImage(image, 0, 100, null);
      
      g.setFont (new Font ("Arial", 0, 30));
      
      g.drawString ("MathDash HighScores", 0, 200);
      g.setFont (new Font ("Arial", 0, 20));
      g.drawString ("Name", 0, 200);
      g.drawString ("Score", 110, 200);
      for (int x = 0; x < scores.length(); x++)
      {
        g.drawString (scores.get (x).getName() + "  " + scores.get (x).getScore() , 0, 310 + (25 * x));
        //g.drawString (scores.get (x).getScore() + "", 110, 300 + (30 * x));
        
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  
  /**
   * This method prints the page using a PrinterJob and its method print ().
   * @throws ex Can throw this PrinterException if there is an error printing.
   */
  public void printScores () 
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(this);
    boolean ok = job.printDialog();
    if (ok) 
    {
      try 
      {
        job.print();
      } 
      catch (PrinterException ex) 
      {
      }
    }
  }
}