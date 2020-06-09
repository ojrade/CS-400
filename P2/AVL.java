// A BST search tree that maintains its balance using AVL rotations.
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {
	private boolean rem;// if value removed
	// must add rebalancing to BST via rotate operations

	public AVL() {
		rem = false;
	}

	/**
	 * Inserts new node into the AVL Tree
	 * 
	 * @param key   of node
	 * @param value of node
	 * @throws IllegalNullKeyException if Key is null
	 * @throws DuplicateKeyException   if key is already in tree
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
			root = insertHelper(root, new BSTNode<K, V>(key, value, null, null, null, 0, 0));
			numKeys++;
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (DuplicateKeyException e) {
			throw e;
		}
	}

	/**
	 * Helper for Insert Inserts then rebalances tree
	 * 
	 * @param current recursive node to find correct place to add
	 * @param n       node to be added
	 * @return root of rebalaned tree
	 * @throws IllegalNullKeyException if Key is null
	 * @throws DuplicateKeyException   if key is already in tree
	 */
	public BSTNode<K, V> insertHelper(BSTNode current, BSTNode n)
			throws IllegalNullKeyException, DuplicateKeyException {
		if (current == null) {// if tree is empty
			return n;
		}
		if (n.key.compareTo(current.key) < 0) {// if key is less then current node key
			n.parent = current;
			current.left = insertHelper(current.left, n);

		} else if (n.key.compareTo(current.key) > 0) {// if key is greater than current node key
			n.parent = current;
			current.right = insertHelper(current.right, n);
		} else {
			return current;
		}
		current.height = 1 + Math.max(gHeight(current.left), gHeight(current.right));// Sets new Node's height
		int bal = getBF(current);

		// REBALANCING
		// Left Left Case
		if (bal > 1 && n.key.compareTo(current.left.key) < 0) {
			return rotateRight(current);
		}
		// Right Right Case
		if (bal < -1 && n.key.compareTo(current.right.key) > 0) {
			return rotateLeft(current);
		}
		// Left Right Case
		if (bal > 1 && n.key.compareTo(current.left.key) > 0) {
			current.left = rotateLeft(current.left);
			return rotateRight(current);
		}
		// Right Left Case
		if (bal < -1 && n.key.compareTo(current.right.key) < 0) {
			current.right = rotateRight(current.right);
			return rotateLeft(current);
		}
		return current;
	}

	/**
	 * Removes node with key
	 * 
	 * @param key to be removed
	 * @return true if successfully removed and false otherwise
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not in tree
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
			if (rem) {
				numKeys--;
			}
			return rem;
		} catch (IllegalNullKeyException e) {
			throw e;
		} catch (KeyNotFoundException e) {
			throw e;
		}
	}

	/**
	 * Helper for remove method
	 * 
	 * @param current recursive node to find needed node
	 * @param key     key of node to be removed
	 * @return root of balanced search tree
	 * @throws IllegalNullKeyException
	 */
	public BSTNode<K, V> removeHelper(BSTNode<K, V> current, K key) throws IllegalNullKeyException {
		if (contains(key)) {// if exists in tree with root at current
			rem = true;
		} else {
			rem = false;
			return current;
		}
		BSTNode<K, V> temp = null;
		if (root == null) {// empty tree case
			return root;
		}
		if (key.compareTo(current.key) < 0) {// key is less than current's key
			current.left = removeHelper(current.left, key);
		} else if (key.compareTo(current.key) > 0) {// key is greater than current's key
			current.right = removeHelper(current.right, key);
		} else {
			// 1 or no children
			if (current.left == null || current.right == null) {
				if (current.left == null) {// one right child
					temp = current.right;
				} else {
					temp = current.left;// one left child
				}
				if (temp == null) {// no children
					temp = current;
					current = null;
				} else {
					temp.parent = current.parent;
					current = temp;
				}
			} else {// 2 children
				// set removing node to in order successor and delete successor
				temp = minValueNode(current.right);
				current.key = temp.key;
				current.value = temp.value;
				current.right = removeHelper(current.right, temp.key);
			}
		}
		if (current == null) {
			return current;
		}
		// height update
		current.height = Math.max((gHeight(current.left)), gHeight(current.right));
		int bf = getBF(current);

		// REBALANCING
		if (bf > 1) {
			// Left Left
			if (getBF(current.left) >= 0) {
				return rotateRight(current);
			}
			// Left Right
			if (getBF(current.left) < 0) {
				current.left = rotateLeft(current.left);
				return rotateRight(current);
			}
		}
		if (bf < -1) {
			// Right Right
			if (getBF(current.right) <= 0) {
				return rotateLeft(current);
			}
			// Right Left
			if (getBF(current.right) > 0) {
				current.right = rotateLeft(current.right);
				return rotateLeft(current);
			}
		}
		return current;
	}

	/**
	 * Gets height of node
	 * 
	 * @param node of which's height is to be found
	 * @return height of node
	 */
	public int gHeight(BSTNode<K, V> node) {
		if (node == null) {
			return 0;
		}
		return getHeightHelper(node, 2);
	}

	/**
	 * Returns balance factor of node
	 * 
	 * @param node balance factor of this node
	 * @return balance factor of node
	 */
	public int getBF(BSTNode<K, V> node) {
		if (node == null) {
			node.balanceFactor = 0;
		}
		return node.balanceFactor = gHeight(node.getLeft()) - gHeight(node.getRight());
	}

	/**
	 * Returns balance factor of node
	 * 
	 * @param key of node to be utilised
	 * @return balance factor of node
	 */
	public int getBFTest(K key) {
		BSTNode<K, V> node = getHelper(root, key);
		if (node == null) {// height if tree is empty
			node.balanceFactor = 0;
		}
		return node.balanceFactor = gHeight(node.getLeft()) - gHeight(node.getRight());
	}

	/**
	 * Rotates binary subtree to right
	 * 
	 * @param node root of subtree
	 * @return new root node
	 */
	private BSTNode<K, V> rotateRight(BSTNode<K, V> node) {
		BSTNode<K, V> nl = node.left;
		BSTNode<K, V> nlr = nl.right;
		BSTNode<K, V> par = node.parent;
		if (node != root) {// set parent's children
			if (node.getKey().compareTo(par.getKey()) < 0) {
				par.left = nl;
			}
			if (node.getKey().compareTo(par.getKey()) > 0) {
				par.right = nl;
			}
		}
		// rotate
		nl.right = node;
		nl.parent = node.parent;
		node.parent = nl;
		node.left = nlr;
		node.height = Math.max(gHeight(node.left), gHeight(node.right)) + 1;
		nl.height = Math.max(gHeight(nl.left), gHeight(nl.right)) + 1;
		return nl;
	}

	/**
	 * Rotates binary subtree to left
	 * 
	 * @param node root of subtree
	 * @return new root node
	 */
	private BSTNode<K, V> rotateLeft(BSTNode<K, V> node) {
		BSTNode<K, V> nr = node.getRight();
		BSTNode<K, V> nrl = nr.getLeft();
		BSTNode<K, V> par = node.getParent();
		if (node != root) {// setParents children
			if (node.getKey().compareTo(par.getKey()) < 0) {
				par.left = nr;
			}
			if (node.getKey().compareTo(par.getKey()) > 0) {
				par.right = nr;
			}
		}
		//rotate
		nr.left = node;
		nr.parent = node.parent;
		node.parent = nr;
		node.right = nrl;
		node.height = Math.max(gHeight(node.left), gHeight(node.right)) + 1;
		nr.height = Math.max(gHeight(nr.left), gHeight(nr.right)) + 1;
		return nr;
	}
}
