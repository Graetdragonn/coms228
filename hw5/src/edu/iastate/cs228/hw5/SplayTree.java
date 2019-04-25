package edu.iastate.cs228.hw5;


import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Brian Bates
 *
 */


/**
 * 
 * This class implements a splay tree.  Add any helper methods or implementation details 
 * you'd like to include.
 *
 */


public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E>
{
	protected Node root;        // The root node of the SplayTree
	protected int size; 		// The total number of elements in the tree

	public class Node  			// made public for grading purpose
	{
		public E data;			// The data it holds
		public Node left;		// The left child node
		public Node parent;		// The parent node
		public Node right;		// The right child node

		// Constructor only adds the data
		public Node(E data) {
			this.data = data;
		}

		/**
		 * Clone the node
		 */
		@Override
		public Node clone() {
			return new Node(data);
		}	
	}

	
	/**
	 * Default constructor constructs an empty tree. 
	 */
	public SplayTree() 
	{
		size = 0;
	}
	
	
	/**
	 * Creates a tree root to store given data.
	 * 
	 * Needs to call addBST() later on to complete tree construction. 
	 */
	public SplayTree(E data) 
	{
		// TODO - creates a tree root to store the given data.  Since this is the CONSTRUCTOR, there are no other Nodes
		
		// Add the element to the tree without splaying
		addBST(data);
	}

	
	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.  
	 * No splaying. Calls cloneTreeRec(). 
	 * 
	 * @param tree
	 */
	public SplayTree(SplayTree<E> tree)
	{
		// TODO 
	}

	
	/**
	 * Recursive method called by the constructor above. 
	 * 
	 * @param subTree
	 * @return
	 */
	private Node cloneTreeRec(Node subTree) 
	{
		// TODO
		return null; 
	}
	
	
	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 * 
	 * @return element stored at the tree root 
	 */
	public E getRoot()
	{
		// TODO 
		return null; 
	}
	
	
	/**
	 * @return the size of the splay tree
	 */
	@Override 
	public int size()
	{
		return size; 
	}
	
	
	/**
	 * Clear the splay tree. 
	 */
	@Override
	public void clear() 
	{
		// TODO 
	}
	
	
	// ----------
	// BST method
	// ----------
	
	/**
	 * Adds an element to the tree without splaying.  The method carries out a binary search tree
	 * addition.  It is used for initializing a splay tree. 
	 * 
	 * Calls link(). 
	 * 
	 * @param data
	 * @return true  if addition takes place  
	 *         false otherwise (i.e., data is in the tree already)
	 */
	public boolean addBST(E data)
	{
		if (root == null) {
			// Root does not exist yet, create a new Node to hold the data and assign it to root
			root = new Node(data);
			
			// Iterate the size
			size++;
			
			// Adding the Node took place, return true
			return true;
		}
		
		Node current = root;
		
		while (true) {
			// Compare target data to the current Node's data
			int comp = current.data.compareTo(data);
			
			// Case 1 - target data and current Node data are the same (already exists).  Do not add the new
			//          data and return false (sets do not have dupliates)
			if (comp == 0) {
				return false;
			} else if (comp > 0) {
				// Case 2 - Current Node data (Video title) is alphabetically AFTER target data
				// 			If there is a left-child, set that Node to current
				if (current.left != null) {
					current = current.left;
				} else {
					// Link the current Node to a new Child Node
					link(current, new Node(data));
					
					// Increase the size
					size++;
					return true;
				}
			} else {
				// Case 3 - Current Node data (Video title) is alphabetically BEFORE target data
				// 			If there is a right child, set that Node to current
				if (current.right != null) {
					current = current.right;
				} else {
					// No right child.  Create it and link it to current
					link(current, new Node(data));
					
					// Iterate size
					size++;
					
					return true;
				}
			}
		}
	}
	
	
	// ------------------
	// Splay tree methods 
	// ------------------
	
	/**
	 * Inserts an element into the splay tree. In case the element was not contained, this  
	 * creates a new node and splays the tree at the new node. If the element exists in the 
	 * tree already, it splays at the node containing the element. 
	 * 
	 * Calls link(). 
	 * 
	 * @param  data  element to be inserted
	 * @return true  if addition takes place 
	 *         false otherwise (i.e., data is in the tree already)
	 */
	@Override 
	public boolean add(E data)
	{
		// TODO 
		return false; 
	}
	
	
	/**
	 * Determines whether the tree contains an element.  Splays at the node that stores the 
	 * element.  If the element is not found, splays at the last node on the search path.
	 * 
	 * @param  data  element to be determined whether to exist in the tree
	 * @return true  if the element is contained in the tree 
	 *         false otherwise
	 */
	public boolean contains(E data)
	{
		// Find the Node containing "data" or the last Node in the search path
		Node exists = findEntry(data);
		
		// TODO - Regardless if "exists" matches the data or not, you need to splay here
		
		// If the target data matches the Node data, return true, otherwise return false
		if (exists.data.equals(data)) {
			return true;
		} else {
			return false; 
		}
	}
	
	
	/**
	 * Finds the node that stores the data and splays at it.
	 *
	 * @param data
	 */
	public void splay(E data) 
	{
		contains(data);
	}

	
	/**
	 * Removes the node that stores an element.  Splays at its parent node after removal
	 * (No splay if the removed node was the root.) If the node was not found, the last node 
	 * encountered on the search path is splayed to the root.
	 * 
	 * Calls unlink(). 
	 * 
	 * @param  data  element to be removed from the tree
	 * @return true  if the object is removed 
	 *         false if it was not contained in the tree 
	 */
	public boolean remove(E data)
	{
		// TODO 
		return false; 
	}


	/**
	 * This method finds an element stored in the splay tree that is equal to data as decided 
	 * by the compareTo() method of the class E.  This is useful for retrieving the value of 
	 * a pair <key, value> stored at some node knowing the key, via a call with a pair 
	 * <key, ?> where ? can be any object of E.   
	 * 
	 * Calls findEntry(). Splays at the node containing the element or the last node on the 
	 * search path. 
	 * 
	 * @param  data
	 * @return element such that element.compareTo(data) == 0
	 */
	public E findElement(E data) 
	{
		// TODO 
		return null; 
	}

	
	/**
	 * Finds the node that stores an element. It is called by methods such as contains(), add(), remove(), 
	 * and findElement(). 
	 * 
	 * No splay at the found node. 
	 *
	 * @param  data  element to be searched for 
	 * @return node  if found or the last node on the search path otherwise
	 *         null  if size == 0. 
	 */
	protected Node findEntry(E data)
	{
		// First check if the list size is zero or if the data to be checked for is null
		if (size == 0  || data == null) {
			return null;
		}
		
		// Source: Examples > binarySearchTree > BSTSet.java
		Node current = root;
		
		while (current != null) {
			// Compare the current Node's data to the target data.
			// For Video, this compares the titles by alphabetical order
			int comp = current.data.compareTo(data);
			
			if (comp == 0) {
				// The current Node is the target Video
				return current;
			} else if (comp > 0) {
				// The current Node Video title is alphabetically HIGHER than
				// the target video.  Continue to the left child
				current = current.left;
			} else {
				// The current Node Video title is alphabetically LOWER than
				// the target video.  Continue to the left child
				current = current.right;
			}
		}
		
		// If a match is never found, return the last Node in the search path
		return current;		
	}
	
	
	/** 
	 * Join the two subtrees T1 and T2 rooted at root1 and root2 into one.  It is 
	 * called by remove(). 
	 * 
	 * Precondition: All elements in T1 are less than those in T2. 
	 * 
	 * Access the largest element in T1, and splay at the node to make it the root of T1.  
	 * Make T2 the right subtree of T1.  The method is called by remove(). 
	 * 
	 * @param root1  root of the subtree T1 
	 * @param root2  root of the subtree T2 
	 * @return the root of the joined subtree
	 */
	protected Node join(Node root1, Node root2)
	{
		// TODO
		return null; 
	}

	
	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag 
	 * operations until the current node is moved to the root of the tree.
	 * 
	 * @param current  node to splay
	 */
	protected void splay(Node current)
	{
		// TODO
	}
	

	/**
	 * This method performs the zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current)
    {
		// TODO
	}

	
	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current)
	{
		// TODO
	}

	
    /**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() 
	 * and rightRotate().
	 * 
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current)
	{
		// TODO
	}	
	
	
	/**
	 * Carries out a left rotation at a node such that after the rotation 
	 * its former parent becomes its left child. 
	 * 
	 * Calls link(). 
	 * 
	 * @param current
	 */
	private void leftRotate(Node current)
	{
		
	}

	
	/**
	 * Carries out a right rotation at a node such that after the rotation 
	 * its former parent becomes its right child. 
	 * 
	 * Calls link(). 
	 * 
	 * @param current
	 */
	private void rightRotate(Node current)
	{
		
	}	
	
	
	/**
	 * Establish the parent-child relationship between two nodes. 
	 * 
	 * Called by addBST(), add(), leftRotate(), and rightRotate(). 
	 * 
	 * @param parent
	 * @param child
	 */
	private void link(Node parent, Node child) 
	{
		
		child.parent = parent;
		
		// Make the comparison to determine if child should be left or right of parent
		int comp = parent.data.compareTo(child.data);
		
		if (comp < 0) {
			parent.right = child;
		} else {
			parent.left = child;
		}
	}
	
	
	/** 
	 * Removes a node n by replacing the subtree rooted at n with the join of the node's
	 * two subtrees.
	 * 
	 * Called by remove().   
	 * 
	 * @param n
	 */
	private void unlink(Node n) 
	{
		// TODO 
	}
	
	
	/**
	 * Perform BST removal of a node. 
	 * 
	 * Called by the iterator method remove(). 
	 * @param n
	 */
	private void unlinkBST(Node n)
	{
		// TODO 
	}
	
	
	/**
	 * Called by unlink() and the iterator method next(). 
	 * 
	 * @param n
	 * @return successor of n 
	 */
	private Node successor(Node n) 
	{
		// TODO 
		return null; 
	}

	
	/**
	 * Write the splay tree according to the format specified in Section 2.2 of the project 
	 * description.
	 * 	
	 * Calls toStringRec(). 
	 *
	 */
	@Override 
	public String toString()
	{
		// TODO - test 
		return(toStringRec(root, 0));
	}

	
	private String toStringRec(Node n, int depth)
	{
		// TODO - test
		
		String returnString = "";
		
		// Get the indentation spacing correct - 4 spaces * depth
		for (int i = 0; i < depth; i++) {
			returnString = returnString + "    ";
		}
		
		// If Node is null
		if (n == null) {
			returnString = returnString + "\n";
			return returnString;
		} 

		// Add this Node's data
		returnString = returnString + n.data.toString() + "\n";
		
		// Check for children
		if (n.left != null || n.right != null) {
			returnString = returnString + toStringRec(n.left, depth+1);
			returnString = returnString + toStringRec(n.right, depth+1);
		}
		
		return(returnString);
	}
	
	
	
	@Override
	public Iterator<E> iterator()
	{
	    return new SplayTreeIterator();
	}
	
	/**
	   *
	   * Iterator implementation for this splay tree.  The elements are returned in 
	   * ascending order according to their natural ordering.  The methods hasNext()
	   * and next() are exactly the same as those for a binary search tree --- no 
	   * splaying at any node as the cursor moves.  The method remove() behaves like 
	   * the class method remove(E data) --- after the node storing data is found.  
	   *  
	   */
	private class SplayTreeIterator implements Iterator<E>
	{
		Node cursor;
		Node pending; 

	    public SplayTreeIterator()
	    {
	    	// TODO
	    }
	    
	    @Override
	    public boolean hasNext()
	    {
	    	// TODO
	    	return true; 
	    }

	    @Override
	    public E next()
	    {
	    	// TODO
	    	return null; 
	    }

	    /**
	     * This method will join the left and right subtrees of the node being removed.  
	     * It behaves like the class method remove(E data) after the node storing data is found.  
	     * Place the cursor at the parent (or the new root if removed node was the root).
	     * 
	     * Calls unlinkBST(). 
	     * 
	     */
	    @Override
	    public void remove()
	    {
	      // TODO
	    }
	}
}
