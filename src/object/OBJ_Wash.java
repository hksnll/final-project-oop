package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Wash extends SuperObject{

    public OBJ_Wash(){
        name = "CR Wash";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/CR WASH.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
