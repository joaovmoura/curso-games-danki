package minizelda;

import java.awt.*;

public class Obstacle extends Rectangle {

    public Obstacle(int x, int y) {
        super(x, y, 32,32);
    }

    public void render(Graphics g){
        //Tive que improvisar os blocos porque não conseguia importar do spriteSheet
        g.setColor(new Color(52, 54, 56));
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.drawOval(10+x, 15+y, 1, 1);
        g.drawOval(15+x, 22+y, 1, 1);
        g.drawOval(7+x, 3+y, 1, 1);
        g.drawOval(2+x, 25+y, 1, 1);
        g.drawOval(22+x, 5+y, 1, 1);
        g.drawRect(x, y, width, height);
    }
}
