import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Paths_Sum {
	
	public static void print(TreeNode root) {
		if(root !=  null) {
			//Visit the node by Printing the node data  
			System.out.printf("%d ",root.val);
			print(root.left);
			print(root.right);
		}
		else System.out.printf("%s","# ");
	}
	
	/*****************************8
	 * binary tree sum 
	 */
	
	private static int findSum(List<Integer> row){
		int sum=0;
		for(int i:row) {sum+=i;}
		return sum;
	}
	
	private static List<Integer> copyList(List<Integer> row){
		List<Integer> copy=new ArrayList<Integer>();
		copy.addAll(row);
		return copy;
	}

	public static List<List<Integer>> preorderBTS(TreeNode root,List<Integer> row,List<List<Integer>> list) {
		if(root==null) {return list;}
		row.add(root.val);
		//copy a list, to be used by right node
		List<Integer> rowCopy=copyList(row);  //since list() is a pointer, it's value changed after left side's operation
		if(root.left!=null)
			preorderBTS(root.left,row,list);
		if(root.right!=null)
			preorderBTS(root.right,rowCopy,list);
		if(root.left==null && root.right==null)
			list.add(row);
		return list;
	}

	
	public static List<List<Integer>> binaryTreePathsSum(TreeNode root, int target) {
        // write your code here
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> row=new ArrayList<Integer>();
		list=preorderBTS(root,row,list);
		List<List<Integer>> newList= new ArrayList<List<Integer>>();
		for(List<Integer> s: list) {
			if (findSum(s)==target)
				newList.add(s);
			//System.out.println(findSum(s));
		}
		if (newList==null)return null;
		return newList;	
	}
	
	/*******************
	 * uint test
	 * @param args
	 */
	public static void main(String[] args) {		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> row=new ArrayList<Integer>();
		TreeNode root;
		//root=randomTree(1);
		root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(4);
		root.left.left=new TreeNode(2);
		root.left.right=new TreeNode(3);
		print(root);
		System.out.println("########");
		System.out.println();
		list=binaryTreePathsSum(root,5);
		for(List<Integer> s: list) {
			//for(int a:s)
			System.out.println(s);
			//System.out.println();
		}
	}
}
