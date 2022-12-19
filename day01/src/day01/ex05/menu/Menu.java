package day01.ex05.menu;

import day01.ex05.models.User;

public interface Menu {
	void addUser(User client);
	void showBalance(Integer id);
	void performTransfer(Integer A, Integer B, Integer amount);
	void showAllTransaction(Integer client);
	void finish();
}
