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
  
  private ArrayList<Score> scores;
  private ScoreComparator sc;
  static final int SCORES_SIZE = 10;
  private BufferedReader reader;
  static final String header = "Shenterprises File";
  /**
   * The constructor sets up the Scores class.
   */
  public Scores(){ 
    createFile();
    scores = new ArrayList<Score>();
    sc = new ScoreComparator();
  }
  
  private void createFile(){
    try{
      File f = new File("./highscores.shen");
      if(!f.exists()){
        f.createNewFile();
        try{
          PrintWriter writer = new PrintWriter(new FileWriter("./highscores.shen"));
          writer.println(header);
          writer.close();
        }catch(Exception e){
          System.out.println("Error in creating file");
        }
      }
    }catch(Exception e){
      System.out.println("error creating file");
    }
  }
  /**
   * The add() method adds a high score to the database.
   * @param score The score sent in from Game.
   * @param name The player's name sent in from Game.
   */
  public void add(Score score){
    scores.add(score);
    Collections.sort(scores, sc);
  }
  /**
   * The clear() method deletes all scores.
   */
  public void clear(){
    createFile();
    try{
      PrintWriter writer = new PrintWriter(new FileWriter("./highscores.shen"));
      writer.println(header);
      writer.close();
    }catch(Exception e){
      System.out.println("File error");
    }
  }
  
  /*public ArrayList<Integer> getScores(){
    
  }
  
  public ArrayList<String> getNames(){
    
  }*/
  
  /**
   * The writeToFile() method saves the scores to a file.
   */
  public void writeToFile(){
    createFile();
    try{
      PrintWriter output = new PrintWriter(new FileWriter("./highscores.shen"));
      output.write(header);
      for(int i=0; i<scores.size(); i++){
        if(i > SCORES_SIZE - 1){
          break;
        }
        output.print(scores.get(i).getName() + ",");
        output.println(scores.get(i).getScore());
      }
      output.close();
    }catch(Exception e){
      System.out.println("File error");
    }
  }
  
  public void loadFromFile(){
    createFile();
    scores = new ArrayList<Score>;
    try{
      BufferedReader reader = new BufferedReader(new FileReader("./highscores.shen"));
      if(reader.readLine().equals(header)){
        String line;
        while ((line = reader.readLine()) != null) {
            scores.add(new Score(line.split(",")[0], Integer.parseInt(line.split(",")[1])));
        }
      }
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
  
  class ScoreComparator implements Comparator<Score>
  {
        public int compare(Score s1, Score s2)
        {
           return Integer.compare(s1.getScore(), s2.getScore());
       }
  }

  
}