package day01.ex04.transactionlist;


import day01.ex04.models.User;
import day01.ex04.userslist.UsersArrayList;
import day01.ex04.models.Transaction;
import day01.ex04.models.ListNode;

import java.util.Arrays;
import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("No such transaction");
    }
}

class TransactionListEmptyException extends RuntimeException {
    public TransactionListEmptyException() {
        super("Transaction list is empty");
    }
}

public class TransactionLinkedList implements TransactionList {

    private ListNode    start;
    private ListNode    end;
    private Integer     len;

    public TransactionLinkedList() {
        start = null;
        end = null;
        len = 0;
    }

    public Integer getLen() {
        if (start == null) {
            throw new TransactionListEmptyException();
        }
        return len;
    }

    public ListNode getStart() {
        if (start == null) {
            throw new TransactionListEmptyException();
        }
        return start;
    }

    @Override
    public void addTransaction(Transaction transfer) {
        if (len == 0) {
            start = new ListNode(null, null, transfer);
            end = start;
        } else {
            end.setNext(new ListNode(end, null, transfer));
            end = end.getNext();
        }
        len++;
    }

    @Override
    public void removeTransaction(UUID id) {
        ListNode temp = start;
        ListNode prev;
        ListNode next;


        if (start == null) {
            throw new TransactionListEmptyException();
        }
        if (start.getValue().getId() == id) {
            start = start.getNext();
            len--;
            return ;
        }
        for (int i = 0; i < len; i++) {
            if (temp.getValue().getId() == id) {
                break ;
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new TransactionNotFoundException();
        }
        prev = temp.getPrev();
        next = temp.getNext();
        if (prev != null) {
            prev.setNext(temp.getNext());
        }
        if (next != null) {
            next.setPrev(temp.getPrev());
        }
        temp.setNext(null);
        temp.setPrev(null);
        len--;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transArr = new Transaction[len];
        ListNode temp = start;

        for (int i = 0; i < len; i++) {
            transArr[i] = temp.getValue();
            temp = temp.getNext();
        }
        return (transArr);
    }

    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", 200);

        list.addUser(kamil);
        list.addUser(ramil);
        list.addUser(shamil);
        list.addUser(emil);
        for (int i = 0; i < 10; i++) {
            list.addUser(new User(i + "mil", 0));
        }
        System.out.println(list.getUserCount());
        for (int i = 0; i < list.getUserCount() - 1; i++) {
            System.out.println(list.getUser(i).getName());
        }
        System.out.println("-------------");
        for (int i = 0; i < list.getUserCount() - 1; i++) {
            System.out.println(list.getUserIndex(i).getName());
        }
        try {
            list.getUser(50);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        {
            TransactionLinkedList list1 = new TransactionLinkedList();
            list1.addTransaction(new Transaction(kamil, ramil, 200));
            list1.addTransaction(new Transaction(kamil, shamil, 400));
            list1.addTransaction(new Transaction(kamil, emil, 600));
            Transaction[] arr = list1.toArray();
            System.out.println(Arrays.toString(arr));
            list1.removeTransaction(list1.toArray()[1].getId());
            arr = list1.toArray();
            System.out.println(Arrays.toString(arr));
        }
    }
}
