package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Obstacle> obstacles = new ArrayList<>();
    public static int WIDTH = 1280, HEIGHT = 960;
    public static int SCALE = 3;
    public static List<Enemie> enemies = new ArrayList<>();

    public World(){
        for(int j = 0; j<(WIDTH/32); j++){
            obstacles.add(new Obstacle(j*32, 0));
        }
        for(int j = 0; j<(WIDTH/32); j++){
            obstacles.add(new Obstacle(j*32, HEIGHT-32));
        }
        for(int j = 0; j<(HEIGHT/32); j++){
            obstacles.add(new Obstacle(0, j*32));
        }
        for(int j = 0; j<(HEIGHT/32); j++){
            obstacles.add(new Obstacle(WIDTH-32, j*32));
        }

        enemies.add(new Enemie(60, 60));
    }

    public void render(Graphics g){
        for(int i=0; i < obstacles.size(); i++){
            obstacles.get(i).render(g);
        }

        for(int i = 0; i < enemies.size(); i++)
            enemies.get(i).render(g);
    }

    public static boolean isFree(int x, int y){
        for(int i = 0; i< obstacles.size(); i++){
            Obstacle current = obstacles.get(i);
            if(current.intersects(new Rectangle(x, y, 32, 32)))
                return false;

        }
        return true;
    }

    public void tick() {
        for(int i = 0; i < enemies.size(); i++)
            enemies.get(i).tick();
    }
}
