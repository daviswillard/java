package day01.ex03;

import java.util.UUID;

enum TransCat {
    INCOME,
    OUTCOME
}

public class Transaction {
    private UUID        id;
    private User        personA;
    private User        personB;
    private TransCat    category;
    private Integer     amount;

    private TransCat    checkAmount(int amount) {
        if (amount > 0) {
            return TransCat.INCOME;
        }
        return TransCat.OUTCOME;
    }

    Transaction(User clientA, User clientB, int amount) {
        personA = clientA;
        personB = clientB;
        id = UUID.randomUUID();
        this.amount = amount;
        category = checkAmount(amount);

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

}
