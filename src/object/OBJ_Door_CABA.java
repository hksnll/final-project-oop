package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_CABA extends SuperObject{
    int door = 0;
    public OBJ_Door_CABA(int door){
        name = null;
        door = door;
        try {
            if(door == 0) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/CEITdoorExit.png"));
            } if (door == 1){
                image = ImageIO.read(getClass().getResourceAsStream("/objects/CEITdoor.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
