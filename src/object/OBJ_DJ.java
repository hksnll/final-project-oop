package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DJ extends SuperObject{

    public boolean isAlarmed = false;
    public OBJ_DJ(){
        name = "DJ";
        try {
            if(isAlarmed == false){
                image = ImageIO.read(getClass().getResourceAsStream("/objects/DJ.png"));
            } else {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/DJ Alarmed.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;
    }
}
