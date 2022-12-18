package day01.ex05.models;

import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final User personA;
    private final User personB;

    public User getPersonA() {
        return personA;
    }

    public User getPersonB() {
        return personB;
    }

    private TransCat category;
    private final Integer amount;

    private TransCat checkAmount(int amount) {
        if (amount > 0) {
            return TransCat.INCOME;
        }
        return TransCat.OUTCOME;
    }

    public Transaction(User clientA, User clientB, int amount) {
        personA = clientA;
        personB = clientB;
        id = UUID.randomUUID();
        this.amount = amount;
        category = checkAmount(amount);
    }

    public Transaction(Transaction twin) {
        personA = twin.personB;
        personB = twin.personA;
        id = twin.getId();
        amount = twin.getAmount() * -1;
        category = twin.getCategory();
        if (twin.getCategory() == TransCat.INCOME) {
            category = TransCat.OUTCOME;
        } else {
            category = TransCat.INCOME;
        }
    }

    public TransCat getCategory() {
        return category;
    }

    public UUID getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction id: " + getId() + "\nAmount: " +
                getAmount() + "\nCategory: " + getCategory() +
                "\nClient A: " + personA +
                "\nClient B: " + personB + '\n';
    }

    public static void main(String[] args) {
        User userA = new User("Iskander", 500);
        User userB = new User("Kamil", 200);

        Transaction transaction = new Transaction(userA, userB, 200);
        userA.getTransactions().addTransaction(transaction);
        Transaction transCopy = new Transaction(transaction);
        userB.getTransactions().addTransaction(transCopy);
        System.out.println(userB.getTransactions().toArray()[0]);
        System.out.println(userA.getTransactions().toArray()[0]);
    }
}
