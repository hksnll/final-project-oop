package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_CEIT_Exit extends SuperObject{

    public OBJ_Door_CEIT_Exit(){
        name = "CEIT Door Exit";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/CEITdoorExit.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
