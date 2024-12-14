package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_COR extends SuperObject {
    int num = 1;
    public OBJ_COR(){
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
