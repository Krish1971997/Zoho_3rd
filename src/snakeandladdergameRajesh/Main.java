package snakeandladdergameRajesh;

public class Main {
	
	public static void main(String[] args) {
		
		Game game = Game.getInstance();
		game.createBoard(10);
		game.addPlayers(4);
		
		game.startGame();
	}

}
