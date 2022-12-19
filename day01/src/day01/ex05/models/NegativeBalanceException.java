package day01.ex05.models;

public class NegativeBalanceException extends RuntimeException{
    public NegativeBalanceException() {
        super("User has negative starting balance");
    }
}
