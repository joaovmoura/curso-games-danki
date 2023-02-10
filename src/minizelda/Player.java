package minizelda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

    public int spd;
    public boolean right, up, down, left;

    // Usamos a classe Rectangle porque ela já possui a mecânica da hitbox
    public Player(int x, int y) {
        super(x, y, 32, 32);
        spd = 4;
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

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

}