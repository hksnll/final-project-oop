package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CR extends SuperObject{

    public OBJ_CR(){
        name = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/CR Entrance.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
