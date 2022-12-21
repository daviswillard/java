package day01.ex05.menu;

import day01.ex05.models.TransCat;
import day01.ex05.models.Transaction;
import day01.ex05.models.User;
import day01.ex05.services.IllegalTransactionException;
import day01.ex05.services.TransactionService;

public class MenuImplementation implements Menu {

	TransactionService service;

public MenuImplementation(TransactionService service) {
        this.service = service;
        }

@Override
public void addUser(User client) {
        service.addUser(client);
        System.out.println("User with id = " + client.getId() + " is added");
        }

@Override
public void showBalance(Integer id) {
        System.out.println(service.getUserBalance(id));
        }

@Override
public void performTransfer(Integer A, Integer B, Integer amount) {
        try {
        service.makeTransaction(A, B, amount);
        } catch (IllegalTransactionException ex) {
        System.err.println("Transfer was not completed!\nCheck data validity and try again");
        return ;
        }
        System.out.println("The transfer is completed");
        }

@Override
public void showAllTransaction(Integer client) {
        Transaction[] list = service.getTransactions(client);
        for (Transaction trans : list) {
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
        }
    }
	@Override
	public void finish() {
		System.out.println("Program finished");
		System.exit(0);
	}
}
