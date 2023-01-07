package day01.ex05.services;

import day01.ex05.models.*;
import day01.ex05.transactionlist.TransactionLinkedList;
import day01.ex05.userslist.UsersArrayList;
import day01.ex05.userslist.UsersList;

import java.util.UUID;

public class TransactionService {

    private static class TransactionHelper {
        public static boolean hasAPair(Transaction test, TransactionLinkedList list) {
            if (list.getLen() == 0) {
                return false;
            }
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

    public String getUserBalance(Integer clientID) throws UserNotFoundException {
        if (clientID == null) {
            System.err.println("You passed nonexistent edu.school21.sockets.client argument!");
            throw new UserNotFoundException();
        }
        User temp = usersList.getUser(clientID);
        return String.format("%s - %d", temp.getName(), temp.getBalance());
    }

    public void makeTransaction(Integer firstArg, Integer secondArg, Integer value) {
        User first = usersList.getUser(firstArg);
        User second = usersList.getUser(secondArg);

        Transaction transaction = new Transaction(first, second, value);
        Transaction transReverse = new Transaction(transaction);
        if ((
                transaction.getCategory() == TransCat.OUTCOME
                        && transaction.getAmount() * -1 > first.getBalance()
        ) || (
                transReverse.getCategory() == TransCat.OUTCOME
                        && transReverse.getAmount() * -1 > second.getBalance()
        ) || first.equals(second)
        ) {

            throw new IllegalTransactionException();
        }
        first.getTransactions().addTransaction(transaction);
        second.getTransactions().addTransaction(transReverse);
        first.setBalance(transaction.getAmount());
        second.setBalance(transReverse.getAmount());
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
            TransactionLinkedList linkedList = usersList.getUser(i).getTransactions();
            if (linkedList.getLen() == 0) {
                continue;
            }
            ListNode listNode = linkedList.getStart();
            while (listNode != null) {
                flag = false;
                for (int j = 0; j < usersList.getUserCount(); j++) {
                    if (i != j) {
                        if (TransactionHelper.hasAPair(listNode.getValue(),
                                usersList.getUser(j).getTransactions())) {
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
