/**
 * Provides data fields and methods to create a Java data-type, representing a 
 * Fibonacci number in a Java application.
 * Each class method implements a different algorithm towards calculating the nth
 * Fibonnaci number.
 * 
 * @author Niyousha Raeesinejad
 * @version 1.0
 * @since January 31, 2020
 */

public class Fib {
	
	/**
	 * Computes the nth Fibonacci number using recursion.
	 * @param n is the index in the Fibonacci series
	 * @return the nth Fibonacci number
	 */
	public static long fibRec(int n) {
		if (n == 0) // Base case
			return 0;
		else if (n == 1) // Base case
			return 1;
		else
			return fibRec(n-1) + fibRec(n-2);
	}
	
	/**
	 * Calls fibMemCache to retrieve the nth Fibonacci number.
	 * @param n is the index in the Fibonacci series 
	 * @return result is the nth Fibonacci number
	 */	
	public static long fibMem(int n) {
		// Create array to contain previous recursive calls
		long [] cache = new long [n+1]; 
		return fibMemCache(n, cache);
	}
	
	/**
	 * Computes the nth Fibonacci number using memoization.
	 * @param n is the index in the Fibonacci series
	 * @param cache is the array containing the results of previous recursive
	 * 		  calls
	 * @return result is the nth Fibonacci number
	 */
	private static long fibMemCache(int n, long[] cache) {
		long result;
	
		if (n == 0) // Base case
			return 0;
		else if (n == 1) // Base case
			return 1;
		else if (cache[n] != 0) // checks if index n of cache has been stored
			return cache[n]; // returns previous recursive call for term n
		else { 
			// otherwise store new recursive call in cache 
			result = fibMemCache(n-1, cache) + fibMemCache(n-2, cache);
			cache[n] = result;
		}
		return result;
	}
	
	/**
	 * Computes the nth Fibonacci number using dynamic programming.
	 * @param n is the index in the Fibonacci series 
	 * @return result is the nth Fibonacci number
	 */
	public static long fibDyn(int n) {
		// Create array to store answers
		long [] arr = new long[n+2];
		// Store base case numbers
		arr[0] = 0;
		arr[1] = 1;
		
		// Store the sum of the previous 2 terms in the next index
		for (int i = 2; i < n+1; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		return arr[n];
	}
	
	/**
	 * Computes the nth Fibonacci number using iteration.
	 * @param n is the index in the Fibonacci series 
	 * @return result is the nth Fibonacci number
	 */
	public static long fibIter(int n) {
		long prevF = 0; // Base case
		long currF = 1; // Base case
		long result = 0;
		
		for (int i = 2; i <= n; i++) {
			result = prevF + currF; // compute sum of previous 2 terms
			// keep track of next two terms to add
			prevF = currF; 
			currF = result;
		}
		return result;
	}
	
	/**
	 * Computes the nth Fibonacci number by calling matPow.
	 * @param n is the index in the Fibonacci series 
	 * @return result is the nth Fibonacci number
	 */
	public static long fibMat(int n) {
		if (n == 0) // Base case
			return 0;
		
		long[][] FM = {{1, 1}, {1, 0}};	// initialize FM	
		matPow(n-1, FM);

		return FM[0][0];	
	}
	
	/**
	 * Computes matrix multiplication. 
	 * @param n is the index in the Fibonacci series 
	 * @return result is the nth Fibonacci number
	 */
	private static void matPow(int n, long[][] FM) {

		if (n > 1) {
			matPow(n/2, FM);
			
			// Matrix multiplication 
			long a = FM[0][0]*FM[0][0] + FM[0][1]*FM[1][0];
			long b = FM[0][0]*FM[0][1] + FM[0][1]*FM[1][1];
			long c = FM[1][0]*FM[0][0] + FM[1][1]*FM[1][0];
			long d = FM[1][0]*FM[0][1] + FM[1][1]*FM[1][1];

			FM[0][0] = a;
			FM[0][1] = b;
			FM[1][0] = c;
			FM[1][1] = d;

			if (n%2 == 1) {	// if n is odd
				// Update FM
				a = FM[0][0]*1 + FM[0][1]*1;
				b = FM[0][0]*1 + FM[0][1]*0;
				c = FM[1][0]*1 + FM[1][1]*1;
				d = FM[1][0]*1 + FM[1][1]*0;
				
				FM[0][0] = a;
				FM[0][1] = b;
				FM[1][0] = c;
				FM[1][1] = d;
			}	
		}
		return;
	}
	
	public static void main(String [] args) {
		// Testing fibRec
		System.out.println("Testing algorithm 1 to compute the Fibonacci series up to the 40th term:\n");
		
		for (int i = 0; i < 41; i++) {
			long startTime = System.nanoTime();
			fibRec(i);
			long endTime = System.nanoTime();
			long elapsed = endTime - startTime;
			System.out.println(elapsed+" ns to compute F"+i+"="+ fibRec(i)+ " with alg. 1");
			
		}
		
		// Testing fibMem
		System.out.print("\n\nTesting algorithm 2 to compute the Fibonacci series up to the 25000th term:\n\n");
		
		int i = 0;
		while (i < 25000) {
			int avgTime = 0;
				for (int j = 0; j < 10; j++) {
					long startTime = System.nanoTime();
					fibMem(i);
					long endTime = System.nanoTime();
					long elapsed = endTime - startTime;
					avgTime += elapsed;
				}
				avgTime /= 10;
			System.out.println(avgTime+" ns to compute F"+i+"="+fibMem(i)+" with alg. 2");	
			i += 100;
		}
		
		// Testing fibDyn
		System.out.print("\n\nTesting algorithm 3 to compute the Fibonacci series up to the 1000000th term:\n\n");
		
		i = 0;
		while (i < 1000000) {
			int avgTime = 0;
			for (int j = 0; j < 10; j++) {
				long startTime = System.nanoTime();
				fibDyn(i);
				long endTime = System.nanoTime();
				long elapsed = endTime - startTime;
				avgTime += elapsed;
			}
			avgTime /= 10;
			System.out.println(avgTime+" ns to compute F"+i+"="+fibDyn(i)+" with alg. 3");
			i += 10000;
		}

		// Testing fibIter
		System.out.print("\n\nTesting algorithm 4 to compute the Fibonacci series up to the 2000000th term:\n\n");
		
		i = 0;
		while (i < 2000000) {
			int avgTime = 0;
			for (int j = 0; j < 10; j++) {
				long startTime = System.nanoTime();
				fibIter(i);
				long endTime = System.nanoTime();
				long elapsed = endTime - startTime;
				avgTime += elapsed;
			}
			avgTime /= 10;
			System.out.println(avgTime+" ns to compute F"+i+"="+fibIter(i)+" with alg. 4");
			i += 20000;
		}
		
		// Testing fibMat
		System.out.print("\n\nTesting algorithm 5 to compute the Fibonacci series up to the 100000000th term:\n\n");
		
		i = 0;
		while (i < 100000000) {
			int avgTime = 0;
			for (int j = 0; j < 10; j++) {
				long startTime = System.nanoTime();
				fibMat(i);
				long endTime = System.nanoTime();
				long elapsed = endTime - startTime;
				avgTime += elapsed;
			}
			avgTime /= 10;
			System.out.println(avgTime+" ns to compute F"+i+"="+fibMat(i)+" with alg. 5");
			i += 1000000;
		}
	}
}
