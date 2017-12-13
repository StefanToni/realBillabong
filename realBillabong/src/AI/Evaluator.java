package AI;

import Game.*;


public class Evaluator
{
	
	private final boolean DEBUG = false;
	
	public int Evaluate(Square[][] boardArray, Kangaroo k, Square op, Square np) {
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
		if((k.onlyOne(old_x, old_y, new_x, new_y)) && (k.checkLegal(old_x, old_y, new_x, new_y,np))){
			score -= 1;
		}
		
		if(k.getRightLeft(old_x, old_y, new_x, new_y) && k.checkLegal(old_x, old_y, new_x, new_y,np)){
			score+= 5 ;
		}
		
		if(k.getLeftRight(old_x, old_y, new_x, new_y) && k.checkLegal(old_x, old_y, new_x, new_y,np)){
			score-= 3 ;
		}
			
		if (DEBUG) System.out.println("evaluated score = " + score);
		return score;
	}
	
	public int evaluateMove(Square[][] b, Move m)
	{
		int score = 0;
		int old_x = m.getOrigin().getxLoc();
		int old_y = m.getOrigin().getyLoc();
		int new_x = m.getDest().getxLoc();
		int new_y = m.getDest().getyLoc();
		Kangaroo k = m.getKangaroo();
		Square np = m.getDest();
		
		// Jump
		if(k.checkLegal(old_x, old_y, new_x, new_y,np) && !(k.onlyOne(old_x, old_y, new_x, new_y))){
			score += 1;
		}
		
		// Walk
		if((k.onlyOne(old_x, old_y, new_x, new_y)) && (k.checkLegal(old_x, old_y, new_x, new_y,np))){
			score -= 1;
		}
		
		if(k.getRightLeft(old_x, old_y, new_x, new_y) && k.checkLegal(old_x, old_y, new_x, new_y,np)){
			score+= 5 ;
		}
		
		if(k.getLeftRight(old_x, old_y, new_x, new_y) && k.checkLegal(old_x, old_y, new_x, new_y,np)){
			score-= 3 ;
		}
			
		if (DEBUG) System.out.println("evaluated score = " + score);
		
		return score;
				
	}
	
}
