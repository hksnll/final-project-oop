package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
    int entityRightColumn = entityRightWorldX / gamePanel.tileSize;
    int entityTopRow = entityTopWorldY / gamePanel.tileSize;
    int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

    int tileNumber1, tileNumber2;

    switch (entity.direction){
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityTopRow];
            if (gamePanel.tileManager.tile[tileNumber1].collision == true ||
                gamePanel.tileManager.tile[tileNumber2].collision == true){
                entity.collisionOn = true;

            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
            tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];
            if (gamePanel.tileManager.tile[tileNumber1].collision == true ||
                    gamePanel.tileManager.tile[tileNumber2].collision == true){
                entity.collisionOn = true;

            }
            break;
        case "left":
            entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
            if (gamePanel.tileManager.tile[tileNumber1].collision == true ||
                    gamePanel.tileManager.tile[tileNumber2].collision == true){
                entity.collisionOn = true;

            }
            break;
        case "right":
            entityRightColumn = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];
            if (gamePanel.tileManager.tile[tileNumber1].collision == true ||
                    gamePanel.tileManager.tile[tileNumber2].collision == true){
                entity.collisionOn = true;

            }
            break;
    }

    }
    public int checkObject(Entity entity, boolean player){
        int indexer = 999;

        for(int i = 0; i < gamePanel.object.length; i++){
            if (gamePanel.object[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;


                gamePanel.object[i].solidArea.x = gamePanel.object[i].worldX + gamePanel.object[i].solidArea.x;
                gamePanel.object[i].solidArea.y = gamePanel.object[i].worldY + gamePanel.object[i].solidArea.y;

                switch (entity.direction){
                    case "up":

                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            System.out.println("Hi");
                            if(gamePanel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                indexer = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if(gamePanel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                indexer = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if(gamePanel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                indexer = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if(gamePanel.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                indexer = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.object[i].solidArea.x =  gamePanel.object[i].solidDefaultAreaX;
                gamePanel.object[i].solidArea.y =  gamePanel.object[i].solidDefaultAreaY;
            }

        }

        return indexer;
    }
}
