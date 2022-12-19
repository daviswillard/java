package day01.ex05.services;

public class IllegalTransactionException extends RuntimeException {
	public IllegalTransactionException() {
		super("Couldn't complete transaction");
	}
}
