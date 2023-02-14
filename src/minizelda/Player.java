package minizelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

    public int spd;
    public boolean right, up, down, left;

    public int currentAnimation = 0;

    public int currentFrames = 0, targetFrames = 15;

    public List<Bullet> bullets;
    public boolean shoot = false;

    // Usamos a classe Rectangle porque ela já possui a mecânica da hitbox
    public Player(int x, int y) {
        super(x, y, 32, 32);
        spd = 4;
        bullets =  new ArrayList<>();
    }

    public void tick() {
        if(right && World.isFree(x+spd, y))
            x+=spd;
        else if(left && World.isFree(x-spd, y))
            x-=spd;
        if(down && World.isFree(x, y+spd))
            y+=spd;
        else if(up && World.isFree(x, y-spd))
            y-=spd;

       //Sistema de animação
        currentFrames++;
        if(currentFrames == targetFrames){
            currentFrames = 0;
            if(isMoving())
                currentAnimation++;
            if(currentAnimation == SpriteSheet.player_front.length)
                currentAnimation = 0;
        }

        //Sistema de tiro
        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x, y, 1));
        }

        for (Bullet b: bullets) {
            b.tick();
        }
    }

    public void render(Graphics g) {
        g.drawImage(SpriteSheet.player_front[currentAnimation], x, y, 32, 32, null);
        for (Bullet b: bullets) {
            b.render(g);
        }
    }

    public boolean isMoving(){
        return up || down || left || right;
    }
}