package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //settings of the screen
    final int originalTileSize = 16; //16x16 tile so each character should be 16x16 will be scaled later
    final int scale = 3;//now the characters would be 48x48 after scaled from 16x16;

    final int tileSize = originalTileSize * scale;//48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;//768 pixels
    final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //FPS :I Chose 60 FPS
    int FPS = 60;

    HandleAllKeys keys = new HandleAllKeys();
    Thread gameThread;

    //set players deafult position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//better rendering  performance
        this.addKeyListener(keys);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();//automatically call the run method
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //1 second to have a more precise calculation(Draw Interval)
        double nextDrawTime = System.nanoTime() + drawInterval; //nano time in current system time then the draw interval which is 60 frames per second


        //game loop
        while (gameThread != null) {

            //character positions
            update();

            //time in between

            //draw or put the screen on the screen so basically make the character move
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //makes the time into milliseconds and lets us use the sleep method


                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);//type casted

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if(keys.upP){
            playerY -= playerSpeed;
        }
        else if(keys.downP){
            playerY += playerSpeed;
        }
        else if(keys.rightP){
            playerX += playerSpeed;
        }
        else if(keys.leftP){
            playerX -= playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        //method from JPanel thanks to java
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;//gives the other graphics more function

        g2.setColor(Color.WHITE);//sets the colour to the character

        g2.fillRect(playerX,playerY,tileSize,tileSize);//x,y,width,height

        g2.dispose();//to save memory (good practice)

    }
}
