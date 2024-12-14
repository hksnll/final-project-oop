package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Temba extends SuperObject{


    public OBJ_Temba(){
        name = "Temba";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/TEBMA.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
