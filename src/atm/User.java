package atm;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String accountNo;
    private String accountHolderName;
    private String pin;
    private double balance;
    private List<String> transactions;

    public User(String accountNo, String accountHolderName, String pin, double balance) {
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNo() { return accountNo; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }
    public String getAccountHolderName() { return accountHolderName; }
    public List<String> getTransactions() { return transactions; }

    public void setBalance(double balance) { this.balance = balance; }
    public void addTransaction(String transaction) {
        transactions.add(transaction);
        if (transactions.size() > 5) {
            transactions.remove(0);
        }
    }
}