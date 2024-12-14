package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Null extends SuperObject{
    public OBJ_Null(){
        super();
        name = "Null";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/clear.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
