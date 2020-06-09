import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

///////////////////////////////////////////////////////////
//Title:           p1 Implement and Test DataStructure.adt
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        2/7/2019
//////////////////////////////////////////////////////////
/**
 * Abstract class that tests the functionality of any class which implements
 * DataStructureADT
 * 
 * @author Ojas
 *
 * @param <T>
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	private T dataStructureInstance;

	protected abstract T createInstance();

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	/**
	 * Checks to make sure the data structure starts empty
	 */
	@Test
	void test00_empty_dataStructureInstance_size() {
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	/**
	 * Checks to make sure after 1 insert the size is 1
	 */
	@Test
	void test01_after_insert_one_size_is_one() {
		try {
			dataStructureInstance.insert("hi", "bye");
			if (dataStructureInstance.size() != 1) {
				fail("data structure should be empty, with size=1, but size=" + dataStructureInstance.size());
			}
		} catch (RuntimeException e) {

		}
	}

	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		try {
			dataStructureInstance.insert("hello", "bye");
			dataStructureInstance.remove("hello");
			if (dataStructureInstance.size() != 0) {
				fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Makes sure that an exception is thrown when a duplicate key is inserted
	 */
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert("hi", "bye");
			dataStructureInstance.insert("hi", "bye");
			fail("Runtime error should be thrown, but it is not");
		} catch (RuntimeException e) {

		}
	}

	/**
	 * When a key is not in the data structure, false is returned when remove is
	 * called
	 */
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		try {
			dataStructureInstance.insert("hi", "bye");
			dataStructureInstance.insert("hello", "bye");
			if (dataStructureInstance.remove("bob")) {
				fail("False should be returned but it is not");
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Makes sure remove functions properly when multiple inserts have been done
	 */
	@Test
	void test05_after_four_inserts_remove_first_size_is_3() {
		try {
			dataStructureInstance.insert("hi", "bye");
			dataStructureInstance.insert("hello", "bye");
			dataStructureInstance.insert("h1", "bye");
			dataStructureInstance.insert("hello1", "bye");
			dataStructureInstance.remove("hi");
			if (dataStructureInstance.size() != 3) {
				fail("data structure should be empty, with size=3, but size=" + dataStructureInstance.size());
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Can insert a duplicate key, if the originally inserted node has already been
	 * removed
	 */
	@Test
	void test06_insert_remove_insert_same_key_does_not_throw_exception() {
		try {

			dataStructureInstance.insert("hi", "bye");
			dataStructureInstance.remove("hi");
			dataStructureInstance.insert("hi", "bye");
		} catch (RuntimeException e) {
			fail("Runtime exception was thrown when it shouldn't have been");
		}
	}

	/**
	 * Tests the functionality of the contains function
	 */
	@Test
	void test07_contains_test() {
		try {
			dataStructureInstance.insert("hi", "bye");
			dataStructureInstance.insert("hello", "bye");
			dataStructureInstance.insert("h1", "bye");
			dataStructureInstance.insert("hello1", "bye");
			if (!dataStructureInstance.contains("hi")) {
				fail("True should be returned, but it is not");
			}
			dataStructureInstance.remove("hi");
			if (dataStructureInstance.contains("hi")) {
				fail("False should be returned, but it is not");
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Makes sure that when a null key is inserted, an exception is thrown
	 */
	@Test
	void test08_insert_null_key_exception_thrown() {
		try {
			dataStructureInstance.insert(null, "test");
			fail("IllegalArgumentException should be thrown, but it is not");
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * When a null value is inserted, no exception is thrown
	 */
	@Test
	void test09_insert_null_value_no_exception_thrown() {
		try {
			dataStructureInstance.insert("HELLO", null);
		} catch (RuntimeException e) {
			fail("Exception is thrown when it shouldn't");
		}
	}

	/**
	 * Checks contains correct
	 */
	@Test
	void test10_after_insert_contains() {
		try {
			dataStructureInstance.insert("HELLO", "bye");
			if (!dataStructureInstance.contains("HELLO")) {
				fail("Should return true, but returns false");
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * If something is removed, contains returns false
	 */
	@Test
	void test11_after_insert_and_remove_not_contains() {
		try {
			dataStructureInstance.insert("HELLO", "bye");
			dataStructureInstance.remove("HELLO");
			if (dataStructureInstance.contains("HELLO")) {
				fail("Should return true, but returns false");
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Checks if remove throws an exception when a null key is passed
	 */
	@Test
	void test12_remove_null_key_exception() {
		try {

			dataStructureInstance.insert("HELLO", "bye");
			dataStructureInstance.remove(null);
			fail("No exception thrown when it should have been");
		} catch (IllegalArgumentException e) {

		} catch (RuntimeException e) {

		}
	}

	/**
	 * Checks the functionality of get
	 */
	@Test
	void test13_get_returns_proper_value() {
		try {
			dataStructureInstance.insert("HELLO", "bye1");
			dataStructureInstance.insert("hi", "bye2");
			dataStructureInstance.insert("hello", "bye3");
			dataStructureInstance.insert("h1", "bye4");
			if (dataStructureInstance.contains("HELLO")) {
				if (!dataStructureInstance.get("HELLO").equals("bye1")) {
					fail("Should return true, but returned false");
				}
			}
			dataStructureInstance.remove("HELLO");
			if (dataStructureInstance.contains("HELLO")) {
				if (dataStructureInstance.get("HELLO").equals("bye1")) {
					fail("Should return false, but returned true");
				}
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Checks that get throws an exception when a null key is passed
	 */
	@Test
	void test14_get_null_key_throws_exception() {
		try {

			dataStructureInstance.insert("HELLO", "bye1");
			dataStructureInstance.get(null);
			fail("Should throw IllegalArgumentException ");
		} catch (IllegalArgumentException e) {

		} catch (RuntimeException e) {

		}
	}

	/**
	 * Checks contains throws exception when null key is passed
	 */
	@Test
	void test15_contains_null_key_returns_false() {
		try {
			dataStructureInstance.insert("HELLO", "bye1");
			if (dataStructureInstance.contains(null)) {
				fail("Should return false, but returns true");
			}
		} catch (RuntimeException e) {

		}
	}

	/**
	 * Checks that data structure can add and remove 500 items
	 */
	@Test
	void test16_500_items_can_be_added_and_removed() {
		try {
			for (int i = 0; i < 500; i++) {
				dataStructureInstance.insert(Integer.toString(i), "hi");
			}
			if (dataStructureInstance.size() != 500) {
				fail("500 units should be the size but it is actually " + dataStructureInstance.size());
			}
			for (int i = 0; i < 500; i++) {
				dataStructureInstance.remove(Integer.toString(i));
			}
			if (dataStructureInstance.size() != 0) {
				fail("0 units should be the size but it is actually " + dataStructureInstance.size());
			}
		} catch (RuntimeException e) {

		}
	}
}
