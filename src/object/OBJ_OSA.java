package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_OSA extends SuperObject{


    public OBJ_OSA(){
        name = "OSA";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/OSA.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
