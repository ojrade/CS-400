import java.util.ArrayList; // allowed for creating traversal lists
import java.util.LinkedList;
import java.util.List;
import java.util.Queue; // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K, V> root;// root of tree
	protected int numKeys; // number of keys in BST
	protected List<K> trav;// traversal list
	boolean rem;// if removed

	// Must have a public, default no-arg constructor
	public BST() {
		root = null;
		numKeys = 0;
		trav = new ArrayList<K>();
		rem = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getPreOrderTraversal()
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		trav = new ArrayList<K>();
		if (numKeys == 0) {
			return trav;
		}
		BSTNode<K, V> temp = root;
		getPreOrderTraversalHelper(temp);
		return trav;
	}

	/**
	 * Helper method for getPreOrderTraversal order: root-left-right
	 * 
	 * @param n recursive node starting at root
	 */
	private void getPreOrderTraversalHelper(BSTNode<K, V> n) {
		if (n == null) {
			return;
		}
		trav.add(n.getKey());
		getPreOrderTraversalHelper(n.getLeft());
		getPreOrderTraversalHelper(n.getRight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getPostOrderTraversal()
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		trav = new ArrayList<K>();
		if (numKeys == 0) {
			return trav;
		}
		BSTNode<K, V> temp = root;
		getPostOrderTraversalHelper(temp);
		return trav;
	}

	/**
	 * Helper method for getPostOrderTraversal order: left-right-root
	 * 
	 * @param n recursive node starting at root
	 */
	private void getPostOrderTraversalHelper(BSTNode<K, V> n) {
		if (n == null) {
			return;
		}
		getPostOrderTraversalHelper(n.getLeft());
		getPostOrderTraversalHelper(n.getRight());
		trav.add(n.getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getLevelOrderTraversal()
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		trav = new ArrayList<K>();
		if (numKeys == 0) {
			return trav;
		}
		BSTNode<K, V> temp = root;
		getLevelOrderTraversalHelper(temp);
		return trav;
	}

	/**
	 * Helper method for getLevelOrderTraversal
	 * 
	 * @param n recursive node starting at root
	 */
	private void getLevelOrderTraversalHelper(BSTNode<K, V> n) {
		Queue<BSTNode<K, V>> queue = new LinkedList<BSTNode<K, V>>();
		queue.add(n);
		while (!queue.isEmpty()) {
			BSTNode<K, V> temp = queue.poll();
			trav.add(temp.getKey());
			if (temp.getLeft() != null) {
				queue.add(temp.getLeft());
			}
			if (temp.getRight() != null) {
				queue.add(temp.getRight());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SearchTreeADT#getInOrderTraversal()
	 */
	@Override
	public List<K> getInOrderTraversal() {
		trav = new ArrayList<K>();
		if (numKeys == 0) {
			return trav;
		}
		BSTNode<K, V> temp = root;
		getInOrderTraversalHelper(temp);
		return trav;
	}

	/**
	 * Helper method for getInOrderTraversal order: left-root-right
	 * 
	 * @param n recursive node starting at root
	 */
	private void getInOrderTraversalHelper(BSTNode<K, V> n) {
		if (n == null) {
			return;
		}
		getInOrderTraversalHelper(n.getLeft());
		trav.add(n.getKey());
		getInOrderTraversalHelper(n.getRight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			if (contains(key)) {
				throw new DuplicateKeyException();
			}
			insertHelper(root, new BSTNode(key, value, null, null, null, 0, 0));
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (DuplicateKeyException e) {
			throw e;
		}
	}

	/**
	 * Helper for insert method
	 * 
	 * @param current Recursive searching node starting at root
	 * @param n       node to be inserted
	 * @throws IllegalNullKeyException when key is null
	 * @throws DuplicateKeyException   when the key is already in the tree
	 */
	private void insertHelper(BSTNode<K, V> current, BSTNode<K, V> n)
			throws IllegalNullKeyException, DuplicateKeyException {
		int x = 0;
		if (numKeys == 0) {// empty tree
			root = n;
			numKeys++;
		} else {
			if (n.getKey().compareTo(current.getKey()) < 0) {// key is less than current's key
				if (current.getLeft() != null) {
					insertHelper(current.getLeft(), n);
				} else {
					current.setLeft(n);
					n.setParent(current);
					numKeys++;
				}
			}
			if (n.getKey().compareTo(current.getKey()) > 0) {// key is less than current's key
				if (current.getRight() != null) {
					insertHelper(current.getRight(), n);
				} else {
					current.setRight(n);
					n.setParent(current);
					numKeys++;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			if (!contains(key)) {
				throw new KeyNotFoundException();
			}
			rem = false;
			root = removeHelper(root, key);
			return rem;
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (KeyNotFoundException e) {
			throw e;
		}
	}

	/**
	 * Helper for the remove method
	 * 
	 * @param current Recursive searching node starting at root
	 * @param key     of node to be removed
	 * @return Root node after operation
	 * @throws IllegalNullKeyException if key is null;
	 */
	private BSTNode<K, V> removeHelper(BSTNode<K, V> current, K key) throws IllegalNullKeyException {
		if (current == null) {
			return current;
		}
		if (contains(key)) {
			rem = true;
		} else {
			rem = false;
			return current;
		}
		if (key.compareTo(current.key) < 0) {// key is less than current's key
			current.left = removeHelper(current.left, key);
		} else if (key.compareTo(current.key) > 0) {// key is greater than current's key
			current.right = removeHelper(current.right, key);
		} else {// key is equal to current's key
			if (current.left == null) {
				rem = true;
				return current.right;
			} else if (current.right == null) {
				rem = true;
				return current.left;
			}
			current.key = minValueNode(current.right).key;
			current.value = minValueNode(current.right).value;
			current.right = removeHelper(current.right, current.key);
		}
		return current;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			if (!contains(key)) {
				throw new KeyNotFoundException();
			}
			return getHelper(root, key).getValue();
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (KeyNotFoundException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			BSTNode temp = getHelper(root, key);// finds node with key
			return false;
		} catch (IllegalNullKeyException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return numKeys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyAtRoot()
	 */
	@Override
	public K getKeyAtRoot() {
		return root.getKey();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			if (!contains(key)) {
				throw new KeyNotFoundException();
			}
			BSTNode<K, V> temp = getHelper(root, key);// finds node with key
			temp = temp.getLeft();
			if (temp == null) {
				return null;
			}
			return temp.getKey();
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (KeyNotFoundException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		try {
			if (key == null) {
				throw new IllegalNullKeyException();
			}
			if (!contains(key)) {
				throw new KeyNotFoundException();
			}
			BSTNode<K, V> temp = getHelper(root, key);// finds node with key
			temp = temp.getRight();
			if (temp == null) {
				return null;
			}
			return temp.getKey();
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (KeyNotFoundException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTADT#getHeight()
	 */
	@Override
	public int getHeight() {
		int h = getHeightHelper(root, 2);
		return h;
	}

	/**
	 * Helper method for getHeight
	 * 
	 * @param current recursive search node
	 * @param x       test
	 * @return height of node
	 */
	public int getHeightHelper(BSTNode<K, V> current, int x) {
		if (current == null) {
			return 0;
		}
		return Math.max(getHeightHelper(current.getLeft(), 0), getHeightHelper(current.getRight(), 1)) + 1;
	}

	/**
	 * Helper for get method
	 * 
	 * @param current
	 * @param key
	 * @return
	 */
	protected BSTNode<K, V> getHelper(BSTNode<K, V> current, K key) {
		if (root == null) {
			return null;
		}
		if (current != null) {
			if (key.compareTo(current.getKey()) < 0) {
				return getHelper(current.getLeft(), key);
			} else if (key.compareTo(current.getKey()) > 0) {
				return getHelper(current.getRight(), key);
			} else {
				return current;
			}
		} else {
			return null;
		}
	}

	/**
	 * Get in order first node in subtree
	 * 
	 * @param node root of subtree
	 * @return In order first value
	 */
	protected BSTNode<K, V> minValueNode(BSTNode<K, V> node) {
		BSTNode current = node;
		System.out.println(current.key);
		// loop down to find the leftmost leaf
		while (current.left != null) {
			current = current.left;
		}

		return current;
	}
}
