package main;

import object.*;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        if (gamePanel.tileManager.mapNumber == 0){


            gamePanel.object[0] = new OBJ_Key();
            gamePanel.object[0].setWorld(27 * gamePanel.tileSize, 13 * gamePanel.tileSize);

            gamePanel.object[1] = new OBJ_Chest();
            gamePanel.object[1].setWorld(25 * gamePanel.tileSize, 13 * gamePanel.tileSize);

            gamePanel.object[2] = new OBJ_Door();
            gamePanel.object[2].setWorld(27 * gamePanel.tileSize, 13 * gamePanel.tileSize);

            gamePanel.object[3] = new OBJ_Door_CEIT();
            gamePanel.object[3].setWorld(30 * gamePanel.tileSize, 17 * gamePanel.tileSize);

            gamePanel.object[4] = new OBJ_Door_CEIT_Exit();
            gamePanel.object[4].setWorld(8 * gamePanel.tileSize, 44 * gamePanel.tileSize);

        }


    }
}
