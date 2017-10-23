import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;;

/******************************
 * 
 * @author mpelang@nus.edu.sg
 *  1. binary search
 *  2. two pointer search, however, TP is for looking all combinations of array elements which add up to a certain value
 *  , in this case, once a satisfying key is found, no further searching is needed for, so the complexity is still N^3 
 */


public class triangle_Count {
	/*****************
	 * mergesort helper function
	 */
	private void merge(int[] a, int[] aux, int lo, int mid, int hi)
	{

		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}

	}
	private static boolean less(int a,int b) {
		return a<b;
	}
	private void sort(int[] a, int[] aux, int lo, int hi)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	private void sort(int[] a)
	{
		int[] aux = new int[a.length];
		sort(a, aux, 0, a.length - 1);
	}
	/*****************
	 * Mergesort helper function ends
	 */
	

	private int binarySearch(int[] sortedArray, int key, int low, int high) {
		int middle = (low + high) / 2;
		if (high < low) {
			return middle+1;
		}
		if (key == sortedArray[middle]) {
			while(middle>low) {
				if(key == sortedArray[middle-1]) //there might be a consecutive identical values near this key, 
					middle--;					 //so we need to iterate until find the one with smallest key
				else break;
			}
			//System.out.println("middle: "+key);
			return middle;
		}

		else if (key < sortedArray[middle]) {
			return binarySearch(sortedArray, key, low, middle - 1);
		}
		else {
			return binarySearch(sortedArray, key, middle + 1, high);
		}
	}

	private int searchKey(int[] a, int key, int low, int high) {
		for(int i=high;i>=low;i--) {
			if(a[i]<key)
				return i+1;
		}
		return low;
	}

	/*********************************
	 * 
	 * @param a raw array
	 * @return number of triangles
	 * complexity: NlogN+a*N^2*logN
	 */
	public int triangleCountBS(int[] a) {
		sort(a);
		int count=0;
		int l ,r ,key;
		for(int i=a.length-1;i>=2;i--) {
			l = 0;  r=i-1; //two pointers
			while ( l < r ) {
				key=binarySearch(a,a[i]-a[l]+1,l+1,r);
				if(key!=-1)
					count+=i-key;
				l++;
			}
		}
		return count;
	}
	
	/*********************************
	 * 
	 * @param a raw array
	 * @return number of triangles
	 * complexity: NlogN+a*N^t,where 2<t<3
	 */
	private int triangleCountTP(int[] a) {
		sort(a);
		int count=0;
		int l ,r ,key;
		for(int i=a.length-1;i>=2;i--) {
			l = 0;  r=i-1; //two pointers
			while ( l < r ) {
				key=searchKey(a,a[i]-a[l]+1,l+1,r);
				if(key!=-1)
					count+=i-key;
				l++;
			}
		}	
		return count;
	}

	
	public static void main(String[] args)
	{	
		/*
		In in = new In(args[0]);
		int SIZE = in.readInt();
		int[] test=new int[SIZE];
		for(int i=0;i<SIZE;i++)
			test[i] = in.readInt();
		triangle_Count tCount=new triangle_Count();
		 */
		int SIZE=4000,CASES=10;
		triangle_Count tCount=new triangle_Count();
		int[][] test=new int[CASES][SIZE];
		for(int j=0;j<CASES;j++)
			for(int i=0;i<SIZE;i++)
				test[j][i]=StdRandom.uniform(SIZE,3*SIZE);
		
		Stopwatch stopWatch = new Stopwatch();
		for(int i=0;i<CASES;i++)
			tCount.triangleCountTP(test[i]);
		double time=stopWatch.elapsedTime();
		
		
		Stopwatch stopWatchBS = new Stopwatch();
		for(int i=0;i<CASES;i++)
			tCount.triangleCountBS(test[i]);
		double time2=stopWatchBS.elapsedTime();
		
		System.out.println("SIZE="+SIZE);
		System.out.println("Normal Search: "+time);
		System.out.println("Bianry Search: "+time2);
		
	}
}