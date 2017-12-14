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
					score += 2000;
				}
				
		System.out.println("SCORE IS :  "+score);
		
		return score;
	}
	
	
}
