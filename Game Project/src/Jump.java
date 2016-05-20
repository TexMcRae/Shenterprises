public class Jump extends Thread {
  int type;
  public Jump (int type){
    this.type = type;
  }
  public void run() {
    if (type==0)
      for (double x = 0 ; x < Math.PI ; x += 0.01){
        Player.x = (int) (Math.sin (x) * 300);
        //Player.y = (int) (Math.sin (x*10) * 25);
        Game.delay(6);
      } 
    else 
      ;//thing
  }
}
