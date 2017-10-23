




/**************************************************************************
 *  String Permutation
 *  complexity: NlogN+c*N
 *  
 *  if we permute one of the string and compare the permuted strings to another,
 *  the permutation costs o(n!), in which, n! is the time for getting all permuted strings 
 ***************************************************************************/
 

public class String_Permutation{

	private static boolean less(char a,char b) {
		return a<b;
	}

	private static void merge(char[] a, char[] aux, int lo, int mid, int hi)
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
	private static void sort(char[] a, char[] aux, int lo, int hi)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	private static void sort(char[] a)
	{
		char[] aux = new char[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	public static void main(String[] args)
	{
		String A = "abcc",B="abc",C="ccba",D="cbaa";
		String_Permutation a=new String_Permutation();
		System.out.println(a.Permutation(A, B));
		System.out.println(a.Permutation(A, C));
		System.out.println(a.Permutation(A, D));
	}

	public boolean Permutation(String A, String B) {
		// write your code here
		if(A.length()!=B.length())return false;
		char[] chA = A.toCharArray();
		char[] chB = B.toCharArray();
		sort(chA);
		sort(chB);
		A = new String(chA);
		B = new String(chB);
		if(A.compareTo(B)==0)
			return true;
		return false;

	}
}