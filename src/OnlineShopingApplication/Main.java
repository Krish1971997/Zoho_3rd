package OnlineShopingApplication;

import java.util.*;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner sc = new Scanner(System.in);
		UserUtility userutility = new UserUtility();

		userutility.userdeatils.put("admin", new User("admin", "admin@gamil.com", "90444232", "admin", "admin"));
		while (true) {
			System.out.println(" 1 - Register \n 2 - Login \n 3 - Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				userutility.register();
				break;
			case 2:
				userutility.login();
				break;
			case 3:
				System.exit(0);
				sc.close();
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}
		}

	}
}
