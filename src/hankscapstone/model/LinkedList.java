package hankscapstone.model;

/**
 *
 * @author Bryant hanks
 * @version 1.0 2023-04-26
 */
public class LinkedList {

    Node head;

    static class Node {

	Expense expense;
	Node next;

	Node(Expense e) {
	    expense = e;
	    next = null;
	}
    }

    public static void clear(LinkedList list) {
	list.head = null;
    }

    public static LinkedList insert(LinkedList list, Expense expense) {
	Node new_node = new Node(expense);

	if (list.head == null) {
	    list.head = new_node;
	} else {
	    Node last = list.head;
	    while (last.next != null) {
		last = last.next;
	    }
	    last.next = new_node;
	}
	return list;
    }

    public static void snowballSort(LinkedList list) {
	Node currNode = list.head;
	Node nodeIndex = null;
	Expense placeHolder = null;

	while (currNode != null) {
	    nodeIndex = currNode.next;
	    while (nodeIndex != null) {
		if (currNode.expense.getRemainingBalance()
		      > nodeIndex.expense.getRemainingBalance()) {
		    placeHolder = currNode.expense;
		    currNode.expense = nodeIndex.expense;
		    nodeIndex.expense = placeHolder;
		}
		nodeIndex = nodeIndex.next;
	    }
	    currNode = currNode.next;
	}
    }
}
