package carRentalSystem;

public class Spot {
	private String floorNum;
	private String row;
	private String col;

	public Spot(String floorNum, String row, String col) {
		this.floorNum = floorNum;
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Spot [floorNum=" + floorNum + ", row=" + row + ", col=" + col + "]";
	}
	
	

}
