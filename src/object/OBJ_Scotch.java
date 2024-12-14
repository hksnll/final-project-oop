package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Scotch extends SuperObject{

    public OBJ_Scotch(){
        name = "Scotch";
        try {

                image = ImageIO.read(getClass().getResourceAsStream("/objects/Scotch Tape.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = false;



    }
    }

