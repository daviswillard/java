package ex03.src;

import java.util.UUID;

public interface TransactionList {
    void            addTransaction(Transaction transfer);
    void            removeTransaction(UUID id);
    Transaction[]   getArray();
}
