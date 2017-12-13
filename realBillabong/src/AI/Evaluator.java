package AI;

import java.util.Random;

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
		if( Math.abs(old_x - new_x) > 1 || Math.abs(old_y - new_y) > 1 ){
			score += 5;
		}
		
		// Walk
		if(Math.abs(old_x - new_x) <= 1 || Math.abs(old_y - new_y) <= 1){
			score += 2;
		}
		
		if(k.getRightLeft(old_x, old_y, new_x, new_y)){
			score+= 5 ;
		}
		
		if(k.getLeftRight(old_x, old_y, new_x, new_y)){
			score-= 1 ;
		}
		
			
		if (DEBUG) System.out.println("evaluated score = " + score);
		
		Random rnd = new Random() ;
		int varCreate = score ;
		//System.out.println(" score: " + score);
		if(varCreate < 0){
			varCreate = varCreate * -1 ;
		}
		//System.out.println("varCreate: "+ varCreate);
		int variance = rnd.nextInt(varCreate) ;
		score = (score * 20);
		if(rnd.nextInt(10) < 5){
			variance = variance * -1 ;
		}
		score = score + variance ;
		//System.out.println("score after variances : " + score);
		
		score += getDirections(old_x, old_y, new_x, new_y);
		
		return score;
				
	}
	
	private int getDirections(int x, int y, int nx, int ny)
	{
		int directions = 0 ;
		
		if(x<8 && y>7  && ny<y && nx<=x)
		{
			directions = directions + 40 ;
		}
		
		else if(x<8 && y<7 && nx>x && ny<=y)
		{
			directions = directions + 40 ;
		}
		
		else if(x>=8 && y<7 && ny>y && nx>=x)
		{
			directions = directions + 40 ;
		}
		
		else if(x>=8 && y>=7 && nx<x && ny>=y)
		{
			directions = directions + 40 ;
		}
		
		

		if(x<8 && y>7  && ny>y || nx<x)
		{
			directions = directions - 40  ;
		}
		
		if(x<8 && y<7 && nx<x || ny<y)
		{
			directions = directions - 40 ;
		}
		
		if(x>=8 && y<7 && ny<y || nx>x)
		{
			directions = directions - 40 ;
		}
		
		if(x>=8 && y>=7 && nx>x || ny>y)
		{
			directions = directions - 40 ;
		}
		
		

		
		return directions;
	}
	
}
