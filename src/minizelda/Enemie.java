package minizelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemie extends Rectangle {

    public int spd;
    public int right = 1, up = 0, down = 0, left = 0;

    public int currentAnimation = 0;
    public int currentFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets;


    // Usamos a classe Rectangle porque ela já possui a mecânica da hitbox
    public Enemie(int x, int y) {
        super(x, y, 32, 32);
        spd = 2;
        bullets =  new ArrayList<>();
    }

    public void tick() {
        chasePlayer();

        //Sistema de animação
        currentFrames++;
        if(currentFrames == targetFrames){
            currentFrames = 0;
            currentAnimation++;
            if(currentAnimation == SpriteSheet.player_front.length)
                currentAnimation = 0;
        }
        shooted();
    }

    public void render(Graphics g) {
        g.drawImage(SpriteSheet.enemie_front[currentAnimation], x, y, 32, 32, null);

    }

    public void shooted(){
        for(int i = 0; i<Player.bullets.size(); i++){
            if(this.intersects(Player.bullets.get(i))){
                World.enemies.remove(this);
                World.resetEnemie();
            }
        }
    }

    public void chasePlayer(){
        //Lógica de perseguir o player
        Player p = Game.player;
        //Pode ser adicionado um Random para randomizar a probabilidade de o player se mexer
        if(this.x < p.x && World.isFree(x+spd, y))
            x+=spd;
        else if(this.x > p.x && World.isFree(x-spd, y))
            x-=spd;

        if(this.y < p.y && World.isFree(x, y+spd))
            y+=spd;
        else if(this.y > p.y && World.isFree(x, y-spd))
            y-=spd;
    }
}