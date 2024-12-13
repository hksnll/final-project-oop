package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    public int lives;
    int enterRoute = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle(0, 0, 24, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();

        this.lives = 3;

    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 13;
        worldY = gamePanel.tileSize * 13;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true){
            if(keyHandler.upPressed){
                direction = "up";

            } else if(keyHandler.downPressed){
                direction = "down";

            } else if (keyHandler.rightPressed) {
                direction = "right";

            } else if (keyHandler.leftPressed) {
                direction = "left";

            }

            // CHECK TILE COLLISION
            collisionOn =  false;
            gamePanel.collisionChecker.checkTile(this);

            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false){

                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }


            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gamePanel.object[i].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gamePanel.ui.showMessage("You got a Key!");
                    gamePanel.object[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0){
                        gamePanel.playSE(1);
                        gamePanel.object[i] = null;
                        hasKey--;
                        teleportPlayer(13, 13);
                        System.out.println("Key: " + hasKey);
                    } else {
                        System.out.println("You need a key to open the door!");
                    }
                    break;
                case "CEIT Door":
                    if(enterRoute == 8){
                        teleportPlayer(9, 44);
                        enterRoute = 0;
                    }

                    enterRoute++;
                    break;
                case "CEIT Door Exit":
                    if(enterRoute == 8){
                        teleportPlayer(29, 17);
                        enterRoute = 0;
                    }

                    enterRoute++;
                    break;
                case "Stair 1":
                    if(enterRoute == 8){

                        teleportPlayer(30, 8);
                        gamePanel.tileManager.changeMap(2);
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute = 0;
                    }

                    enterRoute++;
                    break;
                case "Stair 2":
                    if(enterRoute == 8){
                        teleportPlayer(30, 44);
                        gamePanel.tileManager.changeMap(0);
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute = 0;
                    }
                    enterRoute++;
                    break;
                case "CR":
                    if(enterRoute == 8){

                        teleportPlayer(9, 25);
                        gamePanel.tileManager.changeMap(2);
                        enterRoute = 0;
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute++;
                        break;
                    }
                    enterRoute++;
                    break;
                case "CR Exit":
                    if(enterRoute == 8){

                        teleportPlayer(21, 44);
                        gamePanel.tileManager.changeMap(0);
                        enterRoute = 0;
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute++;
                        break;
                    }
                    enterRoute++;
                    break;
            }
        }

    }

    public void draw(Graphics2D graphics2D){

//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(worldX, worldY, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNumber == 1){
                    image = up1;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1){
                    image = down1;
                }
                if (spriteNumber == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1){
                    image = left1;
                }
                if (spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1){
                    image = right1;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
                break;
        }

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void teleportPlayer(int x, int y){
        this.worldX = x * gamePanel.tileSize;
        this.worldY = y * gamePanel.tileSize;
    }
}


