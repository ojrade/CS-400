import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the rebalancing of the AVL tree operations

@SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

	AVL<String, String> avl;
	AVL<Integer, String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String, String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer, String>();
	}

	/**
	 * Insert three values in sorted order and then check the root, left, and right
	 * keys to see if rebalancing occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20))
				fail("avl insert to right child of root does not work");

			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values in reverse sorted order and then check the root, left,
	 * and right keys to see if rebalancing occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
		try {
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			avl2.insert(20, "20");
			if (!avl2.getKeyOfLeftChildOf(30).equals(20))
				fail("avl insert to right child of root does not work");

			avl2.insert(10, "10");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values so that a right-left rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			avl2.insert(30, "30");
			if (!avl2.getKeyOfRightChildOf(10).equals(30))
				fail("avl insert to right child of root does not work");

			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values so that a left-right rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	@Test
	void testAVL_003_insert_largest_smallest_middle_order_simple() {
		try {
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			avl2.insert(10, "10");
			if (!avl2.getKeyOfLeftChildOf(30).equals(10))
				fail("avl insert to right child of root does not work");

			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test that the in-order traversal order is correct if the items are entered in
	 * a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30 In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		try {
			avl2.insert(10, "1st key inserted");
			avl2.insert(20, "2nd key inserted");
			avl2.insert(30, "3rd key inserted");
			List<Integer> inOrd = new ArrayList<Integer>();
			inOrd.add(10);
			inOrd.add(20);
			inOrd.add(30);
			List<Integer> list = avl2.getInOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != inOrd.get(i)) {
					fail("Inorder list should be 10-20-30, but is " + list.get(0) + "-" + list.get(1) + "-"
							+ list.get(2));
				}
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Test that the pre-order traversal order is correct if the items are entered
	 * in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_008_check_preOrder_for_not_balanced_insert_order() {
		try {
			avl2.insert(10, "1st key inserted");
			avl2.insert(20, "2nd key inserted");
			avl2.insert(30, "3rd key inserted");
			List<Integer> preOrd = new ArrayList<Integer>();
			preOrd.add(20);
			preOrd.add(10);
			preOrd.add(30);
			List<Integer> list = avl2.getPreOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != preOrd.get(i)) {
					fail("Preorder list should be 20-10-30, but is " + list.get(0) + "-" + list.get(1) + "-"
							+ list.get(2));
				}
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the items are entered
	 * in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
	 */
	@Test
	void testBST_009_check_postOrder_for_not_balanced_insert_order() {
		try {
			avl2.insert(10, "1st key inserted");
			avl2.insert(20, "2nd key inserted");
			avl2.insert(30, "3rd key inserted");
			List<Integer> postOrd = new ArrayList<Integer>();
			postOrd.add(10);
			postOrd.add(30);
			postOrd.add(20);
			List<Integer> list = avl2.getPostOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != postOrd.get(i)) {
					fail("Postorder list should be 10-30-20, but is " + list.get(0) + "-" + list.get(1) + "-"
							+ list.get(2));
				}
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the items are entered
	 * in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30 Level-Order traversal order: 10-20-30 (FIXED ON
	 * 2/14/18)
	 */
	@Test
	void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
		try {
			avl2.insert(10, "1st key inserted");
			avl2.insert(20, "2nd key inserted");
			avl2.insert(30, "3rd key inserted");
			List<Integer> levOrd = new ArrayList<Integer>();
			levOrd.add(20);
			levOrd.add(10);
			levOrd.add(30);
			List<Integer> list = avl2.getLevelOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != levOrd.get(i)) {
					fail("Levelorder list should be 20-10-30, but is " + list.get(0) + "-" + list.get(1) + "-"
							+ list.get(2));
				}
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}

	}

	/**
	 * Checks if the hieght of the tree is correct
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 */
	@Test
	void testBST_019_check_height_of_tree() throws IllegalNullKeyException, DuplicateKeyException {
		avl2.insert(20, "1st key inserted");
		avl2.insert(10, "2nd key inserted");
		avl2.insert(30, "3rd key inserted");
		avl.insert("10", "1st key inserted");
		avl.insert("20", "2nd key inserted");
		avl.insert("30", "3rd key inserted");
		if (avl.getHeight() != 2) {
			fail("avl height should be 2, but it is " + avl.getHeight());
		}
		if (avl2.getHeight() != 2) {
			fail("avl height should be 2, but it is " + avl.getHeight());
		}
	}

	@Test
	void testBST_024_remove_right_subtree()
			throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		bst2.insert(20, "1st key inserted");
		bst2.insert(10, "2nd key inserted");
		bst2.insert(30, "3rd key inserted");
		bst2.insert(15, "4th key inserted");
		bst2.insert(5, "5th key inserted");
		bst2.insert(12, "6th key inserted");
		bst2.remove(10);
		List<Integer> preOrd = new ArrayList<Integer>();
		preOrd.add(15);
		preOrd.add(12);
		preOrd.add(5);
		preOrd.add(20);
		preOrd.add(30);
		List<Integer> list = bst2.getPreOrderTraversal();
		for (int i = 0; i < 5; i++) {
			if (list.get(i) != preOrd.get(i)) {
				fail("Levelorder list should be 15-12-5-20-30, but is " + list.get(0) + "-" + list.get(1) + "-"
						+ list.get(2) + "-" + list.get(3) + "-" + list.get(4));
			}
		}
	}

	/**
	 * Checks remove method when there is a right subtree
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Test
	void testAVL_004_add_500_maintain_balance() throws IllegalNullKeyException, DuplicateKeyException {
		for (Integer i = 0; i < 500; i++) {
			avl2.insert(i, i.toString());
		}
		if (avl2.getKeyAtRoot() != 255) {
			fail("Key at root incorrect");
		}
		if (avl2.getBFTest(255) > 1 || avl2.getBFTest(255) < -1) {
		}
	}
}
