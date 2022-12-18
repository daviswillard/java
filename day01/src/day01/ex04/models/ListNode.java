package day01.ex04.models;

public class ListNode {
	Transaction value;
	ListNode next;
	ListNode prev;

	public ListNode(ListNode prev, ListNode next, Transaction value) {
		this.next = next;
		this.prev = prev;
		this.value = value;
	}

	public Transaction getValue() {
		return value;
	}

	public void setValue(Transaction value) {
		this.value = value;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public ListNode getPrev() {
		return prev;
	}

	public void setPrev(ListNode prev) {
		this.prev = prev;
	}
}