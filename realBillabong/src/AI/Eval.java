package AI;

import Game.Kangaroo;
import Game.LapChecker;
import Game.Square;
import realBillabong.Main;

public class Eval {
	
	private Kangaroo k;
	private Square op, np;
	
	public Eval()
	{
		
		
	}

	public double getScore(Move thisMove)
	{
		int x = thisMove.getOrigin().getxLoc();
		int y = thisMove.getOrigin().getyLoc();
		int nx = thisMove.getDest().getxLoc();
		int ny = thisMove.getDest().getyLoc();
		
		double score = 0;
		Diffuser diff = Main.getState().getLoop().getDiffuser();
		score += diff.getWeight(nx, ny)/diff.getWeight(x, y);
				
				LapChecker co = new LapChecker();
				int lap = co.checkLap(x, y, nx, ny, 1);
				if(lap == 0)
				{
					score = -1000000.0;
				}
				
				if(lap == 2)
				{
					score += 200000.0;
				}
				
		//System.out.println("SCORE IS :  "+score);
		
		return score;
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
				
				LapChecker co = new LapChecker();
				int lap = co.checkLap(x, y, nx, ny, 1);
				if(lap == 0)
				{
					score = -1000000.0;
				}
				
				if(lap == 2)
				{
					score += 200000.0;
				}
				
		//System.out.println("SCORE IS :  "+score);
		
		return score;
	}
	
	public double getScoreVariance(Kangaroo k, Square op, Square np)
	{
		double score = 0;
		score = getScore(k,op,np);
		double variance = score*0.1;
		if(Math.random()*1>0.5) variance = variance*-1;
		System.out.println("Variance = " + variance);
		score = score +variance;
		
		return score;
		
	}
	
	
}
