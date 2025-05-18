package atm;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
	private List<User> users;

	public UserManager() {
		this.users = new ArrayList<>();
		loadUsers();
	}

	private void loadUsers() {
		// Sample users
		users.add(new User("1001", "John Doe", "1234", 50000));
		users.add(new User("1002", "Jane Smith", "5678", 75000));
	}

	public User validateUser(String accountNo, String pin) {
		for (User user : users) {
			if (user.getAccountNo().equals(accountNo) && user.getPin().equals(pin)) {
				return user;
			}
		}
		return null;
	}

	public User findUser(String accountNo) {
		for (User user : users) {
			if (user.getAccountNo().equals(accountNo)) {
				return user;
			}
		}
		return null;
	}

	public void printUserTable() {
		System.out.println("+------------+------------------+-------+----------+");
		System.out.println("| Account No | Account Holder    | PIN   | Balance  |");
		System.out.println("+------------+------------------+-------+----------+");
		for (User user : users) {
			System.out.printf("| %-10s | %-16s | %-5s | %-8.2f |\n", user.getAccountNo(), user.getAccountHolderName(),
					user.getPin(), user.getBalance());
		}
		System.out.println("+------------+------------------+-------+----------+");
	}
}