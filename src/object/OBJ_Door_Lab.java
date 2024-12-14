package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_Lab extends SuperObject{

    public OBJ_Door_Lab(int door){
        name = null;

        try {

                image = ImageIO.read(getClass().getResourceAsStream("/objects/CABA Entrance_0_16.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
