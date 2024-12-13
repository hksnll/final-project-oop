package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Electro extends SuperObject{

    public OBJ_Electro(){
        name = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ELECTRO DOOR/ELECTRO DOOR_0_16.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
