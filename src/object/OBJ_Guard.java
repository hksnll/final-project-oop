package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Guard extends SuperObject{

    public OBJ_Guard(){
        name = "Guard";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/GUARD.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
