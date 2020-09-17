import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides data fields and methods to simulate image processing to create and
 * explore data using a graph structure.
 * 
 * @author N. Raeesinejad
 * @since April 7, 2020
 */
public class CPSC319W20A4 {

	/**
	 * size is the size of the neighborhoods.
	 * i.e. the number of rows and columns as specified in the output file.
	 */
	private static int size;
	
	/**
	 * count is the number of vertices in the graph.
	 */
	private static int count;
	
	/**
	 * adjMat is the adjacency matrix representation of the data given in the 
	 * input file.
	 */
	private static int [][] adjMat;
	
	/**
	 * vertices is the matrix representation of every neighborhood given in the
	 * input file.
	 */
	private static ArrayList <int[][]> vertices;
	
	/**
	 * visited is an array to keep track of vertices visited during Depth First
	 * Traversal of the tree.
	 */
	private static int[] visited; 
	
	/**
	 * Reads the input file and stores its data inside vertices.
	 */
	private static void readFile() {
		Scanner scan = new Scanner(System.in);
		size = scan.nextInt();
		scan.nextLine();
		count = scan.nextInt();
		scan.nextLine();
		vertices = new ArrayList<int[][]>(count);
		
		while (scan.hasNextLine()) {
			scan.nextLine();
			int [][] vertex = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					vertex[i][j] = scan.nextInt();
				}
				if (scan.hasNextLine())
					scan.nextLine();
			}
			vertices.add(vertex);	
		}
	}

	/**
	 * Constructs the adjacency matrix adjMat and populates it with the weight
	 * of each edge by calling method calcWeight. 
	 */
	private static void createAdjMat() {
		adjMat = new int [count][count];
		
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = i+1; j < vertices.size(); j++) {
				addEdge(i, j, calcWeight(vertices.get(i), vertices.get(j)));
			}
		}	
	}
	
	/**
	 * Returns the weight of an edge of adjMat by calculating the absolute value
	 * of the difference between the 2 vertices (neighborhoods) supplied by the
	 * given parameters.
	 * 
	 * @param v1 is the first vertex (neighborhood).
	 * @param v2 is the second vertex (neighborhood).
	 * @return weight the difference between v1 and v2.
	 */
	private static int calcWeight(int[][] v1, int[][] v2) {
		int weight = 0;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				weight += Math.abs(v1[i][j] - v2[i][j]);
			}
		}
		return weight;
	}
	
	/**
	 * Adds an edge between 2 vertices supplied by the given parameters to the 
	 * adjacency matrix adjMat.
	 * 
	 * @param v1 is the first vertex (neighborhood).
	 * @param v2 is the second vertex (neighborhood).
	 * @param w is the weight of the edge between v1 and v2.
	 */
	private static void addEdge(int v1, int v2, int w) {
		adjMat[v1][v2] = w;
		adjMat[v2][v1] = w;
	}
	
	/**
	 * Creates and appends a string representation of adjMat to send to the 
	 * method createFile with a specified file name.
	 */
	private static void writeGraphFile() {
		String graph = "Edge	Weight\n";
		
		for (int i = 0; i < count; i++) {
			for (int j = i+1; j < count; j++) {
				graph += i + " - " + j + "\t" + adjMat[i][j] + "\n";
			}
		}
		createFile("NxN_GRAPH.txt", graph);
	}
	
	/**
	 * Creates and appends a string representation of a Depth First Traversal of
	 * the tree to send to the method createFile with a specified file name.
	 */
	private static void writeDFT() {
		visited = new int[count];
		for (int i = 0; i < count; i++)
			visited[i] = 0;
		DFT(0);
		String DFT = "Edge	Weight\n";
		
		for (int i = 0; i < count-1; i++) {
			int j = i + 1;
			DFT += i + " - " + j + "\t" + visited[i] + "\n";
		}
		
		createFile("NxN_DFT.txt", DFT);
	}
	
	/**
	 * Performs a Depth First Traversal of the tree and stores each visited 
	 * vertex in the array visited.
	 * @param i is the node index in the tree.
	 */
	private static void DFT(int i) {
		int j;
		visited[i] = 1;
		
		for (j = 0; j < count; j++) {
			if ((visited[j] == 0) && adjMat[i][j] > 0) {
				visited[i] = adjMat[i][j];
				DFT(j);
			}
		}
	}
	
	/**
	 * Creates and appends a string representation of the Minimum Spanning Tree 
	 * from the graph adjMat to send to the method createFile with a specified
	 * file name.
	 */
	private static void writeMST() {
		int parent[] = createMST();
		String MST = "Edge	Weight\n";
		for (int i = 1; i < count; i++) {
			MST += parent[i] + " - " + i + "\t" + adjMat[i][parent[i]] + "\n";
		}
		
		createFile("NxN_MST.txt", MST);
	}
	
	/**
	 * Produces a Minimum Spanning Tree MST from adjMat using Prim's Algorithm.
	 * @return the parent node of the graph adjMat.
	 */
	private static int[] createMST() {
		int parent[] = new int[count];
		int key[] = new int[count];
		boolean MST[] = new boolean[count];
		
		for (int i = 0; i < count; i++) {
			key[i] = Integer.MAX_VALUE;
			MST[i] = false;
		}
		
		key[0] = 0;
		parent[0] = -1;
		
		for (int i = 0; i < count-1; i++) {
			int minVertex = findMinKey(key, MST);
			MST[minVertex] = true;
			
			for (int j = 0; j < count; j++) {
				if (adjMat[minVertex][j] != 0 && MST[j] == false && adjMat[minVertex][j] < key[j]) {
					parent[j] = minVertex;
					key[j] = adjMat[minVertex][j];
				}
			}
		}
		return parent;
	}
	
	/**
	 * Serves as a utility method to find the vertex containing the minimum key
	 * value, from the set of vertices not yet existing in the MST.
	 * @param key is the array of minimum key values.
	 * @param MST is the Minimum Spanning Tree as a subset of the graph adjMat.
	 * @return minIndex is the index with the minimum key value.
	 */
	private static int findMinKey(int key[], boolean MST[]) {
		int min = Integer.MAX_VALUE, minIndex = -1;
		
		for (int i = 0; i < count; i++) {
			if (MST[i] == false && key[i] < min) {
				min = key[i];
				minIndex = i;
			}
		}
		
		return minIndex;
	}
	
	/**
	 * Creates and writes into an output text file with a name and content 
	 * supplied by the given parameters.
	 * @param filename is the name of the output text file.
	 * @param content is the content of the output text file.
	 */
	private static void createFile(String filename, String content) {
		try {
			filename = filename.replace('N', (char)(size + '0'));
			File graphFile = new File (filename);
			
			if (!graphFile.createNewFile())
				System.out.println("File already exists.");
			
			FileWriter graphFileWriter = new FileWriter(filename);
			graphFileWriter.write(content);
			graphFileWriter.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		readFile();
		createAdjMat();
		writeGraphFile();
		writeDFT();
		writeMST();
	}
}