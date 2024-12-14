package main;

import object.*;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void removeObject(){
        for(int i = 0; i < gamePanel.object.length; i++){
            if (gamePanel.object[i] != null){
                gamePanel.object[i] = null;
            }
        }
    }

    public void setObject(){
        if (gamePanel.tileManager.mapNumber == 0){
            System.out.println("Map 0");
            gamePanel.object[0] = new OBJ_Key();
            gamePanel.object[0].setWorld(999 * gamePanel.tileSize, 14 * gamePanel.tileSize);

            gamePanel.object[2] = new OBJ_Door();
            gamePanel.object[2].setWorld(999 * gamePanel.tileSize, 13 * gamePanel.tileSize);

            gamePanel.object[3] = new OBJ_Door_CEIT();
            gamePanel.object[3].setWorld(30 * gamePanel.tileSize, 17 * gamePanel.tileSize);

            gamePanel.object[4] = new OBJ_Door_CEIT_Exit();
            gamePanel.object[4].setWorld(8 * gamePanel.tileSize, 44 * gamePanel.tileSize);

            gamePanel.object[5] = new OBJ_Stairs(1);
            gamePanel.object[5].setName("Stair 1");
            gamePanel.object[5].setWorld(29 * gamePanel.tileSize, 43 * gamePanel.tileSize);

            gamePanel.object[6] = new OBJ_Stairs(2);
            gamePanel.object[6].setName("Stair 1");
            gamePanel.object[6].setWorld(30 * gamePanel.tileSize, 43 * gamePanel.tileSize);

            gamePanel.object[7] = new OBJ_CR();
            gamePanel.object[7].setName("CR");
            gamePanel.object[7].setWorld(21 * gamePanel.tileSize, 43 * gamePanel.tileSize);

            if (gamePanel.player.isAllowed == false) {
                gamePanel.object[8] = new OBJ_Guard();
                gamePanel.object[8].setWorld(19 * gamePanel.tileSize, 43 * gamePanel.tileSize);
            }
            gamePanel.object[9] = new OBJ_Null();
            gamePanel.object[9].setName("Elevator Null");
            gamePanel.object[9].setWorld(24 * gamePanel.tileSize, 43 * gamePanel.tileSize);

            gamePanel.object[10] = new OBJ_Door_CABA(0);
            gamePanel.object[10].setName("CABA Door");
            gamePanel.object[10].setWorld(15 * gamePanel.tileSize, 17 * gamePanel.tileSize);

        } else if (gamePanel.tileManager.mapNumber == 2){
            gamePanel.object[0] = new OBJ_Stairs(1);
            gamePanel.object[0].setName("Stair 2");
            gamePanel.object[0].setWorld(29 * gamePanel.tileSize, 9 * gamePanel.tileSize);

            gamePanel.object[1] = new OBJ_Stairs(2);
            gamePanel.object[1].setName("Stair 2");
            gamePanel.object[1].setWorld(30 * gamePanel.tileSize, 9 * gamePanel.tileSize);

            gamePanel.object[2] = new OBJ_CR();
            gamePanel.object[2].setName("CR Exit");
            gamePanel.object[2].setWorld(9 * gamePanel.tileSize, 25 * gamePanel.tileSize);

            gamePanel.object[3] = new OBJ_Wash();
            gamePanel.object[3].setWorld(11 * gamePanel.tileSize, 21 * gamePanel.tileSize);

            gamePanel.object[4] = new OBJ_Wash();
            gamePanel.object[4].setWorld(10 * gamePanel.tileSize, 21 * gamePanel.tileSize);

            gamePanel.object[5] = new OBJ_Trash();
            gamePanel.object[5].setWorld(20 * gamePanel.tileSize, 22 * gamePanel.tileSize);

            gamePanel.object[6] = new OBJ_Electro();
            gamePanel.object[6].setName("Electro Entrance");
            gamePanel.object[6].setWorld(16 * gamePanel.tileSize, 5 * gamePanel.tileSize);

            gamePanel.object[7] = new OBJ_Alarm();
            gamePanel.object[7].setWorld(10 * gamePanel.tileSize, 4 * gamePanel.tileSize);

            gamePanel.object[8] = new OBJ_Electro();
            gamePanel.object[8].setName("Electro Exit");
            gamePanel.object[8].setWorld(9 * gamePanel.tileSize, 41 * gamePanel.tileSize);

            gamePanel.object[9] = new OBJ_DJ();
            gamePanel.object[9].setName("DJ Help");
            gamePanel.object[9].setWorld(19 * gamePanel.tileSize, 5 * gamePanel.tileSize);

            gamePanel.object[10] = new OBJ_Chest();
            gamePanel.object[10].setName("Fake Chest");
            gamePanel.object[10].setWorld(12 * gamePanel.tileSize, 33 * gamePanel.tileSize);

            gamePanel.object[11] = new OBJ_Chest();
            gamePanel.object[11].setName("Fake Chest");
            gamePanel.object[11].setWorld(11 * gamePanel.tileSize, 35 * gamePanel.tileSize);

            gamePanel.object[11] = new OBJ_Chest();
            gamePanel.object[11].setName("Fake Chest");
            gamePanel.object[11].setWorld(20 * gamePanel.tileSize, 39 * gamePanel.tileSize);

            gamePanel.object[12] = new OBJ_Chest();
            gamePanel.object[12].setName("Fake Chest");
            gamePanel.object[12].setWorld(23 * gamePanel.tileSize, 35 * gamePanel.tileSize);

            if (gamePanel.player.hasScotchTape == false) {
                gamePanel.object[13] = new OBJ_Chest();
                gamePanel.object[13].setWorld(28 * gamePanel.tileSize, 37 * gamePanel.tileSize);
            }


        } else if (gamePanel.tileManager.mapNumber == 1){
            gamePanel.object[0] = new OBJ_Door_CABA(1);
            gamePanel.object[0].setName("CABA Door EXIT");
            gamePanel.object[0].setWorld(31 * gamePanel.tileSize, 6 * gamePanel.tileSize);
        }



    }
}
