package AI;

import java.util.ArrayList;

public class MiniMaxDepth {
//call minimax for list of moves
//for each move create copy of current board & update it for the move
//for each virtual board come up with the best possible move
//repeat two & three
ArrayList<Move> ml;
int depth = 4;
int counter, playerCounter, depthLevel = 0;
Eval eval = new Eval();
ArrayList<Move> finalMovesList = new ArrayList<Move>();


public MiniMaxDepth()
	{
		
		MiniMax mm = new MiniMax(new Move(null, null, null));
		ml = mm.returnMoves();
		finalMoves(ml);
		
	}

public MiniMaxDepth(int depthLevel, MiniMaxNode parent)
{	this.depthLevel = depthLevel;
	if (depthLevel == depth+1) return;
	MiniMax mm = new MiniMax(parent.getMove());
	ml = mm.returnMoves();
	finalMoves(parent, ml);
}
	

	private void finalMoves(ArrayList<Move> currentMoves)
	{	
		while(counter<depth){
			if(playerCounter == 0) finalMovesList = new ArrayList<Move>();
			playerCounter = 1;
		for(int i = 0; i < currentMoves.size(); i++)
		{
			int x = currentMoves.get(i).getDest().getxLoc();
			int y = currentMoves.get(i).getDest().getyLoc();
			int ox = currentMoves.get(i).getOrigin().getxLoc();
			int oy = currentMoves.get(i).getOrigin().getyLoc();
			
			if(walk(x,y,ox,oy))
			{
				finalMovesList.add(currentMoves.get(i));
			}
			
			else
			{
				MiniMax temp = new MiniMax(currentMoves.get(i));
				finalMoves(temp.returnMoves());
			}
			
		}
		
		}
		
		tree(null, finalMovesList);
		playerCounter = 0;
		finalMoves(finalMovesList);
		
	}

	private void finalMoves(MiniMaxNode parent, ArrayList<Move> currentMoves)
	{	
		while(counter<depth){
			if(playerCounter == 0) finalMovesList = new ArrayList<Move>();
			playerCounter = 1;
		for(int i = 0; i < currentMoves.size(); i++)
		{
			int x = currentMoves.get(i).getDest().getxLoc();
			int y = currentMoves.get(i).getDest().getyLoc();
			int ox = currentMoves.get(i).getOrigin().getxLoc();
			int oy = currentMoves.get(i).getOrigin().getyLoc();
			
			if(walk(x,y,ox,oy))
			{
				finalMovesList.add(currentMoves.get(i));
			}
			
			else
			{
				MiniMax temp = new MiniMax(currentMoves.get(i));
				finalMoves(temp.returnMoves());
			}
			
		}
		
		}
	//	counter++;
		tree(parent, finalMovesList);
//		playerCounter = 0;
//		for(int i = 0; i<finalMovesList.size(); i++)
//		{	tree(parent, finalMovesList);
//			finalMovesList = new MiniMax(finalMovesList.get(i));
//			finalMoves(finalMovesList);
//			
//		}
	}
	
	public void tree(MiniMaxNode parent, ArrayList<Move> finalMovesList)
	{
		
		if (parent.getMove() == null) 
			{
				MiniMaxNode root = new MiniMaxNode(true, false, new ArrayList<MiniMaxNode>(), null, new Move(null,null,null), 0);
				for(int i = 0; i<finalMovesList.size(); i++) 
				{
					
					double score = eval.getScore(finalMovesList.get(i));
					System.out.println(score + " is the score");
					
					root.newChild(new MiniMaxNode(false, false, new ArrayList<MiniMaxNode>(), root, finalMovesList.get(i), score));
					
					
				}
			}
		
		else 
			{
				MiniMaxNode tempRoot = new MiniMaxNode(false, false, new ArrayList<MiniMaxNode>(), parent, parent.getMove(), 0);
			
		
				for(int i = 0; i<finalMovesList.size(); i++) 
				{
			
					double score = eval.getScore(finalMovesList.get(i));
					System.out.println(score + " is the score");
					MiniMaxNode tempNode = new MiniMaxNode(false, false, new ArrayList<MiniMaxNode>(), tempRoot, finalMovesList.get(i), score);
					tempRoot.newChild(tempNode);
					new MiniMaxDepth(depthLevel+1,tempNode);
			
				}
		
			}
		
	}
	private Boolean walk(int x, int y, int ox, int oy)
	{
		if(Math.abs(ox-x) == 1 || Math.abs(oy-y) == 1)
		{
			return true;
		}
		
		return false;
	}
	
	
}
