package AI;

import Game.*;


public class Evaluator
{
	public int Evaluate(Board b, Kangaroo k, Square op, Square np) {
		int score = 0;
		int oldx = op.getxLoc();
		int oldy = op.getyLoc();
		int newx = np.getxLoc();
		int newy = np.getyLoc();
		
		// Jump
		if(k.checkLegal(oldx, oldy, newx, newy,np) && !(k.onlyOne(oldx, oldy, newx, newy))){
			score += 3;
		}
		
		// Walk
		if((k.onlyOne(oldx, oldy, newx, newy) && !(k.checkLegal(oldx, oldy, newx, newy,np)))){
			score += 1;
		}
		
		if(k.getRightLeft(op.getxLoc(), op.getyLoc(), np.getxLoc(), np.getyLoc()))
		{
			score+=5;
		}
		
		if(k.getLeftRight(op.getxLoc(), op.getyLoc(), np.getxLoc(), np.getyLoc()))
		{
			score-=5;
		}
		
		if(np.getxLoc()<8 && np.getyLoc())
				
		return score;
	}
	
	
	
}
