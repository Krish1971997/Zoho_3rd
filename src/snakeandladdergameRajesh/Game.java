package snakeandladdergameRajesh;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Board board = new Board();
	private List<Player> players = new ArrayList<>();

	private static Game instance = null;

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();

		}
		return instance;
	}

	public void createBoard(int size) {
		board.createBoard(size);
	}

	public void addPlayers(int count) {

		for (int i = 1; i <= count; i++) {
			players.add(new Player(i));
		}

	}

	public void startGame() {
		
		
		System.out.println("---------- play started -----------");

		while (!isOnePlayerRemaining()) {

			for (int i = 0; i < players.size(); i++) {

				if (players.get(i).isReachedLastPosition()) {
					continue;
				}

				Dice dice = Dice.getInstance();
				int val = dice.rollDice();
				System.out.println("Player Id : "+ players.get(i).getId()+" dice val "+val);
				movePosition(players.get(i), val);

			}

		}

	}

	public void movePosition(Player player, int val) {

		int currentPosition = player.getPosition();
		int nextPosition = currentPosition + val;

		if (nextPosition > board.getIndex()) {

			System.out.println("Player  " + player.getId() + " out of the range");
			return;

		}

		BoardPropery propery = board.getBoardProperty().get(nextPosition);
		if (propery == null) {
			System.out.println("Player  " + player.getId() + " moved to position " + nextPosition);
			;
			player.setPosition(nextPosition);
			if(nextPosition == board.getIndex()) {
				player.setReachedLastPosition(true); 
			}

		} else if (propery.getType() == BoardPropertyType.SNAKE) {
			System.out.println("Current is at snake head");
			System.out.println("Player  " + player.getId() + " moved to position " + propery.getEnd());
			;
			player.setPosition(propery.getEnd());
		}
		else if(propery.getType() == BoardPropertyType.LADDER) {
			System.out.println("Current is at ladder start");
			System.out.println("Player  " + player.getId() + " moved to position " + propery.getEnd());
			;
			player.setPosition(propery.getEnd());
			
		}

	}

	public boolean isOnePlayerRemaining() {

		int remainingPlayerCount = 0;

		for (Player player : players) {
			if (!player.isReachedLastPosition()) {
				remainingPlayerCount++;
			}
		}

		return remainingPlayerCount == 1;

	}

}
