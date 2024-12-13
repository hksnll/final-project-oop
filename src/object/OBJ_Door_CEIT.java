package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_CEIT extends SuperObject{

    public OBJ_Door_CEIT(){
        name = "CEIT Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/CEITdoor.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
