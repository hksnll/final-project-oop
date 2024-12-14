package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_COR_Down extends SuperObject {
    int num = 2;
    public OBJ_COR_Down(){
        name = null;
        try {
            if (num == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/COR_UP.png"));
            } else if (num == 2) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/cordown.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
    public void setNum(int num){
        this.num = num;
    }
}
