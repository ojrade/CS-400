///////////////////////////////////////////////////////////
//Title:           P4 Package Manager
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        4/19/2019
//////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename: Graph.java Project: p4 Authors:
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	private int numVert;
	private int numEdges;
	private Map<String, ArrayList<String>> adjListsMap;
	private ArrayList<String> verts;

	/*
	 * Default no-argument constructor
	 */
	public Graph() {
		numVert = 0;
		numEdges = 0;
		verts = new ArrayList<String>();
		adjListsMap=new HashMap<String,ArrayList<String>>();
	}

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 */
	public void addVertex(String vertex) {
		if (vertex == null) {
			return;
		}
		if (verts.contains(vertex)) {
			return;
		}
		adjListsMap.put(vertex, new ArrayList<String>());
		numVert++;
		verts.add(vertex);
	}

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 */
	public void removeVertex(String vertex) {
		if (vertex == null) {
			return;
		}
		if (!verts.contains(vertex)) {
			return;
		}
		adjListsMap.remove(vertex);
		numVert--;
		verts.remove(vertex); 
	}

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted) If either vertex does not exist, no edge is added and no
	 * exception is thrown. If the edge exists in the graph, no edge is added and no
	 * exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		if(vertex1==null || vertex2==null) {
			return;
		}
		if(!verts.contains(vertex1)) {
			addVertex(vertex1);
		}
		if(!verts.contains(vertex2)){
			addVertex(vertex2);
		}
		if(contEdge(vertex1, vertex2)) {
			return;
		}
		if(vertex1.equals(vertex2)) {
			return;
		}
		adjListsMap.get(vertex1).add(vertex2);
		numEdges++;
	}

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
	 * and unweighted) If either vertex does not exist, or if an edge from vertex1
	 * to vertex2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge from vertex1 to vertex2 is in the graph
	 */
	public void removeEdge(String vertex1, String vertex2) {
		if(vertex1==null || vertex2==null) {
			return;
		}
		if(!verts.contains(vertex1) || !verts.contains(vertex2)) {
			return;
		}
		if(!contEdge(vertex1,vertex2)) {
			return;
		}
		adjListsMap.get(vertex1).remove(vertex2);
		numEdges--;
	}

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 */
	public Set<String> getAllVertices() {
		Set<String> temp=new HashSet<String>();
		for(String x:verts){
			temp.add(x);
		}
		return temp;
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		return adjListsMap.get(vertex);
	}

	/**
	 * Returns the number of edges in this graph.
	 */
	public int size() {
		return numEdges;
	}

	/**
	 * Returns the number of vertices in this graph.
	 */
	public int order() {
		return numVert;
	}
	
	/**
	 * Returns true if edge already exists
	 */
	private boolean contEdge(String v1, String v2) {
		ArrayList<String> temp=adjListsMap.get(v1);
		if(temp.contains(v2)) {
			return true;
		}
		return false;
	}
}
