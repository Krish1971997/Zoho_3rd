package cabBooking;

public class Driver extends Person {
	public Driver(int id, String name, String password, int age, String gender) {
		super(id, name, password, age, gender);
	}

	public int getId() {
		return super.getId();
	}

	public String getName() {
		return super.getName();
	}

	public String getPassword() {
		return super.getPassword();
	}

	public int getAge() {
		return super.getAge();
	}

	public String getGender() {
		return super.getGender();
	}
}
