package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Obstacle> obstacles = new ArrayList<>();

    public World(){
        for(int j = 0; j<15; j++){
            obstacles.add(new Obstacle(j*32, 0));
        }
        for(int j = 0; j<15; j++){
            obstacles.add(new Obstacle(j*32, 480-32));
        }
        for(int j = 0; j<15; j++){
            obstacles.add(new Obstacle(0, j*32));
        }
        for(int j = 0; j<15; j++){
            obstacles.add(new Obstacle(480-32, j*32));
        }
    }
    public void render(Graphics g){
        for(int i=0; i < obstacles.size(); i++){
            obstacles.get(i).render(g);
        }
    }

    public static boolean isFree(int x, int y){
        for(int i = 0; i< obstacles.size(); i++){
            Obstacle current = obstacles.get(i);
            if(current.intersects(new Rectangle(x, y, 32, 32)))
                return false;

        }
        return true;
    }
}
