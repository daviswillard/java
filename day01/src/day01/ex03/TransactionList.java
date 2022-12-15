package day01.ex03;

import java.util.UUID;

public interface TransactionList {
    void            addTransaction(Transaction transfer);
    void            removeTransaction(UUID id);
    Transaction[]   getArray();
}
