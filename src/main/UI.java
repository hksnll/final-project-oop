package main;

import entity.Player;
import object.*;

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
    OBJ_COR cor1 = new OBJ_COR();
    OBJ_COR cor2 = new OBJ_COR();
    OBJ_COR_End corEnd = new OBJ_COR_End();
    BufferedImage heartImage;
    BufferedImage heartImage2;
    BufferedImage heartImage3;
    BufferedImage scotchImage;
    BufferedImage keyImage;
    BufferedImage corImage1;
    BufferedImage corImage2;
    BufferedImage corEndImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gamePanel, Player player){
        this.gamePanel = gamePanel;


        arial_40 = new Font("Arial", Font.BOLD, 5);
        cor1.setNum(1);
        cor2.setNum(2);
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
            heart2.setLife(true);

        } else if (player.lives == 1) {

            heart2.setLife(false);
            heart3.setLife(false);
        } else if (player.lives == 0) {
            heart.setLife(false);
            heart2.setLife(false);
            heart3.setLife(false);
            gamePanel.stopMusic();
        }else if(player.lives == 3){
            System.out.println(heart3.isLife);

        }
        heartImage = heart.image;
        heartImage2 = heart2.image;
        heartImage3 = heart3.image;
        scotchImage = scotch.image;
        keyImage = key.image;
        corImage1 = cor1.image;
        corImage2 = cor2.image;
        corEndImage = corEnd.image;

        if(player.hasCOR1 == true){
            graphics2D.drawImage(this.corImage1, 290, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(player.hasCOR2 == true){
            graphics2D.drawImage(this.corImage2, 290, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }


        if(player.hasScotchTape == true){
            graphics2D.drawImage(this.scotch.image, 190, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(player.hasKey2 == true){
            graphics2D.drawImage(this.key.image, 245, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        }


        graphics2D.drawImage(this.heart.image, 25, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(this.heart2.image, 80, 25, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(this.heart3.image, 135, 25, gamePanel.tileSize, gamePanel.tileSize, null);

        if(player.ending){
            graphics2D.drawImage(this.corEnd.image, player.screenX / 2, player.screenY / 2, 7*gamePanel.tileSize, 7* gamePanel.tileSize, null);
        }



        if(messageOn== true){
            graphics2D.setFont(graphics2D.getFont().deriveFont(15F));
            // Set background color
            graphics2D.setColor(Color.BLACK);
// Draw the rectangle behind the text
            graphics2D.fillRect(gamePanel.tileSize / 2 - 10, gamePanel.tileSize * 10 - 20,
                    graphics2D.getFontMetrics().stringWidth(message) + 20, 30);

// Set text color
            graphics2D.setColor(Color.WHITE);
// Draw the string
            graphics2D.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 10);

            messageCounter++;

            if(messageCounter > 150){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
