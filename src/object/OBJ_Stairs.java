package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Stairs extends SuperObject {
    public int stairNum;
    public OBJ_Stairs (int stairNum){
        this.stairNum = stairNum;
        name = null;

        try {
            if (stairNum == 1){
                    image = ImageIO.read(getClass().getResourceAsStream("/objects/stairs/stairs_0_16.png"));

            } else if (stairNum == 2){
                image = ImageIO.read(getClass().getResourceAsStream("/objects/stairs/stairs_16_16.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

