import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.print.*;
/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.0_13.05.2016
 * <p><b>Instance Variables:</b>
 * <p><b> scores </b> (private) The list of scores affiliated with the names.
 * <p><b> name </b> (private) The list of names affiliated with the scores.
 * <p><b> SCORE_SIZE </b> (static, final) The amount of scores stored at once.
 */
public class Scores {
  
  private ArrayList<Integer> scores;
  private ArrayList<String> name;
  static final int SCORE_SIZE = 10;
  /**
   * The constructor sets up the Scores class.
   */
  public Scores() { 
    
  }
  /**
   * The add() method adds a high score to the database.
   * @param score The score sent in from Game.
   * @param name The player's name sent in from Game.
   */
  public void add(int score, String name){
    
  }
  /**
   * The clear() method deletes all scores.
   */
  public void clear(){
    
  }
  
  /*public ArrayList<Integer> getScores(){
    
  }
  
  public ArrayList<String> getNames(){
    
  }*/
  /**
   * The writeToFile() method saves the scores to a file.
   * @param file The file to be saved to.
   */
  public void writeToFile(File file){
    
  }
  /**
   * The print() method prints the high scores to a printer.
   * @param g The graphics to be used.
   * @param pf The format of the given page size.
   * @param page The page id.
   */
  public void print(Graphics g, PageFormat pf, int page){
    
  }
}