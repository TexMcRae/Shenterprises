import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.print.event.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 2.0_10.06.2016
 * <b> ~ TOTAL TIME SPENT ~ </b>
 * Ryan: 48h30m (9.75, 12.25, 12, 14.5)
 * Kevin: 32h (8, 8, 7, 9)
 * Max: 32h30m (8.5, 7.5, 8.5, 8)
 * <p><b>Instance Variables:</b>
 * <p><b> scores </b> (private) The list of scores affiliated with the names.
 * <p><b> sc </b> (private) A comparator used in affiliation with scores.
 * <p><b> SCORE_SIZE </b> (static, final) The amount of scores stored at once.
 * <p><b> reader </b> (private) The reader used to interpret files.
 * <p><b> header </b> (static, final) The company's file header.
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
  /**
   * Creates a file.
   */
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
        }
      }
    }catch(Exception e){}
  }
  /**
   * The add() method adds a high score to the database.
   * @param score The score sent in from Game.
   */
  public void add(Score score){
    scores.add(score);
    Collections.sort(scores, sc);
  }
  /**
   * The get method returns a specified value.
   * @param x The index used.
   * @return The score return.
   */
  public Score get(int x){
    return scores.get(x);
  }
  /**
   * The length method returns the number of scores.
   * @return The number of scores.
   */
  public int length(){
    return scores.size();
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
      
      scores = new ArrayList<Score>();
    }catch(Exception e){
    }
  }
  /**
   * The writeToFile() method saves the scores to a file.
   */
  public void writeToFile(){
    createFile();
    try{
      PrintWriter output = new PrintWriter(new FileWriter("./highscores.shen"));
      output.println(header);
      for(int i=0; i<scores.size(); i++){
        if(i > SCORES_SIZE - 1){
          break;
        }
        output.print(scores.get(i).getName() + ",");
        output.println(scores.get(i).getScore());
      }
      output.close();
    }catch(Exception e){
    }
  }
  /**
   * The loadFromFile() method opens the scores in a file.
   */
  public void loadFromFile(){
    createFile();
    scores = new ArrayList<Score>();
    try{
      BufferedReader reader = new BufferedReader(new FileReader("./highscores.shen"));
      if(reader.readLine().equals(header)){
        String line;
        while ((line = reader.readLine()) != null) {
            scores.add(new Score(line.split(",")[0], Integer.parseInt(line.split(",")[1])));
        }
      }
    }catch(Exception e){
    }
  
  }
  /**
   * The printHighscores() method prints the high scores to a printer.
   */
  public void printHighscores(){
    
    Printer printer = new Printer(this);
    printer.printScores();
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
           return -(Integer.compare(s1.getScore(), s2.getScore()));
       }
  }

  
}