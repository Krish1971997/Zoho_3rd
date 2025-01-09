package snakeandladdergameRajesh;

import java.util.Random;

public class Dice {
	static private Dice instance = null;

	static public Dice getInstance() {
		if (instance == null) {
			instance = new Dice();
		}
		return instance;
	}

	public int rollDice() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}

}
