package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
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
    Player player = new Player(this, keyHandler);
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



//    @Override
//    public void run() {
//        double drawInterval = 1000000000 / framesPerSecond;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//            // UPDATE
//            update();
//            // DRAW
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            nextDrawTime += drawInterval;
//        }
//    }



    @Override
    public void run(){

        double drawInterval =   1000000000 / framesPerSecond;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        byte drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;



            if (delta >= 1) {
            update();
            repaint();
            delta--;
            drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void update(){
        player.update();

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);


        Graphics2D graphics2D = (Graphics2D) graphics;
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
