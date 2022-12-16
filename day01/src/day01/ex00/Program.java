package day01.ex00;

import day01.ex00.models.Transaction;
import day01.ex00.models.User;

public class Program {

    public static void main(String[] args) {
        User kamil = new User("Kamil", 1, 4000);
        User ramil = new User("Ramil", 2, 5000);
        User shamil = new User("Shamil", 3, 2500);
        User emil = new User("Emil", 4, -200);

        System.out.println(kamil);
        System.out.println(ramil);
        System.out.println(shamil);
        System.out.println(emil);

        {
            Transaction ramKam = new Transaction(ramil, kamil, -200);
            System.out.println(ramKam);
        }
        {
            Transaction shamKam = new Transaction(shamil, kamil, 200);
            System.out.println(shamKam);
        }
        {
            Transaction kamEm = new Transaction(emil, kamil, -200);
            System.out.println(kamEm);
        }
    }
}
