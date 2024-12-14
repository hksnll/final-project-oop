package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Computer extends SuperObject{


    public OBJ_Computer(){
        name = "Computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Normal PC.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
