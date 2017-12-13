package AI;

import java.util.ArrayList;
import java.util.TimerTask;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class MiniMax {

	private Square[][] b;
	private Player currentPlayer; 
	private ArrayList<Move> allMoves;
	private Evaluator eval;
	private Move best;
	
		public MiniMax(Square [][] b)
		{
			this.b = b;
			currentPlayer = Main.getState().getLoop().getCurrentPlayer();
			allMoves = new ArrayList<Move>();
			eval = new Evaluator();
			getBestMove();
		}
		
		public void getBestMove()
		{	
			Move m = null;
			int score = 0;
			int bestScore = Integer.MIN_VALUE;
			setAllMoves();
			
			
			for(int i = 0; i<allMoves.size(); i++)
			{
				m = allMoves.get(i);
				score = eval.evaluateMove(b, m);
				if(score>bestScore)
				{	
					bestScore = score;
					System.out.println("Best score = " + bestScore);
					best = m;
				}
			}
			performMove();
		}
		
		
		private void setAllMoves()
		{
			System.out.println("checking moves");
			Kangaroo current; 
			
			//Not all best moves are added? This is probably where the bug is somewhere
			for(int i = 0; i < 14; i++){
				for(int j = 0 ;  j < 16; j++){
					if(b[i][j].isOccupied() && b[i][j].getIsHere().getTeam() == currentPlayer.getColor()){
						current = b[i][j].getIsHere() ;
						
						for(int y = 0; y < 14; y++){
							for(int x = 0; x < 16; x++){
								if(current.checkLegal(j, i, x, y, b[y][x])){
									Move m = new Move(current, b[i][j], b[y][x]) ;
									System.out.println("Dest coords are " + b[y][x].getxLoc() + " " + b[y][x].getyLoc());
									allMoves.add(m) ;
									//System.out.println("move added to list");
								}
							}
						}
					}
					
				}
			
			}
			
		}
			private void performMove(){
				System.out.println("perform move");
				Kangaroo k = best.getKangaroo() ;
				Square o = best.getOrigin() ;
				Square d = best.getDest() ;
				
				currentPlayer.performMove(k, o, d) ;
				System.out.println("move performed: " + d.getxLoc()+ " "+  d.getyLoc());
				Main.getState().getComponent().repaint();
				//if(Math.abs(o.getxLoc()- d.getxLoc()) == 1 || Math.abs(o.getyLoc()- d.getyLoc()) == 1 ) k.terminateTurn();
				if((Math.abs(o.getxLoc()- d.getxLoc()) > 1 || Math.abs(o.getyLoc()- d.getyLoc()) > 1 )) 
					{
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							 //TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("New Minimax Created");
						new MiniMax(b);//Main.getState().getLoop().getBoardAr());
					}
				
			}
			
		
		

}
