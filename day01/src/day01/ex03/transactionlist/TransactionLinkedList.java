package day01.ex03.transactionlist;

import day01.ex03.models.Transaction;

import java.util.UUID;

class ListNode {
    Transaction value;
    ListNode next;
    ListNode prev;

    public ListNode(ListNode prev, ListNode next, Transaction value) {
        this.next = next;
        this.prev = prev;
        this.value = value;
    }
}

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
            end.next = new ListNode(end, null, transfer);
            end = end.next;
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
        if (start.value.getId() == id) {
            start = start.next;
            len--;
            return ;
        }
        for (int i = 0; i < len; i++) {
            if (temp.value.getId() == id) {
                break ;
            }
            temp = temp.next;
        }
        if (temp == null) {
            throw new TransactionNotFoundException();
        }
        prev = temp.prev;
        next = temp.next;
        if (prev != null) {
            prev.next = temp.next;
        }
        if (next != null) {
            next.prev = temp.prev;
        }
        temp.next = null;
        temp.prev = null;
        len--;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transArr = new Transaction[len];
        ListNode temp = start;

        for (int i = 0; i < len; i++) {
            transArr[i] = temp.value;
            temp = temp.next;
        }
        return (transArr);
    }


}
