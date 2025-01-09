package trainticketBookingRajesh;

public class Person {
	private String name;
	private String id;
	private Role role;

	public Person(String name, String id, Role role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}
