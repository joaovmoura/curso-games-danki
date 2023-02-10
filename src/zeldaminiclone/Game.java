package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static int WIDTH = 480, HEIGTH = 480;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void tick() {
		// lógica do game
	}
	
	public void render() {
		// renderizar o jogo
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGTH);
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, 50, 50);;
		
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

}
