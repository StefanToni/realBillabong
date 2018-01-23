package AI;

import java.util.ArrayList;

public class MiniMaxNode {

	ArrayList<MiniMaxNode> children;
	Boolean root, leaf;
	double score;
	Move move;
	MiniMaxNode parent;
	int i = 0;
	
	public MiniMaxNode(Boolean root, Boolean leaf, ArrayList<MiniMaxNode> children, MiniMaxNode parent, Move move, double score)
	{
		this.root = root;
		this.leaf = leaf;
		this.children = children;
		this.parent = parent;
		this.score = score;
		this.move = move;
	}
	
	public MiniMaxNode getNextChild()
	{	if(i == children.size())
		{
			i = 0;
		}
		
	i++;
	
		return children.get(i-1);
		
	}
	
	public double getScore()
	{
		return score;
	}
	
	public void setScore(double sc)
	{
		score = sc;
	}
	
	public void newChild(MiniMaxNode child)
	{
		children.add(child);
	}
	
	public void deleteChild(MiniMaxNode child)
	{
		for(int i = 0; i<children.size(); i++)
		{
			if(children.get(i) == child)
			{
				children.remove(i);
			}
		}
	}
	
	public Move getMove()
	{
		return move;
	}
	
	public void newParent(MiniMaxNode parent)
	{
		this.parent = parent;
	}
	
	public MiniMaxNode getParent()
	{
		return parent;
	}
//	public void deleteParent(MiniMaxNode parent)
//	{
//		for(int i = 0; i<parents.size(); i++)
//		{
//			if(parents.get(i) == parent)
//			{
//				parents.remove(i);
//			}
//		}
//	}
	
	
}
