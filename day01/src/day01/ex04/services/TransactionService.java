package day01.ex04.services;

import day01.ex04.models.Transaction;
import day01.ex04.models.User;
import day01.ex04.userslist.UsersArrayList;
import day01.ex04.userslist.UsersList;

import java.util.UUID;

public class TransactionService {

    private static UsersList usersList;

    public TransactionService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User client) {
        usersList.addUser(client);
    }

    public Integer getUserBalance(User client) {
        if (client == null || client.getId() == null) {
            System.err.println("You passed nonexistent client argument!");
            System.exit(0);
        }
        return usersList.getUser(client.getId()).getBalance();
    }

    public Integer getUserBalance(Integer clientID) {
        if (clientID == null) {
            System.err.println("You passed nonexistent client argument!");
            System.exit(0);
        }
        return usersList.getUser(clientID).getBalance();
    }

    public void makeTransaction(User first, User second, Integer value) {
        Transaction transaction = new Transaction(first, second, value);
    }
}
