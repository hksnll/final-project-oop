package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DJ extends SuperObject{


    public OBJ_DJ(){
        name = "DJ";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/DJ.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
