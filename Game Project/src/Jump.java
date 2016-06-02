/**
 * @author Ryan McRae, Kevin Shen, Max Sossin
 * @version 1.1_20.05.2016
 * The Jump class allows for animations to take place by remotely changing their values.
 * <p><b> Instance variables </b>
 * <p><b> g2d </b> (private) The type representing the animation with immediate attention for variable changes.
 */
public class Jump extends Thread {
  int type;
  static double x;
  static boolean isRunning,stop;
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
    if (type==0 && stop == false) {
      for (x = 0 ; x < Math.PI ; x += 0.01){
        Player.y = (int) (Math.sin (x) * 350);
        //Player.y = (int) (Math.sin (x*10) * 25);
        Game.delay(5-Game.difficulty);
        while(Game.paused){
          System.out.print("");
        }
      } 
      stop = true;
      x=0;
      return;
    }/*
    else if (type == 1)
    {
      for (int i = 0; i < 3200; i++)
      {
        MathDash.i++;
        Game.delay(1000000);
      }
    }*/
    else
    {
      while (isRunning)
      {
        NumberBall.x += 1;
        Game.delay(10-Game.difficulty*2);
        while(Game.paused){
          System.out.print("");
        }
      }
      NumberBall.x = 0;
    };//thing
  }
}
