package main;

import entity.Player;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Scotch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class UI {
    GamePanel gamePanel;
    Font arial_40;

    OBJ_Heart heart = new OBJ_Heart();
    OBJ_Heart heart2 = new OBJ_Heart();
    OBJ_Heart heart3 = new OBJ_Heart();
    OBJ_Scotch scotch = new OBJ_Scotch();
    OBJ_Key key = new OBJ_Key();
    BufferedImage heartImage;
    BufferedImage heartImage2;
    BufferedImage heartImage3;
    BufferedImage scotchImage;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gamePanel, Player player){
        this.gamePanel = gamePanel;


        arial_40 = new Font("Arial", Font.BOLD, 5);

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D graphics2D, Player player){


        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.pink);

        if (player.lives == 2){
            heart3.setLife(false);

        } else if (player.lives == 1) {

            heart2.setLife(false);
            heart3.setLife(false);
        } else if (player.lives == 0) {
            heart.setLife(false);
            heart2.setLife(false);
            heart3.setLife(false);
        }
        heartImage = heart.image;
        heartImage2 = heart2.image;
        heartImage3 = heart3.image;
        scotchImage = scotch.image;
        keyImage = key.image;



        if(player.hasScotchTape == true){
            graphics2D.drawImage(this.scotch.image, 190, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(player.hasKey2 == true){
            graphics2D.drawImage(this.key.image, 245, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }


        graphics2D.drawImage(this.heart.image, 25, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(this.heart2.image, 80, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(this.heart3.image, 135, 25, gamePanel.tileSize, gamePanel.tileSize, null);


        if(messageOn== true){
            graphics2D.setFont(graphics2D.getFont().deriveFont(15F));
            graphics2D.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
            messageCounter++;

            if(messageCounter > 150){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
