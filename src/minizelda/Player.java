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

    public static List<Bullet> bullets;
    public boolean shoot = false;

    public int dir = 1;

    // Usamos a classe Rectangle porque ela já possui a mecânica da hitbox
    public Player(int x, int y) {
        super(x, y, 32, 32);
        spd = 4;
        bullets =  new ArrayList<>();
    }

    public void tick() {
        if(right && World.isFree(x+spd, y)){
            x+=spd;
            dir = 1;
        }
        else if(left && World.isFree(x-spd, y)){
            x-=spd;
            dir = -1;
        }
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
            bullets.add(new Bullet(x, y, dir));
        }

        for (int i = 0; i< bullets.size(); i++) {
            bullets.get(i).tick();
        }
    }

    public void render(Graphics g) {
        g.drawImage(SpriteSheet.player_front[currentAnimation], x, y, 32, 32, null);
        for (int i = 0; i< bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }

    public boolean isMoving(){
        return up || down || left || right;
    }
}