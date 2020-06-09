///////////////////////////////////////////////////////////
//Title:           p1 Implement and Test DataStructure.adt
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        2/7/2019
//////////////////////////////////////////////////////////
/**
 * Implementation of the DataStructureADT as a linked list
 * @author Ojas
 *
 */
public class DS_My implements DataStructureADT {
	/**
	 * Nodes which represent each individual insert
	 * @author Ojas
	 *
	 */
	private class Node {
		private Object key;//key value of the node. Cannot be duplicate
		private Object val;//Value of the node
		private Node next;//Next node in list
		/**
		 * Constructor of the Node
		 * @param k Key of the node
		 * @param v Value of the node
		 */
		private Node(Object k, Object v) {
			key = k;
			val = v;
			next = null;
		}
	}

	// Private Fields of the class
	// TODO create field(s) here to store data pairs
	private Node current;//First Node in the list
	private int size;//Number of Nodes in the list

	/**
	 * Constructor of the Linked List
	 */
	public DS_My() {
		current=null;
		size=0;
	}

	/**
	 * Inserts a new node to the end of the list
	 * 
	 * @param k Key of the node to be inserted
	 * @param v Value of the node to be inserted
	 * @throws RuntimeException when a node with that key already exists
	 * @throws IllegalArgumentException when k is null
	 */
	@Override
	public void insert(Comparable k, Object v) {
		try {
			if (size > 0) {
				if (contains(k)) {
					throw new RuntimeException("duplicate key");
				}
			}
			if (k == null) {
				throw new IllegalArgumentException("null key");
			}
			if (size == 0) {//if none then create first node
				current = new Node(k, v);
				size++;
			} else {
				Node temp = current;
				while (temp.next != null) {
					temp = temp.next;
				}
				temp.next = new Node(k, v);
				size++;
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			throw e;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
			System.out.println(e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	/**
	 * Removes a node with a specific key
	 * 
	 * @param k Key of node to be removed
	 * @throws IllegalArgumentException when k is null
	 */
	@Override
	public boolean remove(Comparable k) {
		try {
			if (k == null) {
				throw new IllegalArgumentException("null key");
			}
			if (current == null) {
				return false;
			}
			boolean r = false;
			Node temp = current;
			if (current.key.equals(k)) {
				r = true;
				if (current.next != null) {
					current = current.next;
				} else {
					current = null;
				}
				size--;
			}
			while (temp.next != null && !r) {
				if (temp.next.key.equals(k)) {
					r = true;
					temp.next = temp.next.next;
					size--;
				} else {
					temp = temp.next;
				}
			}
			return r;
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			throw e;
		}
	}

	/**
	 * Checks if the linked list contains a Node with a specific key
	 * 
	 * @param k key of node to be checked for
	 * @throws IllegalArgumentException when k is null
	 */
	@Override
	public boolean contains(Comparable k) {
		try {
		boolean r = false;
		Node temp = current;
		if(k==null) {
			throw new IllegalArgumentException ("null key");
		}
		if(size==0) {
			System.out.println("WE");
			return false;
		}
		if (current.key.equals(k)) {
			return true;
		}
		while (temp.next != null && !r) {
			if (temp.key.equals(k)) {
				return true;
			} else {
				temp = temp.next;
			}
		}
		if (!r) {
			if (temp.key.equals(k)) {
				return true;
			}
		}
		return false;
		}catch(IllegalArgumentException e) {
			System.out.println(e.toString());
			return false;
		}
	}

	/**
	 * Returns the value of a specific node
	 * 
	 * @param k key of node who's value is to be returned
	 * @return the value of the node with key k
	 * @throws IllegalArgumentException when k is null
	 */
	@Override
	public Object get(Comparable k) {
		try {
			boolean f = false;
			Node temp = current;
			if (k == null) {
				throw new IllegalArgumentException("null key");
			}
			while (temp != null) {
				if (temp.key.equals(k)) {
					return temp.val;
				}
				else {
					temp=temp.next;
				}
			}
			return null;
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			throw e;
		}
	}

	/**
	 * @return the size of the LinkedList
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
