import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO: Add tests to test that binary search tree operations work

public class BSTTest extends DataStructureADTTest {

	BST<String, String> bst;
	BST<Integer, String> bst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// The setup must initialize this class's instances
		// and the super class instances.
		// Because of the inheritance between the interfaces and classes,
		// we can do this by calling createInstance() and casting to the desired type
		// and assigning that same object reference to the super-class fields.
		dataStructureInstance = bst = createInstance();
		dataStructureInstance2 = bst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = bst = null;
		dataStructureInstance2 = bst2 = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected BST<String, String> createInstance() {
		return new BST<String, String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected BST<Integer, String> createInstance2() {
		return new BST<Integer, String>();
	}

	/**
	 * Test that empty trees still produce a valid but empty traversal list for each
	 * of the four traversal orders.
	 */
	@Test
	void testBST_001_empty_traversal_orders() {
		try {

			List<String> expectedOrder = new ArrayList<String>();

			// Get the actual traversal order lists for each type
			List<String> inOrder = bst.getInOrderTraversal();
			List<String> preOrder = bst.getPreOrderTraversal();
			List<String> postOrder = bst.getPostOrderTraversal();
			List<String> levelOrder = bst.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: " + expectedOrder);
			System.out.println("   In Order: " + inOrder);
			System.out.println("  Pre Order: " + preOrder);
			System.out.println(" Post Order: " + postOrder);
			System.out.println("Level Order: " + levelOrder);

			assertEquals(expectedOrder, inOrder);
			assertEquals(expectedOrder, preOrder);
			assertEquals(expectedOrder, postOrder);
			assertEquals(expectedOrder, levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 002: " + e.getMessage());
		}

	}

	/**
	 * Test that trees with one key,value pair produce a valid traversal lists for
	 * each of the four traversal orders.
	 */
	@Test
	void testBST_002_check_traversals_after_insert_one() {

		try {

			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			bst2.insert(10, "ten");
			if (bst2.numKeys() != 1)
				fail("added 10, size should be 1, but was " + bst2.numKeys());

			List<Integer> inOrder = bst2.getInOrderTraversal();
			List<Integer> preOrder = bst2.getPreOrderTraversal();
			List<Integer> postOrder = bst2.getPostOrderTraversal();
			List<Integer> levelOrder = bst2.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: " + expectedOrder);
			System.out.println("   In Order: " + inOrder);
			System.out.println("  Pre Order: " + preOrder);
			System.out.println(" Post Order: " + postOrder);
			System.out.println("Level Order: " + levelOrder);

			assertEquals(expectedOrder, inOrder);
			assertEquals(expectedOrder, preOrder);
			assertEquals(expectedOrder, postOrder);
			assertEquals(expectedOrder, levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: " + e.getMessage());
		}

	}

	/**
	 * Test that the in-order traversal order is correct if the items are entered in
	 * a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30 In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_003_check_inOrder_for_balanced_insert_order() {
		// insert 20-10-30 BALANCED
		try {
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10); // L
			expectedOrder.add(20); // V
			expectedOrder.add(30); // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: " + e.getMessage());
		}
	}

	/**
	 * Test that the pre-order traversal order is correct if the items are entered
	 * in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_004_check_preOrder_for_balanced_insert_order() {
		try {
			//inserting in order
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//Check list
			List<Integer> preOrd = new ArrayList<Integer>();
			preOrd.add(20);
			preOrd.add(10);
			preOrd.add(30);
			List<Integer> list = bst2.getPreOrderTraversal();
			for (int i = 0; i < 3; i++) {//check if correct
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
	 * in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
	 */
	@Test
	void testBST_005_check_postOrder_for_balanced_insert_order() {
		try {
			//inserting in order
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//Check List
			List<Integer> postOrd = new ArrayList<Integer>();
			postOrd.add(10);
			postOrd.add(30);
			postOrd.add(20);
			List<Integer> list = bst2.getPostOrderTraversal();
			for (int i = 0; i < 3; i++) {//check if correct
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
	 * in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30 Level-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_006_check_levelOrder_for_balanced_insert_order() {
		try {
			//inserting in order
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//Check list
			List<Integer> levOrd = new ArrayList<Integer>();
			levOrd.add(20);
			levOrd.add(10);
			levOrd.add(30);
			List<Integer> list = bst2.getLevelOrderTraversal();
			for (int i = 0; i < 3; i++) {//check if correc
				list.get(i);
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
	 * Test that the in-order traversal order is correct if the items are entered in
	 * a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30 In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		try {
			//inserting in order
			bst2.insert(10, "1st key inserted");
			bst2.insert(20, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//check list
			List<Integer> inOrd = new ArrayList<Integer>();
			inOrd.add(10);
			inOrd.add(20);
			inOrd.add(30);
			List<Integer> list = bst2.getInOrderTraversal();
			for (int i = 0; i < 3; i++) {//check if correct
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
			//inserting in order
			bst2.insert(10, "1st key inserted");
			bst2.insert(20, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//Check list
			List<Integer> preOrd = new ArrayList<Integer>();
			preOrd.add(10);
			preOrd.add(20);
			preOrd.add(30);
			List<Integer> list = bst2.getPreOrderTraversal();
			for (int i = 0; i < 3; i++) {//check if correct
				if (list.get(i) != preOrd.get(i)) {
					fail("Preorder list should be 10-20-30, but is " + list.get(0) + "-" + list.get(1) + "-"
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
			//inserting in order
			bst2.insert(10, "1st key inserted");
			bst2.insert(20, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			List<Integer> postOrd = new ArrayList<Integer>();
			postOrd.add(30);
			postOrd.add(20);
			postOrd.add(10);
			List<Integer> list = bst2.getPostOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != postOrd.get(i)) {
					fail("Postorder list should be 30-20-10, but is " + list.get(0) + "-" + list.get(1) + "-"
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
			//inserting in order
			bst2.insert(10, "1st key inserted");
			bst2.insert(20, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			//check list
			List<Integer> levOrd = new ArrayList<Integer>();
			levOrd.add(10);
			levOrd.add(20);
			levOrd.add(30);
			List<Integer> list = bst2.getLevelOrderTraversal();
			for (int i = 0; i < 3; i++) {
				if (list.get(i) != levOrd.get(i)) {
					fail("Levelorder list should be 10-20-30, but is " + list.get(0) + "-" + list.get(1) + "-"
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
	 * Tests the remove method of BST
	 * 
	 * Insert order: 10-20-30 Pre-Order traversal order: 25-10-30-40
	 */
	@Test
	void testBST_011_check_remove_preorder() throws KeyNotFoundException {
		try {
			//inserting in order
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			bst2.insert(40, "d");
			bst2.insert(25, "d");
			bst2.remove(20);
			//check list
			List<Integer> preOrd = new ArrayList<Integer>();
			preOrd.add(25);
			preOrd.add(10);
			preOrd.add(30);
			preOrd.add(40);
			List<Integer> list = bst2.getPreOrderTraversal();
			for (int i = 0; i < 4; i++) {
				if (list.get(i) != preOrd.get(i)) {
					fail("Levelorder list should be 25-10-30-40, but is " + list.get(0) + "-" + list.get(1) + "-"
							+ list.get(2) + "-" + list.get(3));
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
	 * Tests if trying to insert a null key throws a IllegalNullKeyException
	 */
	@Test
	void testBST_012_insert_null_key_throws_exception() {
		try {
			bst2.insert(null, "hi");
			fail("IllegalNullKeyException should be thrown");
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("IllegalNullKeyException should be thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests if trying to insert a null key throws a DuplicateKeyException
	 */
	@Test
	void testBST_013_insert_duplicate_key_exception_thrown() {
		try {
			bst2.insert(2, "hi");
			bst2.insert(2, "hi");
			fail("DuplicateKeyException should be thrown");
		} catch (IllegalNullKeyException e) {
			fail("DuplicateKeyException should be thrown");
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests if removing a not added key will result in a KeyNotFoundException is
	 * thrown
	 */
	@Test
	void testBST_014_remove_key_not_found_exception_thrown() {
		try {
			bst2.insert(2, "hi");
			bst2.remove(4);
			fail("KeyNotFoundException should be thrown");
		} catch (IllegalNullKeyException e) {
			fail("KeyNotFoundException should be thrown");
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("KeyNotFoundException should be thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests if removing a not added key will result in a IllegalNullKeyException is
	 * thrown
	 */
	@Test
	void testBST_015_remove_null_key_exception_thrown() {
		try {
			bst2.insert(2, "hi");
			bst2.remove(null);
			fail("IllegalNullKeyException should be thrown");
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			fail("IllegalNullKeyException should be thrown");
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			fail("IllegalNullKeyException should be thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests functionality of get method
	 * 
	 * @throws KeyNotFoundException If key is not in tree
	 */
	@Test
	void testBST_016_test_get_value_method() throws KeyNotFoundException {
		try {
			bst2.insert(20, "1st key inserted");
			bst2.insert(10, "2nd key inserted");
			bst2.insert(30, "3rd key inserted");
			bst2.insert(40, "d");
			bst2.insert(25, "a");
			System.out.println(bst2.get(30));
			if (!bst2.get(30).equals("3rd key inserted")) {
				fail("Should return 3rd key inserted but does not");
			}
			if (!bst2.get(40).equals("d")) {
				fail("Should return d but does not");
			}
			if (!bst2.contains(30) && !bst2.contains(40)) {
				fail("get keys should still be in tree");
			}
		} catch (IllegalNullKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks if getting a null key an IllegalNullKeyException is thrown
	 * 
	 * @throws DuplicateKeyException if a duplicate key is trying to be inserted
	 */
	@Test
	void testBST_017_get_null_key_exception_thrown() throws DuplicateKeyException {
		try {
			bst2.insert(2, "hi");
			bst2.get(null);
			fail("IllegalNullKeyException should be thrown");
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			fail("IllegalNullKeyException should be thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Checks if getting a null key an KeyNotFoundException is thrown
	 * 
	 * @throws DuplicateKeyException if a duplicate key is trying to be inserted
	 */
	@Test
	void testBST_018_get_key_not_found_exception_thrown() throws DuplicateKeyException {
		try {
			bst2.insert(2, "hi");
			bst2.get(3);
			fail("KeyNotFoundException should be thrown");
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("KeyNotFoundException should be thrown");
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
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
		bst2.insert(20, "1st key inserted");
		bst2.insert(10, "2nd key inserted");
		bst2.insert(30, "3rd key inserted");
		bst.insert("10", "1st key inserted");
		bst.insert("20", "2nd key inserted");
		bst.insert("30", "3rd key inserted");
		if (bst.getHeight() != 3) {
			fail("BST height should be 3, but it is " + bst.getHeight());
		}
		if (bst2.getHeight() != 2) {
			fail("BST height should be 2, but it is " + bst.getHeight());
		}
	}

	/**
	 * Adds 500 and tests the get methode
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Test
	void testBST_020_add_500_get() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		for (Integer i = 0; i < 500; i++) {//500 nodes added to tree
			bst2.insert(i, i.toString());
		}
		if (!bst2.get(499).equals("499")) {
			fail("Did not return 499 and instead " + bst2.get(499));
		}
	}

	/**
	 * Checks remove method when there is one left child
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Test
	void testBST_021_remove_1_left_child() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		bst2.insert(20, "1st key inserted");
		bst2.insert(10, "2nd key inserted");
		bst2.insert(30, "3rd key inserted");
		bst2.insert(5, "4th key inserted");
		bst2.remove(10);
		List<Integer> preOrd = new ArrayList<Integer>();
		preOrd.add(20);
		preOrd.add(5);
		preOrd.add(30);
		List<Integer> list = bst2.getPreOrderTraversal();
		for (int i = 0; i < 3; i++) {
			if (list.get(i) != preOrd.get(i)) {
				fail("Levelorder list should be 20-5-40, but is " + list.get(0) + "-" + list.get(1) + "-" + list.get(2)
						+ "-" + list.get(3));
			}
		}
	}

	/**
	 * Checks remove method when there is one right child
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Test
	void testBST_022_remove_1_right_child()
			throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		bst2.insert(20, "1st key inserted");
		bst2.insert(10, "2nd key inserted");
		bst2.insert(30, "3rd key inserted");
		bst2.insert(15, "4th key inserted");
		bst2.remove(10);
		List<Integer> preOrd = new ArrayList<Integer>();
		preOrd.add(20);
		preOrd.add(15);
		preOrd.add(30);
		List<Integer> list = bst2.getPreOrderTraversal();
		for (int i = 0; i < 3; i++) {
			if (list.get(i) != preOrd.get(i)) {
				fail("Levelorder list should be 20-15-40, but is " + list.get(0) + "-" + list.get(1) + "-" + list.get(2)
						+ "-" + list.get(3));
			}
		}
	}

	/**
	 * Checks remove method when there is 2 children
	 * 
	 * @throws IllegalNullKeyException if a null key is trying to be inserted
	 * @throws DuplicateKeyException   if a duplicate key is trying to be inserted
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Test
	void testBST_023_remove_2_children() throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		bst2.insert(20, "1st key inserted");
		bst2.insert(10, "2nd key inserted");
		bst2.insert(30, "3rd key inserted");
		bst2.insert(15, "4th key inserted");
		bst2.insert(5, "5th key inserted");
		bst2.remove(10);
		List<Integer> preOrd = new ArrayList<Integer>();
		preOrd.add(20);
		preOrd.add(15);
		preOrd.add(5);
		preOrd.add(30);
		List<Integer> list = bst2.getPreOrderTraversal();
		for (int i = 0; i < 4; i++) {
			if (list.get(i) != preOrd.get(i)) {
				fail("Levelorder list should be 20-15-5-30, but is " + list.get(0) + "-" + list.get(1) + "-"
						+ list.get(2) + "-" + list.get(3));
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
		preOrd.add(20);
		preOrd.add(12);
		preOrd.add(5);
		preOrd.add(15);
		preOrd.add(30);
		List<Integer> list = bst2.getPreOrderTraversal();
		for (int i = 0; i < 5; i++) {
			if (list.get(i) != preOrd.get(i)) {
				fail("Preorder list should be 20-12-5-15-30, but is " + list.get(0) + "-" + list.get(1) + "-"
						+ list.get(2) + "-" + list.get(3) + "-" + list.get(4));
			}
		}
	}
}
