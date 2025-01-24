package snakeandladdergameRajesh;

public class Player {
	private int id;
	private int position;
	private boolean isReachedLastPosition;

	public Player(int id) {
		this.id = id;
		this.position = 0;
		this.isReachedLastPosition = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isReachedLastPosition() {
		return isReachedLastPosition;
	}

	public void setReachedLastPosition(boolean isReachedLastPosition) {
		this.isReachedLastPosition = isReachedLastPosition;
	}
}
