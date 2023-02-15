package minizelda;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    public static BufferedImage player_front[];
    public static BufferedImage enemie_front[];
    public static BufferedImage bullet;
    public static BufferedImage spriteSheet;

    public SpriteSheet() {
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/spriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player_front = new BufferedImage[2];
        player_front[0] = SpriteSheet.getSprite(0, 11, 16, 16);
        player_front[1] = SpriteSheet.getSprite(16, 11, 16, 16);

        bullet = SpriteSheet.getSprite(52, 185, 10, 16);

        enemie_front = new BufferedImage[2];

        enemie_front[0] = SpriteSheet.getSprite(237, 283, 16, 16);
        enemie_front[1] = SpriteSheet.getSprite(255, 283, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height){
        return spriteSheet.getSubimage(x, y, width, height);
    }


}
