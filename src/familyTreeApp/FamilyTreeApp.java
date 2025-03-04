package familyTreeApp;

import java.util.*;

class Person {
	String name;
	String gender;
	Person father;
	Person mother;

	public Person(String name, String gender, Person father, Person mother) {
		this.name = name;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
	}
}

class FamilyTree {
	private Map<String, Person> people = new HashMap<>();

	public void addPerson(String name, String gender, String fatherName, String motherName) {
		Person father = people.get(fatherName);
		Person mother = people.get(motherName);
		Person person = new Person(name, gender, father, mother);
		people.put(name, person);
	}

	public List<String> findEligibleMatches(String personName) {
		List<String> matches = new ArrayList<>();
		Person person = people.get(personName);
		if (person == null)
			return matches;

		if ("Male".equals(person.gender)) {
			findMatches(person, "Female", matches);
		} else {
			findMatches(person, "Male", matches);
		}

		return matches;
	}

	private void findMatches(Person person, String matchGender, List<String> matches) {
		if (person.father != null && person.father.mother != null) {
			findChildren(person.father.mother, matchGender, matches); // Father's sisters' daughters
		}
		if (person.mother != null && person.mother.father != null) {
			findChildren(person.mother.father, matchGender, matches); // Mother's brothers' daughters
		}
	}

	private void findChildren(Person ancestor, String gender, List<String> matches) {
		for (Person child : people.values()) {
			if (child.father == ancestor || child.mother == ancestor) {
				if (gender.equals(child.gender)) {
					matches.add(child.name);
				}
			}
		}
	}
}

public class FamilyTreeApp {
	public static void main(String[] args) {
		FamilyTree familyTree = new FamilyTree();

		familyTree.addPerson("John", "Male", "Brad", "Lisa");
		familyTree.addPerson("Emma", "Female", "Brad", "Lisa");
		familyTree.addPerson("Alex", "Male", "John", "Jenny");
		familyTree.addPerson("Emily", "Female", "Steve", "Emma");
		familyTree.addPerson("Rachel", "Female", "Steve", "Emma");

		String personName = "Alex";
		List<String> eligibleMatches = familyTree.findEligibleMatches(personName);

		System.out.println("Eligible matches for " + personName + ": " + eligibleMatches);
	}
}
