package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Trash extends SuperObject{

    public OBJ_Trash(){
        name = "Trash";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/TRASH CAN.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
