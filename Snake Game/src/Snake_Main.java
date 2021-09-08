import javax.swing.*;
import java.awt.*;



public class Snake_Main {
          public static void main(String[] args){
              JFrame jf = new JFrame();
              jf.setTitle("My Snake Game");
              jf.setBounds(10,10,920,920);
              jf.setResizable(true);
              jf.setVisible(true);
              jf.setBackground(Color.yellow);

              Gameplay gp = new Gameplay();
              jf.add(gp);

              
          }

}
