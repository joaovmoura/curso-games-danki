package minizelda;

import java.awt.*;

public class Bullet extends Rectangle {

    public int direction = 1;
    public int spd = 8;
    public int frames = 0;
    public Bullet(int x, int y, int direction){
        super(x, y, 20, 20);
        this.direction = direction;

    }

    public void tick(){
        x+=spd*direction;
        frames++;
        if(frames==60){
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics g){
        g.drawImage(SpriteSheet.bullet, x, y, 16, 16, null);
    }
}
