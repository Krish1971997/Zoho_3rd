package snakeandladdergameRajesh;

public class BoardPropery {

	private int start;
	private int end;
	private BoardPropertyType type;

	public BoardPropery(int start, int end, BoardPropertyType type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}

	@Override
	public String toString() {
		return "BoardPropery [start=" + start + ", end=" + end + ", type=" + type + "]";
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public BoardPropertyType getType() {
		return type;
	}

	public void setType(BoardPropertyType type) {
		this.type = type;
	}
}
