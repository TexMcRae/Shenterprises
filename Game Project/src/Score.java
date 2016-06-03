/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.3_03.06.2016
 * <p><b>Instance Variables:</b>
 * <p><b> name </b> (private) The name associated with the score.
 * <p><b> score </b> (private) The score associated with the name.
 */
public class Score{
  private String name;
  private int score;
  /**
   * The constructor sets up the score.
   * @param n The name used.
   * @param s The score used.
   */
  public Score(String n, int s){
    name = n;
    score = s;
  }
  /**
   * Retrieves the name.
   * @return The score's associated name.
   */
  public String getName(){
    return name;
  }
  /**
   * Retrieves the numeric score.
   * @return The score's associated numeric value.
   */
  public int getScore(){
    return score;
  }
}