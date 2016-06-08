import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Auto Generated Java Class.
 */

public class Time implements ActionListener {
  static Timer timer;
  static int time;
  public Time()
  {
    time = 0;
    timer = new Timer(1000, (ActionListener)this);//fires an actionlistener every n milliseconds
    timer.start();
  }
  public void actionPerformed(ActionEvent a) {
    time++;
  }
}
