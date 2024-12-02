package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 758
    final int screenHeight = tileSize * maxScreenRow; // 576

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int framesPerSecond = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / framesPerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null){
            System.out.println("Running");


            // UPDATE
            update();

            // DRAW
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime += drawInterval;
        }

    }

    public void update(){

        if(keyHandler.upPressed){
            playerY -= playerSpeed;
        } else if(keyHandler.downPressed){
            playerY += playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.white);

        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2D.dispose();
    }
}
