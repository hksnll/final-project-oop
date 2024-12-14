package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_COR_End extends SuperObject{


    public OBJ_COR_End(){
        name = "Computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/COR.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
