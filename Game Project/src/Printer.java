import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 2.0_10.06.2016
 * <b> ~ TOTAL TIME SPENT ~ </b>
 * Ryan: 48h30m (9.75, 12.25, 12, 14.5)
 * Kevin: 32h (8, 8, 7, 9)
 * Max: 32h30m (8.5, 7.5, 8.5, 8)
 * <p><b>Instance Variables:</b>
 * <p><b> scores </b> (private) The scores and their names.
 */
public class Printer implements Printable {
  
  private Scores scores;
  /**
   * The constructor assigns a Scores variable to its instance-level copy.
   * @param s The scores to be assigned to the instance-level copy.
   */
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
  
  
  /**
   * This method sets up the graphics for the page that is being printed. It sets the graphics and draws text for the
   * scores and names.
   * @param g This is used to pass in the instance of Graphics that is used for the page.
   */
  public void setGraphics (Graphics g)
  {
    
    Graphics2D g2d = (Graphics2D) g;
    try{
      BufferedImage image = ImageIO.read(new File("./resources/Logo.png"));
      g2d.drawImage(image, 0, 0, null);
      
      g.setFont (new Font ("Arial", 0, 30));
      
      g.drawString ("MathDash HighScores", 0, 150);
      g.setFont (new Font ("Arial", 0, 20));
      g.drawString ("Name", 0, 200);
      g.drawString ("Score", 110, 200);
      for (int x = 0; x < scores.length(); x++)
      {
        g.drawString (scores.get (x).getName() + "  " + scores.get (x).getScore() , 0, 250 + (25 * x));
        //g.drawString (scores.get (x).getScore() + "", 110, 300 + (30 * x));
        
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  
  /**
   * This method prints the page using a PrinterJob and its method print ().
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