package day01.ex04.services;

import day01.ex04.models.ListNode;
import day01.ex04.models.TransCat;
import day01.ex04.models.Transaction;
import day01.ex04.models.User;
import day01.ex04.transactionlist.TransactionLinkedList;
import day01.ex04.userslist.UsersArrayList;
import day01.ex04.userslist.UsersList;

import java.util.UUID;

class IllegalTransactionExceptions extends RuntimeException {
    public IllegalTransactionExceptions() {
        super("Transaction amount exceeds user balance");
    }
}

public class TransactionService {

    private static class TransactionHelper {
        public static boolean hasAPair(Transaction test, TransactionLinkedList list) {
            ListNode node = list.getStart();
            while (node != null) {
                if (test.getId().equals(node.getValue().getId())) {
                    return true;
                }
                node = node.getNext();
            }
            return false;
        }
    }

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
        Transaction transReverse = new Transaction(transaction);
        if ((
                transaction.getCategory() == TransCat.OUTCOME
                        && transaction.getAmount() * -1 > first.getBalance()
        ) || (
                transReverse.getCategory() == TransCat.OUTCOME
                        && transReverse.getAmount() * -1 > second.getBalance()
        )) {

            throw new IllegalTransactionExceptions();
        }
        first.getTransactions().addTransaction(transaction);
        second.getTransactions().addTransaction(transReverse);
    }

    public Transaction[] getTransactions(Integer id) {
        return usersList.getUser(id).getTransactions().toArray();
    }

    public void removeTransaction(UUID transactionID, Integer id) {
        usersList.getUser(id).getTransactions().removeTransaction(transactionID);
    }

    public Transaction[] getUnpairedTransactions() {
        TransactionLinkedList list = new TransactionLinkedList();
        boolean flag;

        for (int i = 0; i < usersList.getUserCount(); ++i) {
            ListNode listNode = usersList.getUser(i).getTransactions().getStart();

            while (listNode != null) {
                flag = false;
                for (int j = 0; j < usersList.getUserCount(); j++) {
                    if (i != j) {
                        if (TransactionHelper.hasAPair(listNode.getValue(), usersList.getUser(j).getTransactions())) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    list.addTransaction(listNode.getValue());
                }
                listNode = listNode.getNext();
            }
        }
        return list.toArray();
    }
}
