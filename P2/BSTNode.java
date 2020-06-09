// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// package level access means that all classes in the package can access directly.
//
// Classes that use this type:  <TODO, list which if any classes use this type>
class BSTNode<K extends Comparable<K>, V> {

	K key;//key of node
	V value;//value of node
	BSTNode<K, V> parent;//parent node
	BSTNode<K, V> left;//left child
	BSTNode<K, V> right;//right child
	int balanceFactor;//balance factor of node
	int height;//height of node

	/**
	 * @param key key of node
	 * @param value of node
	 * @param par parent of node
	 * @param leftChild left child
	 * @param rightChild right child
	 * @param h height of node
	 * @param bf balance factor of node
	 */
	public BSTNode(K key, V value, BSTNode<K, V> par, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild, int h,
			int bf) {
		this.key = key;
		this.value = value;
		this.parent = par;
		this.left = leftChild;
		this.right = rightChild;
		this.height = h;
		this.balanceFactor = bf;
	}

	//SETTER AND GETTER METHODS
	public void setParent(BSTNode n) {
		this.parent = n;
	}

	public void setLeft(BSTNode n) {
		this.left = n;
	}

	public void setRight(BSTNode n) {
		this.right = n;
	}

	public BSTNode getParent() {
		return parent;
	}

	public BSTNode getLeft() {
		return left;
	}

	public BSTNode getRight() {
		return right;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public void setbalanceFactor(int bf) {
		balanceFactor = bf;
	}

	public int getHeight() {
		return height;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}
