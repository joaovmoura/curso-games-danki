package minizelda;

import java.awt.*;

public class Bullet extends Rectangle {

    public int direction = 1;
    public int spd = 8;
    public Bullet(int x, int y, int direction){
        super(x, y, 20, 20);
        this.direction = direction;

    }

    public void tick(){
        x+=spd*direction;
    }

    public void render(Graphics g){
        g.drawImage(SpriteSheet.bullet, x, y, 16, 16, null);
    }
}
