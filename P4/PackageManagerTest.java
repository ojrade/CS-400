///////////////////////////////////////////////////////////
//Title:           P4 Package Manager
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        4/19/2019
//////////////////////////////////////////////////////////

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for PackageManager.java
 * @author Ojas
 *
 */
class PackageManagerTest {
	// TODO: add other fields that will be used by multiple tests
	PackageManager p;

	// TODO: add code that runs before each test method
	@BeforeEach
	public void setUp() throws Exception {
		p = new PackageManager();
	}

	// TODO: add code that runs after each test method
	@AfterEach
	public void tearDown() throws Exception {
		p = null;
	}

	@Test
	/**
	 * Loads a json file into package manager
	 */
	void test1_LoadJson() {
		try {
			p.constructGraph("valid.json");
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	/**
	 * Checks if getAllPackages functions
	 */
	public void test2_ListAllPackages() {
		try {
			p.constructGraph("valid.json");
			Set<String> s = p.getAllPackages();
			if (s.size() != 5) {
				fail("Size should be 5, but is " + s.size());
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	/**
	 * Tests get installation order
	 */
	public void test3_GetInstallationOrder() {
		try {
			p.constructGraph("valid.json");
			List<String> s = p.getInstallationOrder("A");
			ArrayList<String> test = new ArrayList<String>();
			test.add("C");
			test.add("D");
			test.add("B");
			test.add("A");
			if (s.size() != 4) {
				fail("Size should be 4, but is " + s.size());
			}
			int i = 0;
			for (String p : s) {
				if (!p.equals(test.get(i))) {
					fail("Should be [C,D,B,A], but is " + s.toString());
				}
				i++;
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	/**
	 * Tests getInstallationOrderForAllPackages
	 */
	public void test4_GetInstallationOrderForAllPackages() {
		try {
			p.constructGraph("valid.json");
			List<String> s = p.getInstallationOrderForAllPackages();
			ArrayList<String> test = new ArrayList<String>();
			test.add("D");
			test.add("C");
			test.add("B");
			test.add("E");
			test.add("A");
			if (s.size() != 5) {
				fail("Size should be 4, but is " + s.size());
			}
			int i = 0;
			for (String p : s) {
				if (!p.equals(test.get(i))) {
					fail("Should be [D,C,B,E,A], but is " + s.toString());
				}
				i++;
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	/**
	 * Get Package with max dependencies
	 */
	public void test5_GetPackageMaxDependencies() {
		try {
			p.constructGraph("valid.json");
			String s = p.getPackageWithMaxDependencies();
			if (!s.equals("A")) {
				fail("Should be A, but is " + s);
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	/**
	 * Checks package not found exception occurs
	 */
	public void test6_PackageNotFoundExceptionOccurs() {
		try {
			p.constructGraph("valid.json");
			p.getInstallationOrder("F");
			fail("Should throw packageNotFound");
		} catch (PackageNotFoundException e) {

		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	/**
	 * Tests getInstallationOrder throws cycleException
	 */
	public void test7_CycleExceptionInstallationOrder() {
		try {
			p.constructGraph("cyclic.json");
			List<String> s=p.getInstallationOrder("A");
			for(String x:s) {
				System.out.println(x);
			}
			fail("Should throw cyclicException");
		} catch (CycleException e) {

		} catch (Exception e) {
			System.out.println(e.toString());
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	/**
	 * Tests getInstallationOrderForALl throws cycleException
	 */
	public void test8_CycleExceptionInstallationOrderForAll() {
		try {
			p.constructGraph("cyclic.json");
			List<String> s=p.getInstallationOrderForAllPackages();
			fail("Should throw cyclicException");
		} catch (CycleException e) {

		} catch (Exception e) {
			System.out.println(e.toString());
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	/**
	 * Tests toInstall
	 */
	public void test9_ToInstall() {
		try {
			p.constructGraph("valid.json");
			List<String> s=p.toInstall("A","C");
			if(s.size()!=3) {
				fail("Should be 3, but is "+s.size());
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
	
	@Test
	/**
	 * Shared Dependencies installation order
	 */
	public void test10_SharedDependenciesIO() {
		try {
			p.constructGraph("shared_dependencies.json");
			List<String> s = p.getInstallationOrder("A");
			ArrayList<String> test = new ArrayList<String>();
			test.add("D");
			test.add("B");
			test.add("C");
			test.add("A");
			if (s.size() != 4) {
				fail("Size should be 4, but is " + s.size());
			}
			int i = 0;
			for (String p : s) {
				if (!p.equals(test.get(i))) {
					fail("Should be [D,C,B,A], but is " + s.toString());
				}
				i++;
			}
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
}
