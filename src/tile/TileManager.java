package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class TileManager {
    GamePanel gamePanel;
    public int mapNumber;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[1000];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        mapNumber = 0;
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GRASS2.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brickk.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_0_0.png"));
            tile[2].collision = false;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_0_16.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_16_0.png"));
            tile[4].collision = false;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_16_16.png"));
            tile[5].collision = false;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_32_0.png"));
            tile[6].collision = false;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_32_16.png"));
            tile[7].collision = false;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_48_0.png"));
            tile[8].collision = false;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_48_16.png"));
            tile[9].collision = false;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_64_0.png"));
            tile[10].collision = false;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_64_16.png"));
            tile[11].collision = false;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_80_0.png"));
            tile[12].collision = false;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_80_16.png"));
            tile[13].collision = false;
//
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chunks/chunk_64_16.png"));
            tile[15].collision = false;

            int xFile = 0;
            int xNum = 1;
            int yFile = 0;
            int yNum = 1;
            int tileNumber = 16;
            while(true){


                tile[tileNumber] = new Tile();
                String fileName= "/tiles/STD/std_"+String.valueOf(yFile)+"_"+String.valueOf(xFile)+".png";

                tile[tileNumber].image = ImageIO.read(getClass().getResourceAsStream( fileName));
                tile[tileNumber].collision = true;


                xNum++;
                xFile+= 16;
                tileNumber++;
                if(xNum > 5){

                    yFile+=16;
                    xNum = 1;
                    xFile = 0;

                    yNum++;

                }



                if(yNum > 14){

                    break;
                }

            }

            tile[86] = new Tile();
            tile[86].image = ImageIO.read(getClass().getResourceAsStream("/tiles/roof.png"));
            tile[86].collision = false;

            tile[87] = new Tile();
            tile[87].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
            tile[87].collision = false;

            tile[88] = new Tile();
            tile[88].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wal1l.png"));
            tile[88].collision = false;

            tile[89] = new Tile();
            tile[89].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));
            tile[89].collision = false;

            tile[90] = new Tile();
            tile[90].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wal1l.png"));
            tile[90].collision = true;

            tile[91] = new Tile();
            tile[91].image = ImageIO.read(getClass().getResourceAsStream("/tiles/POSTER/POSTER_0_0.png"));
            tile[91].collision = true;

            tile[92] = new Tile();
            tile[92].image = ImageIO.read(getClass().getResourceAsStream("/tiles/POSTER/POSTER_0_16.png"));
            tile[92].collision = true;

            tile[93] = new Tile();
            tile[93].image = ImageIO.read(getClass().getResourceAsStream("/tiles/skyyy.png"));
            tile[93].collision = true;

            tile[94] = new Tile();
            tile[94].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Elevator/Elevator_0_0.png"));
            tile[94].collision = true;

            tile[95] = new Tile();
            tile[95].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Elevator/Elevator_0_16.png"));
            tile[95].collision = true;

            tile[96] = new Tile();
            tile[96].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Elevator/Elevator_16_0.png"));
            tile[96].collision = true;

            tile[97] = new Tile();
            tile[97].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Elevator/Elevator_16_16.png"));
            tile[97].collision = true;

            tile[98] = new Tile();
            tile[98].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stairs/stairs_0_0.png"));
            tile[98].collision = false;

            tile[99] = new Tile();
            tile[99].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stairs/stairs_16_0.png"));
            tile[99].collision = false;

            tile[100] = new Tile();
            tile[100].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Woman Sign.png"));
            tile[100].collision = false;

            tile[101] = new Tile();
            tile[101].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Man Sign.png"));
            tile[101].collision = false;

            tile[102] = new Tile();
            tile[102].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CR Entrance.png"));
            tile[102].collision = true;

            tile[103] = new Tile();
            tile[103].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CR Entrance down.png"));
            tile[103].collision = false;

            tile[104] = new Tile();
            tile[104].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CR Entrance.png"));
            tile[104].collision = false;

            tile[105] = new Tile();
            tile[105].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CR CUBICLE/CR CUBICLE_0_0.png"));
            tile[105].collision = true;

            tile[106] = new Tile();
            tile[106].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CR CUBICLE/CR CUBICLE_0_16.png"));
            tile[106].collision = true;

            tile[107] = new Tile();
            tile[107].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ELECTRO DOOR/ELECTRO DOOR_0_0.png"));
            tile[107].collision = false;

            tile[108] = new Tile();
            tile[108].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ELECTRO DOOR/ELECTRO DOOR_0_16.png"));
            tile[108].collision = false;

            tile[109] = new Tile();
            tile[109].image = ImageIO.read(getClass().getResourceAsStream("/tiles/MAZE BLOCK.png"));
            tile[109].collision = true;
// Continue this pattern until you've added all needed chunks...




//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/modified_grid.txt");

            BufferedReader bufferedReader = null;
            if(mapNumber == 0){
                 inputStream = getClass().getResourceAsStream("/maps/modified_grid.txt");
                 bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } else if (mapNumber == 1){
                inputStream = getClass().getResourceAsStream("/maps/maps2.txt");
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } else if (mapNumber == 2){
                inputStream = getClass().getResourceAsStream("/maps/map2.txt");
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            }
            System.out.println(mapNumber);


            int column = 0;
            int row = 0;

            while(column < gamePanel.maxWorldRow && row < gamePanel.maxWorldColumn){
                String line = bufferedReader.readLine();

                while (column < gamePanel.maxWorldColumn){
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);

                    mapTileNumber[column][row] = number;
                    column++;
                }

                if(column == gamePanel.maxWorldColumn){
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){
//        graphics2D.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
//        graphics2D.drawImage(tile[1].image, 48, 0, gamePanel.tileSize, gamePanel.tileSize, null);
//

//        int column = 0;
//        int row = 0;
//        int x = 0;
//        int y = 0;
//
//
//        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){
//
//            int tileNumber = mapTileNumber[column][row];
//            System.out.println(column);
//            graphics2D.drawImage(tile[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
//            column++;
//            x += gamePanel.tileSize;
//
//            if(column == gamePanel.maxScreenColumn){
//                column = 0;
//                x = 0;
//                row++;
//                y += gamePanel.tileSize;
//
//            }
//        }

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow){

            int worldX = worldColumn * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            int tileNumber = mapTileNumber[worldColumn][worldRow];
            worldColumn++;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY )
                graphics2D.drawImage(tile[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);


            if(worldColumn == gamePanel.maxWorldColumn){
                worldColumn = 0;

                worldRow++;
            }


        }

    }
    public void changeMap(int mapNumber){
        this.mapNumber = mapNumber;

        this.loadMap();

    }
}

