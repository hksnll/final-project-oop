package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{

    public boolean isLife = true;
    public OBJ_Heart (){
        name = "Heart";
        try {
            if (isLife){
                image = ImageIO.read(getClass().getResourceAsStream("/objects/HEART.png"));}
            else if (isLife == false) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/HEART - GRAY.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;


    }
    public void setLife(boolean life){
        this.isLife = isLife;
        if (!isLife) {
            try {
                this.image = ImageIO.read(getClass().getResourceAsStream("/objects/HEART.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        // set to empty heart or placeholder image;
        } else {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/HEART - GRAY.png"));// set to full heart image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
