package AI;

import java.util.ArrayList;

public class GenericSearchTree<GameState extends Comparable<? super GameState>> 
{
	
	public class Node 
	{
		
		public GameState _gameState;
		ArrayList<Node> _children;
		
		// This constructor initialises a childless node.
		public Node(GameState gameState) 
		{
			_gameState = gameState;
			_children = null;		
		}
		
		// This constructor initialises a node with children.
		public Node(GameState gameState, ArrayList<Node> children) 
		{
			_gameState = gameState;
			_children = children;
		}
	}
	
	// Local variables
	public Node root;					// Pointer to root node, if present.
	private boolean removalSuccesful;	// Set to true when remove() succeeds.
	private boolean insertionSuccesful; // Used in insert().
	
	// This constructor initialises an empty search tree.  An empty search tree contains no nodes.
	public GenericSearchTree() 
	{
		root = null;
	}
	
	// This method determines if the search tree is empty.
	public boolean isEmpty()
	{
		return (root == null);
	}
	
	// These methods attempt to find an element in the search tree.
	public GameState find(GameState gameState)
	{
		Node found = find(root, gameState);
		return (found == null) ? null : found._gameState;
	}
	
	public Node find(Node start, GameState gameState)
	{
		// If the element isn't found, return null.
		if (start == null)
		{
			return null;
		}
		
		// Loop through children to find correct Node.
		for (int i = 0; i < start._children.size(); i++) 
		{
			// Compare the current node's gameState to the gameState we're looking for.
			int comparison = start._children.get(i)._gameState.compareTo(gameState);
			
			// If comparison == 0, gameStates are equal.
			if (comparison == 0) 
			{
				return start;
			}
		}
		// If no match was found, continue searching in the first child's children.
		return find(start._children.get(0), gameState);
	}
	
	//These methods insert an element into the search tree, unless it is already stored.
	public boolean insert(GameState gameState)
	{
		return insert(root, gameState);
	}
	
	// Have to review this method. Didn't have time to test yet and not sure if it works like this.
	public boolean insert(Node start, GameState gameState) 
	{
		// We've reached the point of insertion
		if (start == null)
		{
			// Insert our gameState into a new node
			root = new Node(gameState, null);
			insertionSuccesful = true;
		}

		// Compare the current node's gameState to the gameState we're looking to delete.
		for (int i = 0; i < start._children.size(); i++) 
		{
			// Compare the current node's gameState to the gameState we're looking for.
			int comparison = start._children.get(i)._gameState.compareTo(gameState);
			
			// If the gameStates do not match.
			if(comparison != 0) 
			{
				// If we've reached the point of insertion.
				if(start._children.get(i) == null) 
				{
					// Insert our gameState into a new node.
					start._children.add(new Node(gameState,null));
					insertionSuccesful = true;
				}
				// Otherwise, keep traversing until we reach the point of insertion
				return insert(start._children.get(i), gameState);
			}
			// If the gameState is already in the tree.
			else 
			{
				// Don't insert the gameState.
				insertionSuccesful = false;
			}
		}
		return insertionSuccesful;
	}

	// These methods delete a gameState from the search tree, if it is present.
	public boolean remove(GameState gameState)
	{
		removalSuccesful = true;
		root = remove(root, gameState);
		return removalSuccesful;
	}
	
	public Node remove(Node start, GameState gameState)
	{
		// If the gameState we want to delete wasn't found
		if (start == null)
		{
			// Go back up the recursive loop, but let our object know that the
			// gameState we wanted to delete wasn't found
			removalSuccesful = false;
			return null;
		}
		
		// Compare the current node's gameState to the gameState we're looking to delete.
		for (int i = 0; i < start._children.size(); i++) 
		{
			// Compare the current node's gameState to the gameState we're looking for.
			int comparison = start._children.get(i)._gameState.compareTo(gameState);
			
			// If we are at the gameState we want to delete
			if(comparison == 0) 
			{
				start._children.remove(i);
			}
			else 
			{
				// If no match was found, continue searching in the first child's children.
				return remove(start._children.get(0), gameState);
			}
		}
		// Return the modified search tree.
		return start;
	}
}
