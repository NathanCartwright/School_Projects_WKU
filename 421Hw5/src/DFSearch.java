
/*
 * Nathaniel Cartwright & Devin Lee 
 * HW5 CS 421 Data Structures and Algorithms
 * 
 * This Java program is used to find the cut vertices in a given undirected graph from file if there are any. 
 * To Execute this program:
 * 1. create a text file to read. enter the numbers in terms of edges. ex: 1 2 means that 1 is connected to 2. go to the next line and keep building the graph.
 * 2. enter the name of the text file so that the scanner can read the graph. ex. sampleA.txt means enter just sampleA
 * 3. enter what vertex you would like to check in number format. ex: 4 = node 4.
 * 
 * To run our program with our test graph, ENTER "samplea" (it is not case sensitive)  and you will get the correct output according to that graph.
 * To run another graph, enter your own text file with our edge representation. or simply change our numbers in our test file.
 * 
 * This program uses linked lists to provide the adjacency matrix representation to build graph edges.
 * once the edges are build the cut vertex is determined by checking 2 cases:
 * 
 * 1. the vertex is a root and has two or more children 
 * 2. the vertex is not a root and one of its children as a low value that is less than the vertex discovery time
 * 
 */

import java.io.*;
import java.util.*;

// This class represents an undirected graph using adjacency list representation
class DFSearch {
	private int V; // Number of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];
	int time = 0;
	static final int NIL = -1;

	@SuppressWarnings("unchecked")
	// Constructor for the abstract object graph
	DFSearch(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	// Function to add an edge into the graph which takes in 2 integers which are nodes and connects them 
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		adj[w].add(v); // Add v to w's list
	}

	// findCut() is A recursive method that find cut vertices using a Depth First Search Tree
	// lowerLevel[] Stores the vertices that are lower in height in the Depth First Search Tree
	// next --> The vertex to be visited next
	// visited[] --> keeps tract of visited vertices
	// disc[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in Depth First Search tree
	// cut[] --> Store articulation points
	void findCut(int next, boolean visited[], int discovered[], int lowerLevel[], int parent[], boolean cut[]) {

		// Count of children in Depth First Search Tree
		int children = 0;

		// Mark the current node as visited
		visited[next] = true;

		// Initialize discovery time and low value
		discovered[next] = lowerLevel[next] = ++time;

		// Go through all vertices adjacent to this vertex
		Iterator<Integer> i = adj[next].iterator();
		while (i.hasNext()) { // while there is still adjacent vertices next to i
			int v = i.next(); // v is current adjacent of next 
			
			// If v is not visited yet, then make it a child of next vertex in Depth First Search tree and reiterate it
			if (!visited[v]) {
				children++;
				parent[v] = next;
				findCut(v, visited, discovered, lowerLevel, parent, cut);

				// Check if the subtree rooted with v has a connection to one of the ancestors of next
				lowerLevel[next] = Math.min(lowerLevel[next], lowerLevel[v]);

				// next is a cut vertex in following cases:

				// Case 1: next is root of DFS tree and has two or more children.
				if (parent[next] == NIL && children > 1)
					cut[next] = true;

				// Case 2: If next is not root and low value of one of its child is greater than discovery value of next vertex .
				if (parent[next] != NIL && lowerLevel[v] >= discovered[next])
					cut[next] = true;
			}

			// Update low value of u for parent function calls.
			else if (v != parent[next])
				lowerLevel[next] = Math.min(lowerLevel[next], discovered[v]);
		}
	}

	// The function to do Depth First Search traversal. It uses recursive function cutVertex() expecting an integer
	// returns nothing just contains print functions for cases.
	void cutVertex(int n) {
		
		// Mark all the vertices as not visited initially 
		boolean visited[] = new boolean[V];
		int discovered[] = new int[V];
		int child[] = new int[V];
		int parent[] = new int[V];     
		boolean cut[] = new boolean[V]; // Stores all cut vertices that will be used later in main to hand pick a select vertex

		// Initialize parent and visited, and cut(cut vertex) arrays
		// traverse each vertex set everything to default values for base case
		for (int i = 0; i < V; i++) {  
			parent[i] = NIL;	
			visited[i] = false;
			cut[i] = false;
		}

		// Call the recursive helper function to find cut vertices in Depth First Search Tree rooted with vertex 'i'
		
		for (int i = 0; i < V; i++) // traverse the entire graph
			if (visited[i] == false) // if that vertex has not been visited 
				findCut(i, visited, discovered, child, parent, cut); // check to see if there is a cut vertex

		// Output for printing cut vertices. print is done by calling the method cut[]
		if (cut[n] == true)
			System.out.println(n + 1 + " is a cut vertex"); // print the given vertex is a cut vertex

		else
			System.out.println(n + 1 + " is not a cut vertex"); // print the given vertex is not a cut vertex

		

	}

	// output is handled in this main method.
	// main method asks user for graph file, graph nodes, and which vertex they would like to check
	// calls are made to cutVertex() to receive output and addEdge() to build the graph from file.

	public static void main(String args[]) throws FileNotFoundException {

		System.out.println("Please enter just file name (no .txt) to acquire the graph: ");
		Scanner input = new Scanner(System.in);
		String filename = input.nextLine()+".txt";
		File file = new File(filename);

		// prompt for the number of nodes that the graph from file contains

		Scanner get = new Scanner(System.in);

		// prompt for the vertex the user would like to test. takes in a integer
		// which represents a given node

		System.out.print("Enter what node to check:\n ");
		int n = get.nextInt();

		DFSearch g1 = new DFSearch(filename.length());
		
		// file input output loop that checks if file exists and if not throws exception
		if (file.exists()) // check that the file exists
		{
			Scanner inFile = new Scanner(file); // create a scanner for inside the file

			while (inFile.hasNextInt()) {	// as long as the file contains an integer keep building edges

				g1.addEdge(inFile.nextInt() - 1, inFile.nextInt() - 1); // read the integers from file using two integers from file
																		

			}
			g1.cutVertex(n - 1);  // return the graph which will return if the vertex is a cut file or not
			inFile.close(); // close the scanner inside the file

		}
		get.close(); // close scanner
		input.close(); // close scanner
		
	} // main ends
}// class ends