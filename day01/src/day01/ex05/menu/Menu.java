package day01.ex05.menu;

import day01.ex05.models.User;

public interface Menu {
	void addUser(User client);
	void showBalance(User client);
	void performTransfer(User A, User B, Integer amount);
	void showAllTransaction(User client);
	void finish();
}
