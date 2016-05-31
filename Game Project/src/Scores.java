import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.print.*;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> scores </b> (private) The list of scores affiliated with the names.
 * <p><b> name </b> (private) The list of names affiliated with the scores.
 * <p><b> SCORE_SIZE </b> (static, final) The amount of scores stored at once.
 */
public class Scores {
  
  private ArrayList<Integer> scores;
  private ArrayList<String> name;
  static final int SCORE_SIZE = 10;
  private BufferedReader reader;
  static private final String header = "Shenterprises File";
  /**
   * The constructor sets up the Scores class.
   */
  public Scores(){ 
    try{
      reader = new BufferedReader(new FileReader("./highscores.shen"));
      String temp = reader.readLine();
      if (temp != header)
      {
        
      }
      temp = reader.readLine();
      while (temp != null)
      {
        //scores.add(temp);
        //scores.add(reader.readLine());
        temp = reader.readLine();
      }
    }catch(FileNotFoundException e){
      System.out.println("File not found");
      System.out.println("Creating File");
      PrintWriter writer = new PrintWriter(new FileWriter("./highscores.shen"));
      writer.println(header);
      writer.close();
    }catch(IOException e){
      System.out.println("io exception");
    }
    
  }
  /**
   * The add() method adds a high score to the database.
   * @param score The score sent in from Game.
   * @param name The player's name sent in from Game.
   */
  public void add(int score, String name){
    scores.add(score);
    Collections.sort(scores);
    //int loc = scores.indexOf(score);
    //name.add(loc,name);
  }
  /**
   * The clear() method deletes all scores.
   */
  public void clear() throws FileNotFoundException, IOException{
    PrintWriter writer = new PrintWriter(new FileWriter("./highscores.shen"));
    writer.close();
  }
  
  /*public ArrayList<Integer> getScores(){
    
  }
  
  public ArrayList<String> getNames(){
    
  }*/
  
  /**
   * The writeToFile() method saves the scores to a file.
   */
  public void writeToFile(){
    try{
      PrintWriter output = new PrintWriter(new FileWriter("./highscores.shen"));
      for(int i=0; i<name.size(); i++){
        output.println(name.get(i));
        output.println(scores.get(i));
      }
      output.close();
    }catch(FileNotFoundException e){
      System.out.println("File not found");
      System.out.println("Creating File");
      PrintWriter writer = new PrintWriter(new FileWriter("./highscores.shen"));
      writer.println(header);
      writer.close();
    }catch(IOException e){
      System.out.println("io exception");
    }
  }
  /**
   * The printHighscores() method prints the high scores to a printer.
   */
  public void printHighscores(){
    try{
      String defaultPrinter =
        PrintServiceLookup.lookupDefaultPrintService().getName();
      System.out.println("Default printer: " + defaultPrinter);
      
      PrintService service = PrintServiceLookup.lookupDefaultPrintService();
      
      FileInputStream in = new FileInputStream(new File("./highscores.shen"));
      
      PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
      pras.add(new Copies(1));
      
      
      DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
      Doc doc = new SimpleDoc(in, flavor, null);
      
      DocPrintJob job = service.createPrintJob();
      PrintJobWatcher pjw = new PrintJobWatcher(job);
      job.print(doc, pras);
      pjw.waitForDone();
      in.close();
      
      // send FF to eject the page
      InputStream ff = new ByteArrayInputStream("\f".getBytes());
      Doc docff = new SimpleDoc(ff, flavor, null);
      DocPrintJob jobff = service.createPrintJob();
      pjw = new PrintJobWatcher(jobff);
      jobff.print(docff, null);
      pjw.waitForDone();
    }catch(PrintException e){
      System.out.println("Print exception");
    }catch(IOException e){
      System.out.println("io exception");
    }
  }
  
  class PrintJobWatcher {
    
    boolean done = false;
  
    PrintJobWatcher(DocPrintJob job) {
      job.addPrintJobListener(new PrintJobAdapter() {
        public void printJobCanceled(PrintJobEvent pje) {
          allDone();
        }
        public void printJobCompleted(PrintJobEvent pje) {
          allDone();
        }
        public void printJobFailed(PrintJobEvent pje) {
          allDone();
        }
        public void printJobNoMoreEvents(PrintJobEvent pje) {
          allDone();
        }
        void allDone() {
          synchronized (PrintJobWatcher.this) {
            done = true;
            System.out.println("Printing done ...");
            PrintJobWatcher.this.notify();
          }
        }
      });
    }
    public synchronized void waitForDone() {
      try {
        while (!done) {
          wait();
        }
      } catch (InterruptedException e) {
      }
    }
  }
  
}