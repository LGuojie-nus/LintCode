

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;
public class Binary_Tree_Paths {



/************************************
 * testing module 
 * @param root
 */
	public static void print(TreeNode root) {
		if(root !=  null) {
			//Visit the node by Printing the node data  
			System.out.printf("%d ",root.val);
			print(root.left);
			print(root.right);
		}
	}

	private static TreeNode randomTree(int nodes) {
		if(nodes==0)return null;
		TreeNode root=new TreeNode(1);
		TreeNode curr=root;
		int j=2;
		for(int i=0;i<nodes-1;i++)
		{

			curr.left=new TreeNode(j++);
			curr.right=new TreeNode(j++);
			if(StdRandom.uniform(0,2)==1)
				curr=curr.left;
			else curr=curr.right;
		}
		return root;
	}
	
	
	
	/********************************
	 * API for submission
	 * @param root
	 * @param str
	 * @param s
	 * @return
	 */
	public static List<String> preorder(TreeNode root,String str,List<String> s) {
		if(root==null) {return s;}
		str+=Integer.toString(root.val);
		str+="->";
		if(root.left!=null)
			preorder(root.left,str,s);
		if(root.right!=null)
			preorder(root.right,str,s);
		if(root.left==null && root.right==null)
			s.add(str.substring(0,str.length()-2));
		return s;

	}

	public static List<String> binaryTreePaths(TreeNode root) {
		// write your code here
		List<String> list = new ArrayList<String>();;String str=new String();
		list=preorder(root,str,list);
		return list;		
	}
	
	
	
	
	/****************************
	 * unit test
	 * @param args
	 */
	public static void main(String[] args) {
		String str=new String();
		List<String> list = new ArrayList<String>();
		TreeNode root;
		//testing module
		root=randomTree(1); 
		print(root);
		System.out.println();
		list=binaryTreePaths(root);
		for(String s: list)
			System.out.println(s);

	}
}
