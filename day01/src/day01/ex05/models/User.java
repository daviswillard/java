package day01.ex05.models;

import day01.ex05.UserIdsGenerator;
import day01.ex05.transactionlist.TransactionLinkedList;

public class User {
    private final String  name;
    private final Integer id;

    private final TransactionLinkedList transactions = new TransactionLinkedList();
    private Integer balance;

    public User(String name, int balance) {
        if (balance < 0) {
            throw new NegativeBalanceException();
        }
        this.balance = balance;
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generateId();
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
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "User: " + getName() +
                "\nID: " + getId() + "\nBalance: " +
                getBalance() + '\n';
    }
}
