package snakeandladdergameRajesh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Board {
	private int size;
	private int index;
	private Map<Integer, BoardPropery> boardProperty = new HashMap<>();
	private Set<Integer> usedPoint = new HashSet<>();

	public void createBoard(int size) {
		this.size = size;
		index = size * size;
		addLadder();
		addSnake();

		for (Entry<Integer, BoardPropery> prop : boardProperty.entrySet()) {
			System.out.println(prop);

		}

	}

	public void addLadder() {

		for (int i = 0; i < size;) {

			Random random = new Random();

			int end = random.nextInt(index - 1) + 1;
			int start = random.nextInt(end);

			if (end != start && !usedPoint.contains(end) && !usedPoint.contains(start)) {
				boardProperty.put(start, new Ladder(start, end));
				i++;
				usedPoint.add(start);
				usedPoint.add(end);
			}

		}

	}

	public void addSnake() {

		for (int i = 0; i < size;) {

			Random random = new Random();

			int start = random.nextInt(index - 1) + 1;
			int end = random.nextInt(start) - size;

			if (end != start && !usedPoint.contains(end) && !usedPoint.contains(start)) {
				boardProperty.put(start, new Snake(start, end));
				i++;
				usedPoint.add(start);
				usedPoint.add(end);
			}

		}

	}

	public int getIndex() {
		return index;
	}

	public Map<Integer, BoardPropery> getBoardProperty() {
		return boardProperty;
	}

	public void setBoardProperty(Map<Integer, BoardPropery> boardProperty) {
		this.boardProperty = boardProperty;
	}

}
