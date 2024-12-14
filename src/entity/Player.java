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
    public boolean alarmRing = false;
    public boolean isAllowed;
    public int trashCount = 0;
    public boolean hasKey2 = false;
    public boolean messageShown = false;
    public boolean hasCOR1 = false;
    public boolean hasCOR2 = false;
    public boolean hasScotchTape = false;


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

        this.isAllowed = false;

    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 13;
        worldY = gamePanel.tileSize * 13;
        speed = 6;
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
                    if (isAllowed == true) {
                        if(enterRoute > 8){
                            trashCount = 0;
                            teleportPlayer(9, 25);
                            gamePanel.tileManager.changeMap(2);
                            enterRoute = 0;
                            gamePanel.asssetSetter.removeObject();
                            gamePanel.asssetSetter.setObject();

                            break;
                        }
                        enterRoute++;
                    }
                    else gamePanel.ui.showMessage("HEY, YOU! Males are not allowed here. Get out!");

                    break;
                case "CR Exit":
                    if(enterRoute == 8){

                        teleportPlayer(21, 43);
                        gamePanel.tileManager.changeMap(0);
                        enterRoute = 0;
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();

                        break;
                    }
                    enterRoute++;
                    break;
                case "Electro Entrance":
                    if ( hasKey2 ) {
                        if(enterRoute == 8){

                            teleportPlayer(9, 41);
                            enterRoute = 0;

                            break;
                        }enterRoute++;
                    } else {
                        gamePanel.ui.showMessage("The door is locked!");
                    }

                    break;
                case "Alarm":
                    if(alarmRing == false){
                        gamePanel.playSE(1);
                        setAllowed();
                        alarmRing = true;
                        break;
                    }
                    break;
                case "Trash":
                    if(trashCount < 160){
                        gamePanel.ui.showMessage("Looks like there is something" +
                                "in here...");

                    } else {
                        gamePanel.ui.showMessage("You've got a key!");
                        hasKey2 = true;

                    } trashCount++;
                    break;
                case "Electro Exit":
                    if(enterRoute == 8){

                        teleportPlayer(17, 8);
                        enterRoute = 0;

                        break;
                    }
                    enterRoute++;
                    break;
                case "Elevator Null":
                    gamePanel.ui.showMessage("This elevator is under maintenance …");
                    break;
                case "DJ Help":
                    if (hasKey2 == false){
                            gamePanel.ui.showMessage("Hello, can I ask for help? This door is locked." + "I kinda forgot where I hid the key.");

                    } else {
                        gamePanel.ui.showMessage(
                                "Great job! Careful—there’s a trap inside. Enter if you dare!");

                    } break;
                case "Fake Chest":
                    this.lives--;
                    gamePanel.ui.showMessage("Ouch! That was a trap!");
                    gamePanel.object[i] = null;
                    break;
                case "Chest":
                    gamePanel.ui.showMessage("You found a Scotch Tape inside Chest!");
                    gamePanel.object[i] = null;
                    hasScotchTape = true;
                    break;
                case "CABA Door":
                    if(enterRoute == 8){

                        teleportPlayer(30, 6);
                        gamePanel.tileManager.changeMap(1);
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute = 0;
                        break;
                    }
                    enterRoute++;
                    break;
                case "CABA Door EXIT":
                    if(enterRoute == 8){

                        teleportPlayer(16, 17);
                        gamePanel.tileManager.changeMap(0);
                        gamePanel.asssetSetter.removeObject();
                        gamePanel.asssetSetter.setObject();
                        enterRoute = 0;
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
    public void setAllowed(){
        this.isAllowed = true;
    }
}


