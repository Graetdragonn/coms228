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
		if (tree != null && tree.size() != 0) {
			// Clone the root Node
			root = tree.root.clone();  
			size++;
			
			// Copy the left subtree
			if (root.left != null) {
				root.left = cloneTreeRec(root.left);
			}
			
			// Copy the right subtree
			if (root.right != null) {
				root.right = cloneTreeRec(root.right);
			}
		}
	}

	
	/**
	 * Recursive method called by the constructor above. 
	 * 
	 * @param subTree
	 * @return
	 */
	private Node cloneTreeRec(Node subTree) 
	{
		if (subTree != null) {  
			// Clone the data in the node
			Node n = subTree.clone();
			
			// If the original Node has a left child, recursively copy that tree as well
			if (subTree.left != null) {
				n.left = cloneTreeRec(subTree.left);
			}
			
			// If the original Node has a right child, recursively copy that tree as well
			if (subTree.right != null) {
				n.right = cloneTreeRec(subTree.right);
			}
			
			// Check the parent relationship
			if (subTree.parent != null) {
				n.parent = subTree.parent;
			}
			
			size++;
			
			return n; 
		} else {
			return null;
		}
	}
	
	
	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 * 
	 * @return element stored at the tree root 
	 */
	public E getRoot()
	{
		if (root != null) {
			return root.data;
		} else {
			return null;
		}
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
	 * 
	 *  I think there are multiple ways to do this  
	 *  		- instantiate a new SplayTree?
	 *  		- delete root
	 */
	@Override
	public void clear() 
	{
		// Delete root
		root = null;
		
		// Reset size
		size = 0;
	}
	
	
	// ----------
	// BST method
	// ----------
	
	/**
	 * Adds an element to the tree without splaying.  The method carries out a binary search tree
	 * addition.  It is used for initializing a splay tree. **Very important - it does not change the root so whatever
	 * data was established as the root will remain the root (for operations like bulk add, etc.)
	 * 
	 * Calls link(). 
	 * 
	 * @param data
	 * @return true  if addition takes place  
	 *         false otherwise (i.e., data is in the tree already)
	 */
	public boolean addBST(E data)
	{
		if (data == null) {
			return false;
		}
		
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
		if (data == null) {
			return false;
		}
		
		// If root is null (empty tree), set root equal to a new Node
		// containing this data
		if (root == null) {
			root = new Node(data);
			
			size++;
			
			return true;
		}
		
		// Find if the tree already contains the value
		Node entry = findEntry(data);
		
		if (entry.data.compareTo(data) != 0) {
			// If the data doesn't match, then findEntry returned the last Node on the search path.
			// This node becomes the parent of the new Node
			Node newNode = new Node(data);
			
			// Link the two nodes together
			link(entry, newNode);
			
			// Splay at the new Node
			splay(newNode);
			
			size++;
			
			return true;
		}
		
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
		
		// Regardless if "exists" matches the data or not, you need to splay here
		splay(exists);
		
		// If the target data matches the Node data, return true, otherwise return false
		if (exists != null && exists.data.equals(data)) {
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
		// This is done - don't change
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
		Node n = findEntry(data);
		
		if (n == null) {
			return false;  
		} 
		
		// The Node n matches the target data
		if (n.data.compareTo(data) == 0) {
			Node parent = null;
			if (n != root) {
				parent = n.parent;
			}
			
			unlink(n);
			
			// Splay at the parent
			splay(parent);
			
			return(true);
		} else {
			// Node n does not match target.  Splay at last element in the search path
			splay(n);
		}
		
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
	 * @return element such that element.compareTo(data) == 0 -or-
	 *         null if element.compareTo(data) != 0
	 */
	public E findElement(E data) 
	{
		// Find the Node containing the data, OR the last Node in the search
		Node n = findEntry(data);
		
		if (n == null) {
			return null;
		} else if (n.data.compareTo(data) != 0) {
			// Splay at the Node returned (regardless if it's the target Node or not)
			splay(n);
			return null;
		} else {
			splay(n);
			return n.data;
		}
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
		
		while (true) {
			// Compare the current Node's data to the target data.
			// For Video, this compares the titles by alphabetical order
			int comp = current.data.compareTo(data);
			
			if (comp == 0) {
				// The current Node is the target Video
				return current;
			} else if (comp > 0) {
				// The current Node Video title is alphabetically HIGHER than
				// the target video.  If there is a left-child, continue to the left child
				if (current.left != null) {
					current = current.left;
				} else {
					// No left child, return last searched Node
					return current;
				}
			} else {
				// The current Node Video title is alphabetically LOWER than
				// the target video.  If a right-child exists, continue to the right child
				if (current.right != null) {
					current = current.right;
				} else {
					// No right child, return last searched Node
					return current;
				}
			}
		}
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
		if (root1 == null && root2 != null) {
			return root2;
		}
		
		if (root1 != null && root2 == null) {
			return root1;
		}
		
		if (root1 == null && root2 == null) {
			return null;
		}
		
		// Access the largest Node in T1.  Start with root1
		Node largest = root1;
		
		// First get the largest value
		while(largest.right != null) {
			largest = largest.right;
		}
		
		// Splay it back to root  **If root1 does not have children, you are assigning itself as its parent.  No bueno.
		if (largest != root1) {
			root1.parent = largest;
			largest.left = root1;
			
			// Remove the original
			largest.parent.right = null;
			largest.parent = null;
		}
				
		// Join tree root2 to root1 as the right child
		largest.right = root2;
		
		return largest;
	}

	
	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag 
	 * operations until the current node is moved to the root of the tree.
	 * 
	 * @param current  node to splay
	 */
	protected void splay(Node current)
	{
		
		if (current != null) {
			if (root != null) {
				// Splay if current is not the root
				while (current != root) {
					// Zig - parent of current is root
					if (current.parent == root) {
						zig(current);
					} else if ((current == current.parent.left && current.parent == current.parent.parent.left) ||
						 (current == current.parent.right && current.parent == current.parent.parent.right)) {
						// Ziz-Zig - both current and its parent need to be left or right children
						zigZig(current);
					} else if ((current == current.parent.left && current.parent == current.parent.parent.right) ||
						(current == current.parent.right && current.parent == current.parent.parent.left)) {
						// Zig-Zag
						zigZag(current);
					}
				}
			}
		}
	}
	

	/**
	 * This method performs the zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current)
    {
		// Double check parental status
		if (current != root) {
			if (current.parent == root) {
				// Current is the left-child of the root
				if (root.left != null && root.left == current) {
					rightRotate(current);
				} else {
					// Current is the right-child of the parent
					leftRotate(current);
				}
//				root = current;
			}
		}
	}

	
	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current)
	{
		if (current.parent != root) {
			if (current == current.parent.left && current.parent == current.parent.parent.left) {
				rightRotate(current.parent);
				rightRotate(current);
			} else if (current == current.parent.right && current.parent == current.parent.parent.right) {
				leftRotate(current.parent);
				leftRotate(current);
			}
		}
	}

	
    /**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() 
	 * and rightRotate().
	 * 
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current)
	{
		if (current.parent != root) {
			if (current == current.parent.left && current.parent == current.parent.parent.right) {
				rightRotate(current);
				leftRotate(current);
			} else if (current == current.parent.right && current.parent == current.parent.parent.left) {
				leftRotate(current);
				rightRotate(current);
			}
		}
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
		// Get the parent Node of current
		Node oldParent = current.parent;
		
		if (current.left != null) {
			// If current has a left child, assign it as it's parent right child
			oldParent.right = current.left;
			current.left.parent = oldParent;
		} else {
			oldParent.right = null;
		}
		
		// Set the original parent to the parent of current
		if (oldParent != root) {
			link(oldParent.parent, current);
		}
		
		// Update parent-child relationship
		link(current, oldParent);
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
		// Get the parent Node of current
		Node oldParent = current.parent;
		
		if (current.right != null) {
			// If the Node to splay has a right child, assign it as the parent's left child
			oldParent.left = current.right;
			current.right.parent = oldParent;
		} else {
			oldParent.left = null;
		}

		if (oldParent != root) {
			link(oldParent.parent, current);
		}
		
		// Update parent-child relationship
		link(current, oldParent);
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
		if (child != null) {
			// Set the new child's parent equal to the new parent
			child.parent = parent;
			
			// Make the comparison to determine if child should be left or right of parent
			int comp = parent.data.compareTo(child.data);
			
			if (comp < 0) {
				parent.right = child;
			} else {
				parent.left = child;
			}
			
			if (child == root) {
				// Assign the new parent to root
//				root.data = parent.data;
				root = parent;
				
				// The new root has no parent
				parent.parent = null;
			}
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
		if (n != null) {
			if (n == root) {
				// Case 1 - Unlink root
				Node joined = join(root.left, root.right);
				
				if (joined == null) {
					root.data = null;
					root = null;
				} else {
					root.data = joined.data;
					root = joined;
				}
			} else if (n.left == null && n.right == null) {
				// Case 2 - Unlink a leaf			
				if (n == n.parent.left) {
					n.parent.left = null;
				} else {
					n.parent.right = null;
				}
				
				n.parent = null;
			} else {
				// Case 3 - Node has at least one subtree
				Node parent = n.parent;
				Node joined = join(n.left, n.right);  
				
				n.data = joined.data;
				n = joined;
			
				link(parent, joined);
			}
			
			// Lastly, update size
			size--;
		}
	}
	
	
	/**
	 * Perform BST removal of a node. No splaying.
	 * 
	 * Called by the iterator method remove(). 
	 * @param n
	 */
	private void unlinkBST(Node n)
	{
		// TODO - test to catch the Exception
		// First check the call is not an illegalargument.  Why isn't this required to be thrown?
		if (n != null) {
			
			// If n has two children
			if (n.left != null && n.right != null) {
				// Get the successor of Node n
				Node s = successor(n);
				
				// Assign the successor data to n
				n.data = s.data;
				
				// Point n to its successor (deleting n)
				n = s;
			}
			
			// Now n has at most one child, so we delete n and 
			// replace it with the child (or possibly null)
			
			// First figure out whether the replacement node
			// should be the left child, right child or null
			Node replacement = null;
			
			if (n.left != null) {
				replacement = n.left;
			} else if (n.right != null) {
				replacement = n.right;
			}
			
			if (n == root) {
				root = replacement;
			} else {
				if (n == n.parent.left) {
					n.parent.left = replacement;
				} else {
					n.parent.right = replacement;
				}
			}
			
			if (replacement != null) {
				replacement.parent = n.parent;
			}
			
			size--;
		}
	}
	
	
	/**
	 * Called by unlink() and the iterator method next(). 
	 * 
	 * @param n
	 * @return successor of n or null if no successor
	 */
	private Node successor(Node n) 
	{
		if (n == null) {
			return null;
	    } else if (n.right != null) {
	    	// leftmost entry in right subtree
	    	Node current = n.right;
	    	
	    	// Get the leftmost element from the right subtree
	    	while (current.left != null) {
	    		current = current.left;
	    	}
	    	
	    	return current;
	    } else {
	    	// we need to go up the tree to the closest ancestor that is
	    	// a left child; its parent must be the successor
	    	Node current = n.parent;
	    	Node child = n;
	    	
	    	while (current != null && current.right == child) {
	    		child = current;
	    		current = current.parent;
	    	}
	      
	    	// either current is null, or child is left child of current
	    	return current;
	    }
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
		return(toStringRec(root, 0));
	}

	
	/** 
	 * Recursively builds a string output
	 * @param n
	 * @param depth
	 * @return
	 */
	private String toStringRec(Node n, int depth)
	{
		String returnString = "";
		
		// Places a newline character at the start of each line
		if (depth > 0) {
			returnString = returnString + "\n";
		}
		
		// Get the indentation spacing correct - 4 spaces * depth
		for (int i = 0; i < depth; i++) {
			returnString = returnString + "    ";
		}
		
		// If Node is null
		if (n == null) {
			returnString = returnString + "null";
			return returnString;
		} 

		// Add this Node's data
		returnString = returnString + n.data.toString();
		
		// Check for children
		if (n.left != null || n.right != null) {
			returnString = returnString + toStringRec(n.left, depth+1);
			returnString = returnString + toStringRec(n.right, depth+1);
		}

		return(returnString);
	}
	
	
	/**
	 * Calls the private method to create the iterator
	 */
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
		// Node to be returned by the next call to next()
		Node cursor;
		
		// Node returned by the last call to next() and available for removal.  This field is
		// null when no node is available for removal
		Node pending; 

	    public SplayTreeIterator()
	    {
	    	cursor = root;
	    	
	    	// Start at the smallest value in the tree
	    	if (cursor != null) {
	    		// Non-empty tree
	    		while (cursor.left != null) {
	    			cursor = cursor.left;
	    		}
	    	}
	    }
	    
	    /**
	     * If the cursor is not null, it is the next Node to be returned by the call to next()
	     */
	    @Override
	    public boolean hasNext()
	    {
	    	return cursor != null;
	    }

	    
	    @Override
	    public E next()
	    {
	    	// TODO - test.  Is this correct to throw this exception?
	    	// Calling next() on a cursor that is null throws this exception
	    	if (!hasNext()) throw new NoSuchElementException(); 
	    	
	    	// Set cursor to pending (the last object to be returned)
	    	pending = cursor;
	    	
	    	// Set the cursor to point to the next successor of the last cursor
	    	cursor = successor(cursor);
	    	
	    	// Return the data
	    	return pending.data;
	    }

	    /**
	     * This method will join the left and right subtrees of the node being removed.  
	     * It behaves like the class method remove(E data) after the node storing data is found.  
	     * 
	     * Calls unlinkBST(). 
	     * 
	     */
	    @Override
	    public void remove()
	    {
	    	// TODO - test to catch exception
	    	if (pending == null) throw new IllegalStateException();
	    	
	    	// Remember, current points to the successor of 
	        // pending, but if pending has two children, then
	        // unlinkNode(pending) will copy the successor's data 
	        // into pending and delete the successor node.
	        // So in this case we want to end up with current
	        // pointing to the pending node.
	        if (pending.left != null && pending.right != null)
	        {
	          cursor = pending;
	        }
	        
	        // TODO - this does not splay.  Test
	        unlinkBST(pending);
	        
	        // TODO - test
	        pending = null;
	    }
	}
}
