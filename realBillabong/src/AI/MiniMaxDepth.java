package AI;

import java.util.ArrayList;

public class MiniMaxDepth {
//call minimax for list of moves
//for each move create copy of current board & update it for the move
//for each virtual board come up with the best possible move
//repeat two & three
ArrayList<Move> ml;
int depth = 4;
Eval eval = new Eval();

public MiniMaxDepth()
	{
		
		MiniMax mm = new MiniMax(new Move(null, null, null));
		ml = mm.returnMoves();
		finalMoves(ml);
		
	}
	
	private void finalMoves(ArrayList<Move> currentMoves)
	{
		ArrayList<Move> finalMovesList = new ArrayList<Move>();
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
		
		tree(finalMovesList);
	}
	
	public void tree(ArrayList<Move> finalMovesList)
	{
		MiniMaxNode root = new MiniMaxNode(true, false, new ArrayList<MiniMaxNode>(), null, new Move(null,null,null), 0);
		for(int i = 0; i<finalMovesList.size(); i++) 
		{
			
			double score = eval.getScore(finalMovesList.get(i));
			System.out.println(score + " is the score");
			root.newChild(new MiniMaxNode(false, false, new ArrayList<MiniMaxNode>(), root, finalMovesList.get(i), score));
			
			
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
