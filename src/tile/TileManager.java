package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNumber[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[30];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GRASS2.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brickk.png"));

//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/maps_01.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while(column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenColumn){
                String line = bufferedReader.readLine();

                while (column < gamePanel.maxScreenColumn){
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);

                    mapTileNumber[column][row] = number;
                    column++;
                }

                if(column == gamePanel.maxScreenColumn){
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

            if (
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                graphics2D.drawImage(tile[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }


            if(worldColumn == gamePanel.maxWorldColumn){
                worldColumn = 0;
                System.out.println(worldColumn);
                worldRow++;
            }

        }
    }
}

