package day01.ex05;

import day01.ex05.menu.DevMenuImplementation;
import day01.ex05.menu.Menu;
import day01.ex05.menu.MenuImplementation;
import day01.ex05.models.NegativeBalanceException;
import day01.ex05.models.User;
import day01.ex05.models.UserNotFoundException;
import day01.ex05.services.IllegalTransactionException;
import day01.ex05.services.TransactionService;
import day01.ex05.transactionlist.TransactionListEmptyException;
import day01.ex05.transactionlist.TransactionNotFoundException;

import java.util.Scanner;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionService service = new TransactionService();
        Scanner console = new Scanner(System.in);

        Menu menu;
        if (args.length > 0 && args[0].equals("--profile=dev")) {
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
            int i;
            try {
                i = Integer.parseInt(console.nextLine());
                if ((i == 5 || i == 6) && menu instanceof MenuImplementation) {
                    System.err.println("Numbers 5 and 6 reserved for dev");
                }
                switch (i) {

                    case 1:
                        System.out.println("Enter a user name and a balance");
                        String s = console.nextLine();
                        String[] split1 = s.split(" ", 2);
                        if (split1.length != 2) {
                            System.err.println("Illegal input");
                            break;
                        }
                        User user;
                        try {
                            user = new User(split1[0], Integer.parseInt(split1[1]));
                        } catch (NegativeBalanceException ex) {
                            ex.printStackTrace();
                            break;
                        }
                        menu.addUser(user);
                        break;

                    case 2:
                        System.out.println("Enter a user ID");
                        int i2 = Integer.parseInt(console.nextLine());
                        try {
                            menu.showBalance(i2);
                        } catch (UserNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                        String[] split3 = console.nextLine().split(" ", 3);
                        if (split3.length != 3) {
                            System.err.println("Illegal input");
                            break;
                        }
                        try {
                            menu.performTransfer(Integer.parseInt(split3[0]), Integer.parseInt(split3[1]),
                                    Integer.parseInt(split3[2]));
                        } catch (IllegalTransactionException ex) {
                            System.err.println("Couldn't complete transaction");
                        } catch (UserNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case 4:
                        System.out.println("Enter a user id");
                        int i4 = Integer.parseInt(console.nextLine());
                        try {
                            menu.showAllTransaction(i4);
                        } catch (UserNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case 5:
                        System.out.println("Enter a user ID and a transfer ID");
                        String[] split5 = console.nextLine().split(" ", 2);
                        if (split5.length != 2) {
                            System.err.println("Illegal input");
                            break;
                        }
                        try {
                            ((DevMenuImplementation) menu)
                                    .removeTransaction(Integer.parseInt(split5[0]), UUID.fromString(split5[1]));
                        } catch (UserNotFoundException | TransactionNotFoundException | TransactionListEmptyException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case 6:
                        ((DevMenuImplementation) menu).checkTransferValidity();
                        break;
                    case 7:
                        menu.finish();
                    default:
                        System.err.println("You must enter one number from 1 to 7");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Illegal input");
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
