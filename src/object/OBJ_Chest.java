package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest (){
        super();
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/CHEST.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
