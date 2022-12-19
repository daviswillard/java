package day01.ex05.transactionlist;

public class TransactionListEmptyException extends RuntimeException {
    public TransactionListEmptyException() {
        super("Transaction list is empty");
    }
}
