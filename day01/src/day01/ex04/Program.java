package day01.ex04;

import day01.ex04.models.Transaction;
import day01.ex04.models.User;
import day01.ex04.services.TransactionService;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        TransactionService service = new TransactionService();

        User kamil = new User("Kamil", 1000);
        User ramil = new User("Ramil", 2000);
        User shamil = new User("Shamil", 3000);
        User fanil = new User("Fanil", 1500);
        User emil = new User("Emil", 2500);
        service.addUser(kamil);
        service.addUser(ramil);
        service.addUser(shamil);
        service.addUser(fanil);
        service.addUser(emil);

        service.makeTransaction(kamil, ramil, 200);
        service.makeTransaction(kamil, ramil, -200);
        service.makeTransaction(kamil, shamil, 300);
        service.makeTransaction(kamil, shamil, -300);
        service.makeTransaction(kamil, emil, 400);
        service.makeTransaction(kamil, emil, -400);
        service.makeTransaction(kamil, fanil, 1500);
        service.makeTransaction(kamil, fanil, -500);

        service.removeTransaction(kamil.getTransactions().toArray()[0].getId(), kamil.getId());
        service.removeTransaction(shamil.getTransactions().toArray()[1].getId(), shamil.getId());
        Transaction[] transArr = service.getUnpairedTransactions();
        System.out.println(Arrays.toString(transArr));
    }
}
