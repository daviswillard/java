package day01.ex05;

import day01.ex05.menu.DevMenuImplementation;
import day01.ex05.menu.Menu;
import day01.ex05.menu.MenuImplementation;
import day01.ex05.services.TransactionService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        TransactionService service = new TransactionService();
        Scanner console = new Scanner(System.in);

        Menu menu;
        if (args[0].equals("--profile=dev")) {
            menu = new DevMenuImplementation(service);
        } else {
            menu = new MenuImplementation(service);
        }
        while (true) {
            System.out.println("1. Add a user\n" +
                    "2. View user balances\n" +
                    "3. Perform a transfer\n" +
                    "4. View all transactions for a specific user\n" +
                    "5. DEV – remove a transfer by ID\n" +
                    "6. DEV – check transfer validity\n" +
                    "7. Finish execution");
            String temp = console.nextLine();
            int i;
            try {
                i = Integer.parseInt(temp);
                if (i > 7 || i < 1) {
                    System.err.println("You must enter one number from 1 to 7");
                }
                if ((i == 5 || i == 6) && menu instanceof MenuImplementation) {
                    System.err.println("Numbers 5 and 6 reserved for dev");
                }
                switch (i) {
                    case 1:
                        System.out.println("Enter a user name and a balance");
                        console.nextLine();
//                        menu.addUser();  остановился здесь; надо написать парсер для меню
                }
            } catch (NumberFormatException ex) {
                System.err.println("You must enter one number from 1 to 7");
            }

        }
    }
}
