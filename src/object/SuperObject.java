package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public  int solidDefaultAreaX = 0;
    public  int solidDefaultAreaY = 0;
    public boolean isPickedUp = false;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void draw2(Graphics2D graphics2D, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public SuperObject(){

    }

    public void setWorld(int x, int y){
        if(this.isPickedUp == false){
            this.worldX = x;
            this.worldY = y;
        } else {
            this.worldY = 999;
            this.worldX = 999;
        }

    }
    public void setName(String name){
        this.name = name;
    }

    public void setNum(int i) {

    }
}

