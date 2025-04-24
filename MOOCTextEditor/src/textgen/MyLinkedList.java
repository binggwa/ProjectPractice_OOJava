package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			return false;
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		this.size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index >= this.size() || index < 0) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		
		LLNode<E> curr = this.head.next;
		int i = 0;
		while(i++ < index) {
			curr = curr.next;
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("cannot store null pointers");
		}
		if (index > this.size() || index < 0) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		
		LLNode<E> prev = this.head;
		LLNode<E> curr = prev.next;
		
		int i = 0;
		while(i++ < index) {
			prev = prev.next;
			curr = curr.next;
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = curr;
		newNode.prev = prev;
		curr.prev = newNode;
		prev.next = newNode;
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index is outside the bounds of the list");
		}
		
		LLNode<E> prev = this.head;
		LLNode<E> curr = prev.next;
		
		int i = 0;
		while(i++ < index) {
			prev = prev.next;
			curr = curr.next;
		}
		
		prev.next = curr.next;
		curr.next.prev = prev;
		curr.next = null;
		curr.prev = null;
		this.size--;
		
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index is outside the bounds of the list");
		}
		if (element == null) {
			throw new NullPointerException("cannot store null pointers");
		}
		
		LLNode<E> curr = this.head.next;
		
		int i = 0;
		while (i++ < index) {
			curr = curr.next;
		}
		
		return curr.data = element;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
