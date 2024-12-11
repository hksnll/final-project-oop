package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.object[0] = new OBJ_Key();
        gamePanel.object[0].worldX = 13 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 14 * gamePanel.tileSize;

        gamePanel.object[1] = new OBJ_Chest();
        gamePanel.object[1].worldX = 25 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 13 * gamePanel.tileSize;

        gamePanel.object[2] = new OBJ_Door();
        gamePanel.object[2].worldX = 27 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 13 * gamePanel.tileSize;


    }
}
