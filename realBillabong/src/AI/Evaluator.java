package AI;

import Game.*;


public class Evaluator
{
	public int Evaluate(Board b, Kangaroo k, Square op, Square np) {
		int score = 0;
		int old_x = op.getxLoc();
		int old_y = op.getyLoc();
		int new_x = np.getxLoc();
		int new_y = np.getyLoc();
		
		// Jump
		if(k.checkLegal(old_x, old_y, new_x, new_y,np) && !(k.onlyOne(old_x, old_y, new_x, new_y))){
			score += 1;
		}
		
		// Walk
		if((k.onlyOne(old_x, old_y, new_x, new_y)) && !(k.checkLegal(old_x, old_y, new_x, new_y,np))){
			score -= 1;
		}
				
		return score;
	}
}
