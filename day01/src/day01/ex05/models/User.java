package day01.ex05.models;

import day01.ex05.UserIdsGenerator;
import day01.ex05.transactionlist.TransactionLinkedList;

public class User {
    private final String  name;
    private final Integer id;

    private final TransactionLinkedList transactions = new TransactionLinkedList();
    private Integer balance;

    public User(String name, int balance) {
        this.balance = balance;
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generateId();
        checkBalance();
    }

    private void	checkBalance() {
        if (balance < 0) {
            System.err.println("User " + name + " has negative balance"
                    + " (id: " + id + ')');
            System.err.println("Defaulting balance to zero...");
            balance = 0;
        }
    }

    public TransactionLinkedList getTransactions() {
        return transactions;
    }

    public int  getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public String   getName() {
        return name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User: " + getName() +
                "\nID: " + getId() + "\nBalance: " +
                getBalance() + '\n';
    }
}
