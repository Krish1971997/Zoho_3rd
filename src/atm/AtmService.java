package atm;

import java.util.Map;
import java.util.Scanner;

public class AtmService {
    private CashManager cashManager;
    private UserManager userManager;
    private User currentUser;

    public AtmService() {
        this.cashManager = new CashManager(100000);
        this.userManager = new UserManager();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        userManager.printUserTable();

        System.out.println("\nEnter Account Number: ");
        String accountNo = scanner.nextLine();
        System.out.println("Enter PIN: ");
        String pin = scanner.nextLine();

        currentUser = userManager.validateUser(accountNo, pin);
        if (currentUser == null) {
            System.out.println("Invalid account number or PIN.");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Amount");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter receiver account number: ");
                    String receiverAccNo = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    transfer(receiverAccNo, transferAmount);
                    break;
                case 4:
                    showMiniStatement();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void checkBalance() {
        currentUser.addTransaction("Balance checked: " + currentUser.getBalance());
        System.out.println("Current Balance: " + currentUser.getBalance());
    }

    private void withdraw(double amount) {
        if (amount > 10000) {
            System.out.println("Withdrawal limit is 10000 per transaction.");
            return;
        }
        if (amount > currentUser.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }
        if (!cashManager.canDispense((int) amount)) {
            System.out.println("ATM cannot dispense this amount.");
            return;
        }

        cashManager.dispenseCash((int) amount);
        currentUser.setBalance(currentUser.getBalance() - amount);
        Map<Integer, Integer> denominations = cashManager.calculateDenominations((int) amount);
        currentUser.addTransaction("Withdrawn: " + amount + ", Denominations: " + denominations);

        System.out.println("Amount withdrawn successfully. Denominations:");
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            System.out.println(entry.getKey() + " x " + entry.getValue());
        }
    }

    private void transfer(String receiverAccNo, double amount) {
        User receiver = userManager.findUser(receiverAccNo);
        if (receiver == null) {
            System.out.println("Receiver account does not exist.");
            return;
        }
        if (amount > currentUser.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        currentUser.setBalance(currentUser.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        currentUser.addTransaction("Transferred: " + amount + " to " + receiverAccNo);
        System.out.println("Amount transferred successfully.");
    }

    private void showMiniStatement() {
        System.out.println("\nMini Statement:");
        for (String transaction : currentUser.getTransactions()) {
            System.out.println(transaction);
        }
    }
}
