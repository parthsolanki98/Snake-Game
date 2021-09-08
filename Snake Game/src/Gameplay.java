import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private  ImageIcon ic;

    private int[] snkxlen =new int[750];
    private int[] snkylen = new int[750];
    
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private ImageIcon snakeimage;

    private Timer tm;
    private int delay =100;

    private int lengthofsnake = 3;
    private int moves = 0;

    private int score = 0;

    private int[] enemxpos ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
            525,550,575,600,625,650,675,700,725,750,775,800,825,830};
    private int[] enemypos = {270,295,320,345,370,395,420,445,470,495,520,545,570,595,620,645,670,695};
    private ImageIcon enemyicon;
    private Random rndm = new Random();
    private int xpos = rndm.nextInt(34);
    private int ypos = rndm.nextInt(18);



    public Gameplay(){
              addKeyListener(this);
              setFocusable(true);
              setFocusTraversalKeysEnabled(true);

              tm = new Timer(delay,this);
                 tm.start();
    }
    public void paint(Graphics g) {

        if (moves == 0) {
              snkxlen[0]=100;
              snkylen[0]=400;

            snkxlen[1]=75;
            snkylen[1]=400;

            snkxlen[2]=50;
            snkylen[2]=400;
        }


             //Title image border
        g.setColor(Color.red);
        g.drawRect(24,10,587,186);
        ic = new ImageIcon("Snake-Title.png");
        ic.paintIcon(this,g,25,11);
         //Game Border
        g.setColor(Color.red);
        g.drawRect(24,270,830,450);
        //Fill Rectangle
        g.setColor(Color.GRAY);
        g.fillRect(25,271,829,449);

        //Paint mouth
        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this,g,snkxlen[0],snkylen[0] ) ;

        // Draw Score
        g.setColor(Color.BLUE);
        g.setFont(new Font("arial",Font.PLAIN,25));
        g.drawString("Score : "+score,720,50);
        g.drawString("Length : "+lengthofsnake,720,80);

        //Enemy Image
        enemyicon = new ImageIcon("enemy.png");
        enemyicon.paintIcon(this,g,enemxpos[xpos],enemypos[ypos] );

        for (int a = 0; a<lengthofsnake; a++)
        {
            if (a==0 && right)
            {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this,g,snkxlen[a],snkylen[a] ) ;
            }
            if (a==0 && left)
            {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this,g,snkxlen[a],snkylen[a] ) ;
            }
            if (a==0 && up)
            {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this,g,snkxlen[a],snkylen[a] ) ;
            }
            if (a==0 && down)
            {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this,g,snkxlen[a],snkylen[a] ) ;
            }
            if (a!=0)
            {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snkxlen[a],snkylen[a] ) ;
            }

        }

        if (enemxpos[xpos]==snkxlen[0] && enemypos[ypos]==snkylen[0])
        {
            lengthofsnake++;
            score++;
            xpos = rndm.nextInt(34);
            ypos = rndm.nextInt(18);
        }
        for (int b=1;b<lengthofsnake;b++)
        {
            if (snkxlen[b]==snkxlen[0] && snkylen[b]==snkylen[0])
            {
                 right = false;
                 left = false;
                 up = false;
                 down = false;

                 g.setColor(Color.MAGENTA);
                g.setFont(new Font("arial",Font.ITALIC,65));
                g.drawString("GAME OVER", 300,500);

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.ITALIC,20));
                g.drawString("Press R to Restart", 350,540);
            }
        }
        g.dispose();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (right)
        {
            for (int i=lengthofsnake-1;i>=0;i--)
            {
                snkylen[i+1]=snkylen[i];
            }
             for (int i=lengthofsnake;i>=0;i--)
             {
                 if (i==0)
                 {
                     snkxlen[i] =snkxlen[i]+25;
                 }
                 else
                 {
                     snkxlen[i] =snkxlen[i-1];
                 }
                 if (snkxlen[i]>830)
                 {
                     snkxlen[i]=25;
                 }
             }
        }
        repaint();

        if (left)
        {
            for (int i=lengthofsnake-1;i>=0;i--)
            {
                snkylen[i+1]=snkylen[i];
            }
            for (int i=lengthofsnake;i>=0;i--)
            {
                if (i==0)
                {
                    snkxlen[i] =snkxlen[i]-25;
                }
                else
                {
                    snkxlen[i] =snkxlen[i-1];
                }
                if (snkxlen[i]<25)
                {
                    snkxlen[i]=830;
                }
            }
        }
        repaint();

        if (up)
        {
             for (int i=lengthofsnake-1;i>=0;i--)
            {
                snkxlen[i+1]=snkxlen[i];
            }
            for (int i=lengthofsnake;i>=0;i--)
            {
                if (i==0)
                {
                    snkylen[i] =snkylen[i]-25;
                }
                else
                {
                    snkylen[i] =snkylen[i-1];
                }
                if (snkylen[i]<270)
                {
                    snkylen[i]= 695;
                }
            }
        }
        repaint();
        if (down)
        {
            for (int i=lengthofsnake-1;i>=0;i--)
            {
                snkxlen[i+1]=snkxlen[i];
            }
            for (int i=lengthofsnake;i>=0;i--)
            {
                if (i==0)
                {
                    snkylen[i] =snkylen[i]+25;
                }
                else
                {
                    snkylen[i] =snkylen[i-1];
                }
                if (snkylen[i]>695)
                {
                    snkylen[i]= 270;
                }
            }
        }
        repaint();



    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;
            if (!left)
            {
                right = true;
            }
            else {
                right = false;
                left = true;
            }

            up = false;
            down = false;
        }

        if (e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;
            if (!right)
            {
                left = true;
            }
            else {
                left = false;
                right = true;
            }

            up = false;
            down = false;
        }

        if (e.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;
            if (!down)
            {
                up = true;
            }
            else {
                up = false;
                down = true;
            }
             right = false;
            left = false;

        }

        if (e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            moves++;
            if (!up)
            {
                down = true;
            }
            else {
                down = false;
                up = true;
            }
            right = false;
            left = false;

        }
        if (e.getKeyCode()==KeyEvent.VK_R)
        {
            score=0;
            moves=0;
            lengthofsnake=3;
        }
        repaint();;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
