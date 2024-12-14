package entity;

import main.BinaryGame;
import main.GamePanel;
import main.KeyHandler;
import main.PLVQuiz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    public final int screenX;
    public final int screenY;
    public int lives;
    public boolean alarmRing;
    public boolean isAllowed;
    public int trashCount = 0;
    public boolean hasKey2;
    public boolean messageShown;
    public boolean hasCOR1;
    public boolean hasCOR2;
    public boolean hasScotchTape;
    public boolean ending;
    GamePanel gamePanel;
    PLVQuiz plvQuiz;
    KeyHandler keyHandler;
    BinaryGame binaryGame;
    Graphics2D graphics2D;
    int hasKey = 0;
    int enterRoute = 0;
    public boolean doneAnswered;
    public boolean dialogue;
    int dialougeCounter;


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

        binaryGame = new BinaryGame();
        plvQuiz = new PLVQuiz();

    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 13;
        worldY = gamePanel.tileSize * 13;
        speed = 4;
        lives = 3;
        direction = "down";
        hasKey2 = false;
        messageShown = false;
        hasCOR1 = false;
         hasCOR2 = false;
        hasScotchTape = false;
        alarmRing = false;
        this.lives = 3;
        doneAnswered = false;
        this.isAllowed = false;
        dialogue = false;
        dialougeCounter = 0;
        ending = false;
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

                        teleportPlayer(21, 44);
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
                        gamePanel.ui.showMessage("I’ve triggered the alarm, the guard must leave immediately.");
                        gamePanel.playSE(1);
                        setAllowed();
                        alarmRing = true;
                        break;
                    }
                    break;
                case "Trash":
                    if(trashCount < 160){
                        gamePanel.ui.showMessage("Looks like there is something" +
                                " in here...");

                    } else {
                        gamePanel.ui.showMessage("You've got a key!");
                        hasKey2 = true;

                    } trashCount++;
                    break;
                case "Electro Exit":
                    if(enterRoute == 8){

                        teleportPlayer(16, 6);
                        enterRoute = 0;

                        break;
                    }
                    enterRoute++;
                    break;
                case "Elevator Null":
                    gamePanel.ui.showMessage("This elevator is under maintenance…");
                    break;
                case "DJ Help":
                    if (hasKey2 == false){
                            gamePanel.ui.showMessage("Hello, can I ask for help? This door is locked." + " I kinda forgot where I hid the key.");

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
                    if(hasCOR1 == true && dialogue == false){
                        dialogue = true;
                        gamePanel.ui.showMessage("Finally, a piece of COR. Now to find the last one. Where could Francis have put it?");
                    }
                    enterRoute++;
                    break;
                case "Lab Door":
                    if(enterRoute == 8){
                        teleportPlayer(9,24);
                        enterRoute = 0;
                        break;
                    } enterRoute++;
                    break;
                case "Lab Door Exit":
                    if(enterRoute == 8){
                        teleportPlayer(13,6);
                        enterRoute = 0;
                        break;
                    } enterRoute++;
                    break;
                case "Temba":
                    if (doneAnswered == false) {
                        gamePanel.ui.showMessage("I’ll give you a chance to finish your task without me chasing you. First, head to the Computer Laboratory.");
                    } else {
                        hasCOR1 = true;
                        gamePanel.ui.showMessage("You’ve finished your task? Good. That’s exactly what I expected.      Received a piece of COR.");
                        gamePanel.object[i] = null;
                    }
                    break;
                case "Computer":
                    teleportPlayer(21,23);
                    binaryGame.createAndShowGUI();
                    while (!binaryGame.isGameFinished()) {
                        System.out.println(binaryGame.isGameFinished());
                        // Wait until the game ends
                    }
                    int totalScore = binaryGame.totalScore;
                    if (totalScore < 3) {
                        this.lives--;
                    }
                    doneAnswered = true;
                    gamePanel.object[i] = null;
                    break;
                case "Door Null":
                    if(hasCOR1 == true && hasCOR2 == true && hasScotchTape == true){
                        if(enterRoute == 8){

                            teleportPlayer(15, 39);
                            gamePanel.tileManager.changeMap(1);
                            gamePanel.asssetSetter.removeObject();
                            gamePanel.asssetSetter.setObject();
                            enterRoute = 0;
                            break;
                        }
                        enterRoute++;
                        break;
                    } else {
                        gamePanel.ui.showMessage("You cannot enter until you have the items needed: 2 pieces of COR, Scotchtape");
                        break;
                    }

                case "Student":

                        if (dialougeCounter == 0){
                            gamePanel.ui.showMessage("Looks like you've completed all the tasks. Well done!");
                        } if (dialougeCounter > 150){
                            gamePanel.ui.showMessage("This isn’t the end. Here’s the final challenge to get what you need.");
                        } if (dialougeCounter == 300){

                            plvQuiz.run();
                            while (!plvQuiz.isFinished()){
                                System.out.println(plvQuiz.isFinished);

                            }

                            gamePanel.object[17] = null;
                            gamePanel.object[16] = null;
                            gamePanel.object[15] = null;
                            gamePanel.ui.showMessage("Nice try, Jemy! Here your prize for trying! Received another piece of COR.");

                            hasCOR2 = true;
                            if(plvQuiz.isFailed){
                                lives--;
                            }
                            break;


                        }
                        dialougeCounter++;
                        break;
                case "OSA":
                    gamePanel.ui.showMessage("Congratulations, here's the new printed COR.");
                    gamePanel.playSE(2);
                    gamePanel.object[i] = null;
                    this.ending = true;
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


