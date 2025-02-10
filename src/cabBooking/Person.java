package cabBooking;

public abstract class Person {
    private int id;
    private String name;
    private String password;
    private int age;
    private String gender;

    public Person(int id, String name, String password, int age, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
