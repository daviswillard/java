package day01.ex01;

import day01.ex01.models.User;

public class Program {

    public static void main(String[] args) {
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", -200);

        System.out.println(kamil);
        System.out.println(ramil);
        System.out.println(shamil);
        System.out.println(emil);
    }
}
