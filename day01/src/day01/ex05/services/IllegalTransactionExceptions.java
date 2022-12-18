package day01.ex05.services;

public class IllegalTransactionExceptions extends RuntimeException {
	public IllegalTransactionExceptions() {
		super("Transaction amount exceeds user balance");
	}
}
