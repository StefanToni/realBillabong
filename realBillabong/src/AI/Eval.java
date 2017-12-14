package AI;

import Game.Kangaroo;
import Game.Square;
import realBillabong.Main;

public class Eval {
	
	private Kangaroo k;
	private Square op, np;
	
	public Eval()
	{
		
		
	}

	public double getScore(Kangaroo k, Square op, Square np)
	{	
		
		int x = op.getxLoc();
		int y = op.getyLoc();
		int nx = np.getxLoc();
		int ny = np.getyLoc();
		
		double score = 0;
		Diffuser diff = Main.getState().getLoop().getDiffuser();
		score += diff.getWeight(nx, ny)/diff.getWeight(x, y);
				
				
				if(k.getRightLeft(x, y, nx, ny) && k.checkLegal(x, y, nx, ny,np)){
					score+= 20000 ;
				}
				
				if(k.getLeftRight(x, y, nx, ny) && k.checkLegal(x, y, nx, ny,np)){
					score-= 10000000000.0 ;
				}
		System.out.println("SCORE IS :  "+score);
		
		return score;
	}
	
	
}
