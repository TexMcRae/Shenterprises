/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * The Jump class allows for animations to take place by remotely changing their values.
 * <p><b> Instance variables </b>
 * <p><b> g2d </b> (private) The type representing the animation with immediate attention for variable changes.
 */
public class Jump extends Thread {
  int type;
  
  /**
   * The constructor assigns the given value to type.
   * @param type The type representing the animation with immediate attention for variable changes.
   */
  public Jump (int type){
    this.type = type;
  }
  /**
   * The run() method allows code to be executed simultaneously alongside other graphical code without the interference of Thread.sleep().
   */
  public void run() {
    if (type==0)
      for (double x = 0 ; x < Math.PI ; x += 0.01){
        Player.y = (int) (Math.sin (x) * 300);
        //Player.y = (int) (Math.sin (x*10) * 25);
        Game.delay(1);
      } 
    else
    {
      while (true)
      {
        NumberBall.x += 1;
        Game.delay(10);
      }
    };//thing
  }
}
