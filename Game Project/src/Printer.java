import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 
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
  
  /**
   * This method sets up the graphics for the page that is being printed. It sets the graphics and draws text for the
   * scores and names.
   * @param g This is used to pass in the instance of Graphics that is used for the page.
   */
  public void setGraphics (Graphics g)
  {
    
    Image img = Toolkit.getDefaultToolkit().getImage("./resources/Logo.jpg");
    g.drawImage(img, 10, 10, new JPanel());
    
    g.setFont (new Font ("Arial", 0, 30));
    
    g.drawString ("MathDash HighScores", 0, 100);
    g.setFont (new Font ("Arial", 0, 20));
    g.drawString ("Name", 0, 200);
    g.drawString ("Score", 110, 200);
    for (int x = 0; x < 10; x++)
    {
      g.drawString (scores.get (x).getName(), 0, 300 + (60 * x));
      g.drawString (scores.get (x).getScore() + "", 110, 300 + (60 * x));

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