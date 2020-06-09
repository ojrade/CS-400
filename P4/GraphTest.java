///////////////////////////////////////////////////////////
//Title:           P4 Package Manager
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        4/19/2019
//////////////////////////////////////////////////////////

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for graph.java
 * @author Ojas
 *
 */
class GraphTest {
	// TODO: add other fields that will be used by multiple tests
	Graph g;

	// TODO: add code that runs before each test method
	@BeforeEach
	public void setUp() throws Exception {
		g = new Graph();
	}

	// TODO: add code that runs after each test method
	@AfterEach
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	/**
	 * Tests add vertex
	 */
	public void test001_Add_vertex() {
		try {
			g.addVertex("1");
			if (g.order() != 1) {
				fail("Order should be 1, but it is " + g.order());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * tests add edge
	 */
	public void test002_Add_Edge() {
		try {
			g.addVertex("1");
			g.addVertex("2");
			g.addEdge("1", "2");
			if (g.size() != 1) {
				fail("Size should be 1, but it is " + g.size());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * Test remove Vertex
	 */
	public void test003_remove_vertex() {
		try {
			g.addVertex("1");
			g.removeVertex("1");
			if (g.order() != 0) {
				fail("Order should be 1, but it is " + g.order());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * tests remove edge
	 */
	public void test004_Remove_Edge() {
		try {
			g.addVertex("1");
			g.addVertex("2");
			g.addEdge("1", "2");
			g.removeEdge("1", "2");
			if (g.size() != 0) {
				fail("Size should be 1, but it is " + g.size());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * Add/Remove Vertex null param
	 */
	public void test005_AddRemoveVertexnull() {
		try {
			g.addVertex(null);
			if (g.order() != 0) {
				fail("Order should be 0, but it is " + g.order());
			}
			g.addVertex("1");
			g.removeVertex(null);
			if (g.order() != 1) {
				fail("Order should be 1, but it is " + g.order());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * Add Duplicate Vertex
	 */
	public void test006_AddDupVertex() {
		try {
			g.addVertex("1");
			g.addVertex("1");
			if (g.order() != 1) {
				fail("Order should be 1, but it is " + g.order());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * Add edge if vertex doesn't exist
	 */
	public void test007_AddEdgeWhenVertexDontExist() {
		try {
			g.addEdge("1", "2");
			if (g.size() != 1) {
				fail("Order should be 1, but it is " + g.size());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}

	@Test
	/**
	 * Remove Edge when verts DNE
	 */
	public void test008_RemoveEdgeWhenVertexDontExist() {
		try {
			g.addEdge("2", "3");
			g.removeEdge("1", "2");
			if (g.size() != 1) {
				fail("Order should be 1, but it is " + g.size());
			}
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
	}
}
