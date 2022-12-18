package day01.ex05.menu;

import day01.ex05.models.ListNode;
import day01.ex05.models.TransCat;
import day01.ex05.models.Transaction;
import day01.ex05.models.User;
import day01.ex05.services.IllegalTransactionExceptions;
import day01.ex05.services.TransactionService;
import day01.ex05.transactionlist.TransactionLinkedList;

import java.util.UUID;

public class DevMenuImplementation implements Menu {

	TransactionService service;

	public DevMenuImplementation(TransactionService service) {
		this.service = service;
	}

	@Override
	public void addUser(User client) {
		service.addUser(client);
		System.out.println("User with id = " + client.getId() + " was added");
	}

	@Override
	public void showBalance(User client) {
		System.out.println(service.getUserBalance(client));
	}

	@Override
	public void performTransfer(User A, User B, Integer amount) {
		try {
			service.makeTransaction(A, B, amount);
		} catch (IllegalTransactionExceptions ex) {
			System.err.println("Transfer was not completed!\nCheck data validity and try again");
			return ;
		}
		System.out.println("Transfer was completed");
	}

	@Override
	public void showAllTransaction(User client) {
		ListNode node = client.getTransactions().getStart();
		while (node != null) {
			Transaction trans = node.getValue();
			if (trans.getCategory() == TransCat.OUTCOME) {
				System.out.print("To ");
			} else {
				System.out.print("From ");
			}
			String s = String.format("%s(id = %d) %d with id = %s",
					trans.getPersonB().getName(),
					trans.getPersonB().getId(),
					trans.getAmount(),
					trans.getId().toString());
			System.out.println(s);
			node = node.getNext();
		}
	}

	public void removeTransaction(User user, UUID id) {
		Transaction[] array = service.getTransactions(user.getId());
		Transaction trans = null;
		for (Transaction transaction : array) {
			if (transaction.getId() == id) {
				trans = transaction;
			}
		}
		service.removeTransaction(id, user.getId());
		String s;
		if (trans.getCategory() == TransCat.OUTCOME) {
			s = "To";
		} else {
			s = "From";
		}
		System.out.printf("Transfer %s %s(id = %d) %d removed",
				s,
				trans.getPersonB().getName(),
				trans.getPersonB().getId(),
				trans.getAmount());
	}

	public void checkTransferValidity() {
		System.out.println("Check results:");
		Transaction[] list = service.getUnpairedTransactions();
		for (Transaction iter : list) {
			String s;

			if (iter.getCategory() == TransCat.OUTCOME) {
				s = "To";
			} else {
				s = "From";
			}
			System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s %s %s(id = %d) for %d",
					iter.getPersonA().getName(),
					iter.getPersonA().getId(),
					iter.getId().toString(),
					s,
					iter.getPersonB().getName(),
					iter.getPersonB().getId(),
					iter.getAmount());
		}
	}

	@Override
	public void finish() {
		System.out.println("Program finished");
		System.exit(0);
	}
}
