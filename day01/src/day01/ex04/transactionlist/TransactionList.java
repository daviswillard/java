package day01.ex04.transactionlist;

import day01.ex04.models.Transaction;

import java.util.UUID;

public interface TransactionList {
    void            addTransaction(Transaction transfer);
    void            removeTransaction(UUID id);
    Transaction[]   toArray();
}
