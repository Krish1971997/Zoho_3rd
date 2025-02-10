package taxibooking_nw;

import java.util.ArrayList;

//not working
class taxi extends Thread {
	int id;// taxi number
	char currentPoint = 'A';
	double totalEarn = 0.0;
	ArrayList<Integer> bookingId = new ArrayList<Integer>();
	ArrayList<Character> custId = new ArrayList<Character>();
	ArrayList<Character> from = new ArrayList<Character>();
	ArrayList<Character> to = new ArrayList<Character>();
	ArrayList<Integer> pickUpTime = new ArrayList<Integer>();
	ArrayList<Integer> dropTime = new ArrayList<Integer>();
	ArrayList<Double> amount = new ArrayList<Double>();
	boolean isFree = true;

	taxi(int id) {
		this.id = id;
	}

	void assign(int bookid, char cid, char pp, char dp, int pt) {
		bookingId.add(bookid);
		custId.add(cid);
		from.add(pp);
		to.add(dp);
		pickUpTime.add(pt);
		dropTime.add(pt + Math.abs(pp - dp));
		amount.add((double) 100 + ((15 * Math.abs(pp - dp)) - 5) * 10);// get distance
		totalEarn += (double) 100 + ((15 * Math.abs(pp - dp)) - 5) * 10;
		System.out.println("ampunt to pay : " + (100 + ((15 * Math.abs(pp - dp)) - 5) * 10));
		this.currentPoint = dp;// upate current point with drop point

	}
}