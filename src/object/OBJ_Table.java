package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Table extends SuperObject{


    public OBJ_Table(){
        name = "Table";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/PC TABLE_(0).png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
