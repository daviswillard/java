package day01.ex05.transactionlist;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("No such transaction");
    }
}

