package carRentalSystem;

public class Person {
	private Address addr;
	private String id;
	private String name;
	private PersonType type;

	public Person(Address addr, String id, String name, PersonType type) {
		this.addr = addr;
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [addr=" + addr + ", id=" + id + ", name=" + name + ", type=" + type + "]";
	}

}
