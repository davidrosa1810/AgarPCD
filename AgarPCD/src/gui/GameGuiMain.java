package gui;

import java.util.Observable;
import java.util.Observer;
import game.Game;
import game.PhoneyHumanPlayer;
import game.Player;

import javax.swing.JFrame;

import environment.Direction;

public class GameGuiMain implements Observer {
	private JFrame frame = new JFrame("pcd.io");
	private BoardJComponent boardGui;
	private Game game;

	public GameGuiMain() {
		super();
		game = new Game();
		game.addObserver(this);

		buildGui();

	}

	private void buildGui() {
		boardGui = new BoardJComponent(game);
		frame.add(boardGui);


		frame.setSize(800,800);
		frame.setLocation(0, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init()  {
		frame.setVisible(true);

		// Demo players, should be deleted
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int id = 0; id < Game.NUM_PLAYERS; id++) {
		    game.addPlayerToGame(new PhoneyHumanPlayer(id, game, (byte)Player.generateInitialEnergy()));
		}
//		game.addPlayerToGame(new PhoneyHumanPlayer(1, game, (byte)3));
//		game.addPlayerToGame(new PhoneyHumanPlayer(2, game, (byte)2));
//		game.addPlayerToGame(new PhoneyHumanPlayer(3, game, (byte)1));
	}

	@Override
	public void update(Observable o, Object arg) {
		boardGui.repaint();
	}

	public static void main(String[] args) {
		for(int i = 0; i < 100;i++ )
			System.out.println(Direction.values()[(int)(Math.random()*Direction.values().length)]);
		GameGuiMain game = new GameGuiMain();
		game.init();
	}

}
