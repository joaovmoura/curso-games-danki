package minizelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.Serial;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

    @Serial
    private static final long serialVersionUID = 1L;
    public Player player;
    public World world;

    public Game() {
        this.addKeyListener(this); // Adicionamos eventos de teclado
        this.setPreferredSize(new Dimension(World.WIDTH, World.HEIGHT));
        new SpriteSheet();
        this.player = new Player(32,32);
        this.world = new World();
    }

    public void tick() {
        // lógica do game
        player.tick();
    }

    public void render() {
        // renderizar o jogo
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(0, 135, 13));
        g.fillRect(0, 0, World.WIDTH, World.HEIGHT);
        player.render(g);
        world.render(g);
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini zelda");

        frame.pack(); // Empacotar o que foi feito antes e calcular o tamanho da janela

        frame.setLocationRelativeTo(null); // Janela centralizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando fechar a janela, o processo será encerrado

        frame.setVisible(true);

        new Thread(game).start(); // O start "procura" pelo método run
    }

    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.right = true;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            player.left = true;

        if(e.getKeyCode() == KeyEvent.VK_UP)
            player.up = true;
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            player.down = true;

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            player.shoot = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.right = false;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            player.left = false;

        if(e.getKeyCode() == KeyEvent.VK_UP)
            player.up = false;
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            player.down = false;

    }

}