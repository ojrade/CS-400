///////////////////////////////////////////////////////////
//Title:           P4 Package Manager
//Author:          Ojas Rade
//Email:           rade@wisc.edu
//Lecturer's Name: Deb Deppeler
//Due Date:        4/19/2019
//////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Filename: PackageManager.java Project: p4 Authors: Ojas
 * 
 * PackageManager is used to process json package dependency files and provide
 * function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json
 * file.
 * 
 * Package dependencies are important when building software, as you must
 * install packages in an order such that each package is installed after all of
 * the packages that it depends on have been installed.
 * 
 * For example: package A depends upon package B, then package B must be
 * installed before package A.
 * 
 * This program will read package information and provide information about the
 * packages that must be installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test
 * classes.
 */

public class PackageManager {

	private Graph graph;// Package points to dependencies
	private Graph graph2;// Dependencies point to package

	/*
	 * Package Manager default no-argument constructor.
	 */
	public PackageManager() {
		graph = new Graph();
		graph2 = new Graph();
	}

	/**
	 * Takes in a file path for a json file and builds the package dependency graph
	 * from it.
	 * 
	 * @param jsonFilepath the name of json data file with package dependency
	 *                     information
	 * @throws FileNotFoundException if file path is incorrect
	 * @throws IOException           if the give file cannot be read
	 * @throws ParseException        if the given json cannot be parsed
	 */
	public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
		Object o = new JSONParser().parse(new FileReader(jsonFilepath));
		JSONObject jo = (JSONObject) o;
		JSONArray packages = new JSONArray();
		packages = (JSONArray) jo.get("packages");//array of packages
		ArrayList<String> P = new ArrayList<String>();
		Iterator itr1 = packages.iterator();
		Iterator<Map.Entry> itr2;
		int ctr = 0;
		while (itr1.hasNext()) {
			itr2 = ((Map) itr1.next()).entrySet().iterator();
			ctr = 0;
			while (itr2.hasNext()) {//Next key value pair
				Map.Entry pair = itr2.next();//store key value pair
				if (ctr == 0) {
					P.add((String) pair.getValue());//Package into array
				} else {//put dependencies into array
					Iterator d = ((ArrayList) pair.getValue()).iterator();
					while (d.hasNext()) {
						P.add((String) d.next());
					}
				}
				ctr++;
			}
			graph.addVertex(P.get(0));
			for (int i = 1; i < P.size(); i++) {//Add edges to graph
				graph.addEdge(P.get(0), P.get(i));
				graph2.addEdge(P.get(i), P.get(0));
			}
			P.clear();
		}
	}

	/**
	 * Helper method to get all packages in the graph.
	 * 
	 * @return Set<String> of all the packages
	 */
	public Set<String> getAllPackages() {
		return graph.getAllVertices();
	}

	/**
	 * Given a package name, returns a list of packages in a valid installation
	 * order.
	 * 
	 * Valid installation order means that each package is listed before any
	 * packages that depend upon that package.
	 * 
	 * @return List<String>, order in which the packages have to be installed
	 * 
	 * @throws CycleException           if you encounter a cycle in the graph while
	 *                                  finding the installation order for a
	 *                                  particular package. Tip: Cycles in some
	 *                                  other part of the graph that do not affect
	 *                                  the installation order for the specified
	 *                                  package, should not throw this exception.
	 * 
	 * @throws PackageNotFoundException if the package passed does not exist in the
	 *                                  dependency graph.
	 */
	public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
		List<String> retIO = new ArrayList<String>();
		List<String> IO = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		Set<String> verts = graph.getAllVertices();
		if (!verts.contains(pkg)) {
			throw new PackageNotFoundException();
		}
		getIOHelper(pkg, retIO, visited, false);
		retIO.add(0, pkg);
		for (String s : retIO) {
			IO.add(0, s);
		}
		return IO;
	}

	/**
	 * Helper method for Installation order
	 * 
	 * @param v       String of which installation order is to found
	 * @param retIO   Reversed installation order
	 * @param visited which packages have been visited
	 * @param cycle   if a cycle has possibly happened
	 * @throws CycleException
	 */
	private void getIOHelper(String v, List<String> retIO, ArrayList<String> visited, boolean cycle)
			throws CycleException {
		List<String> adj = graph.getAdjacentVerticesOf(v);
		visited.add(v);
		for (String s : adj) {
			if (cycle) {//if possible cycle
				if (graph.getAdjacentVerticesOf(s).contains(v) || adj.size() != 1) {
					throw new CycleException();
				} else {
					cycle = false;
				}
			} else {
				if (visited.contains(s)) {
					cycle = true;
					getIOHelper(s, retIO, visited, cycle);
				} else {
					getIOHelper(s, retIO, visited, cycle);
					retIO.add(0, s);
				}
			}

		}

	}

	/**
	 * Given two packages - one to be installed and the other installed, return a
	 * List of the packages that need to be newly installed.
	 * 
	 * For example, refer to shared_dependecies.json - toInstall("A","B") If package
	 * A needs to be installed and packageB is already installed, return the list
	 * ["A", "C"] since D will have been installed when B was previously installed.
	 * 
	 * @return List<String>, packages that need to be newly installed.
	 * 
	 * @throws CycleException           if you encounter a cycle in the graph while
	 *                                  finding the dependencies of the given
	 *                                  packages. If there is a cycle in some other
	 *                                  part of the graph that doesn't affect the
	 *                                  parsing of these dependencies, cycle
	 *                                  exception should not be thrown.
	 * 
	 * @throws PackageNotFoundException if any of the packages passed do not exist
	 *                                  in the dependency graph.
	 */
	public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
		List<String> IONew = getInstallationOrder(newPkg);
		List<String> IOIns = getInstallationOrder(installedPkg);
		for (String s : IOIns) {
			if (IONew.contains(s)) {
				IONew.remove(s);
			}
		}
		return IONew;
	}

	/**
	 * Return a valid global installation order of all the packages in the
	 * dependency graph.
	 * 
	 * assumes: no package has been installed and you are required to install all
	 * the packages
	 * 
	 * returns a valid installation order that will not violate any dependencies
	 * 
	 * @return List<String>, order in which all the packages have to be installed
	 * @throws CycleException           if you encounter a cycle in the graph
	 * @throws PackageNotFoundException
	 */
	public List<String> getInstallationOrderForAllPackages() throws CycleException, PackageNotFoundException {
		Set<String> vertex = graph2.getAllVertices();
		String[] verts = new String[vertex.size()];
		vertex.toArray(verts);
		ArrayList<String> visited = new ArrayList<String>();
		List<String> IO = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < vertex.size(); i++) {
			if (!visited.contains(verts[i])) {
				topSortHelper(verts[i], visited, stack);
			}
		}
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			IO.add(stack.pop());
		}
		return IO;
	}

	private void topSortHelper(String i, ArrayList<String> visited, Stack<String> stack)
			throws CycleException, PackageNotFoundException {
		visited.add(i);
		String s;

		// recur
		Iterator<String> itr = graph2.getAdjacentVerticesOf(i).iterator();
		while (itr.hasNext()) {
			s = itr.next();
			if (!visited.contains(s)) {
				topSortHelper(s, visited, stack);
			} else {
				getInstallationOrder(s);
			}
		}
		stack.push(i);
	}

	/**
	 * Find and return the name of the package with the maximum number of
	 * dependencies.
	 * 
	 * Tip: it's not just the number of dependencies given in the json file. The
	 * number of dependencies includes the dependencies of its dependencies. But, if
	 * a package is listed in multiple places, it is only counted once.
	 * 
	 * Example: if A depends on B and C, and B depends on C, and C depends on D.
	 * Then, A has 3 dependencies - B,C and D.
	 * 
	 * @return String, name of the package with most dependencies.
	 * @throws CycleException           if you encounter a cycle in the graph
	 * @throws PackageNotFoundException
	 */
	public String getPackageWithMaxDependencies() throws CycleException, PackageNotFoundException {
		Set<String> packages = graph.getAllVertices();
		Iterator<String> itr = packages.iterator();
		int i;
		String pkg = "";
		String temp = "";
		if (itr.hasNext()) {
			pkg = itr.next();
			i = getInstallationOrder(pkg).size();
			while (itr.hasNext()) {
				temp = itr.next();
				if (getInstallationOrder(temp).size() > i) {
					pkg = temp;
					i = getInstallationOrder(temp).size();
				}
			}
		}
		return pkg;
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException, CycleException, PackageNotFoundException {
		System.out.println("PackageManager.main()");
		PackageManager p = new PackageManager();
		p.constructGraph("valid.json");
		Set<String> v = p.getAllPackages();
		List<String> x = p.getInstallationOrderForAllPackages();
		System.out.println("MAIN");
		for (String s : x) {
			System.out.print(s + ", ");
		}
		System.out.println();
	}

}
