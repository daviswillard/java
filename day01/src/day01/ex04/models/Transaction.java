package day01.ex04.models;

import java.util.UUID;

enum TransCat {
    INCOME,
    OUTCOME
}

public class Transaction {
    private final UUID id;
    private User personA;
    private User personB;
    private TransCat category;
    private Integer amount;

    private TransCat    checkAmount(int amount) {
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

    public void swapClients() {
        User temp = personA;
        personA = personB;
        personB = temp;
    }

    public void swapCategory() {
        if (category == TransCat.INCOME) {
            category = TransCat.OUTCOME;
        } else {
            category = TransCat.INCOME;
        }
        amount *= -1;
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
        return "Transaction id: " + getId() + "\nAmount: " + getAmount() + "\nCategory: " + getCategory() + '\n';
    }
}
