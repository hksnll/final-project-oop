package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Student extends SuperObject{
    public int num;
    public OBJ_Student(int num){
        this.num = num;
        name = null;
        try {
            if (this.num == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/std1.png"));
            } else if (this.num == 2) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/std2.png"));
            } else if (this.num == 3) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/std3.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }

}
