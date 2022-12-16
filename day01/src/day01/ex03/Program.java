package day01.ex03;

import day01.ex03.models.Transaction;
import day01.ex03.models.User;

public class Program {

    public static void main(String[] args) {
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", -200);
        Transaction[] arr;

        System.out.println(kamil.getId() + "\n" + kamil.getName());
        System.out.println(ramil.getId() + "\n" + ramil.getName());
        kamil.getTransactions().addTransaction(new Transaction(kamil, ramil, 500));
        ramil.getTransactions().addTransaction(new Transaction(ramil, kamil, -500));
        kamil.getTransactions().addTransaction(new Transaction(kamil, shamil, 2000));
        kamil.getTransactions().addTransaction(new Transaction(kamil, emil, -500));
        arr = kamil.getTransactions().toArray();
        for (int i = 0; i < kamil.getTransactions().getLen(); i++) {
            System.out.println(arr[i]);
        }

        {
            kamil.getTransactions().removeTransaction(arr[1].getId());
            System.out.println("--------------");
            arr = kamil.getTransactions().toArray();
            for (int i = 0; i < kamil.getTransactions().getLen(); i++) {
                System.out.println(arr[i]);
            }
        }
    }
}
