package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Alarm extends SuperObject{
    public OBJ_Alarm(){
        super();
        name = "Alarm";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/FIRE ALARM.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
