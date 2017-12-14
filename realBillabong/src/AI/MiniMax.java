package AI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class MiniMax {

	private Square[][] b;
	private Player currentPlayer, lastPlayer; 
	private double timeStart, timeEnd, timeTaken;
	private Eval eval;
	private Move best, last;
	
	
	ArrayList<Move> possibleMoves ;
	Move finalMove ;
	Kangaroo lastKanga = null;
	
		public MiniMax(Square [][] b)
		{
			this.b = b;
			currentPlayer = Main.getState().getLoop().getCurrentPlayer();
			possibleMoves = new ArrayList<Move>();
			eval = new Eval();
			checkForMoves();
			getBestMove();
		}
		
		public MiniMax(Square [][] b, Kangaroo lastKanga, Move last)
		{
			this.lastKanga = lastKanga;
			this.b = b;
			this.last = last;
			currentPlayer = Main.getState().getLoop().getCurrentPlayer();
			
			possibleMoves = new ArrayList<Move>();
			eval = new Eval();
			timeStart = System.currentTimeMillis();
			checkForMovesKanga();
			getBestMove();
			
		}
		
		
		public void getBestMove()
		{	
			Move m = null;
			double score = 0;
			double bestScore = 0;
			
			
			
			for(int i = 0; i<possibleMoves.size(); i++)
			{
				m = possibleMoves.get(i);
				score = eval.getScore(m.getKangaroo(), m.getOrigin(), m.getDest());
				//if (m == last) 
				//	{
				//		System.out.println("Move = last");
				//		score-=1000;
				//	}
				if(score>bestScore)
				{	
					bestScore = score;
					//System.out.println("Best score = " + bestScore);
					best = m;
				}
			}
			performMove();
		}
		
		
		

		
		public void checkForMoves(){
			//System.out.println("checking moves");
			Kangaroo current; 
			
			for(int i = 0; i < 14; i++){
				for(int j = 0 ;  j < 16; j++){
					if(b[i][j].isOccupied() && b[i][j].getIsHere().getTeam() == currentPlayer.getColor()){
						current = b[i][j].getIsHere() ;
						for(int y = 0; y < 14; y++){
							for(int x = 0; x < 16; x++){
								if(current.checkLegal(j, i, x, y, b[y][x])){
									Move m = new Move(current, b[i][j], b[y][x]) ;
									possibleMoves.add(m) ;
									//System.out.println("move " + y + " " + x + " added to list");
								}
							}
						}
					}
					
				}
			}
			
			
			
		}
		
		public void checkForMovesKanga(){
			//System.out.println("checking moves kanga");
			int lastx = lastKanga.getPosition().getxLoc();
			int lasty = lastKanga.getPosition().getyLoc();
			
			
						for(int y = 0; y < 14; y++){
							for(int x = 0; x < 16; x++){
								if(lastKanga.checkLegal(lastx, lasty, x, y, b[y][x])){
									Move m = new Move(lastKanga, lastKanga.getPosition(), b[y][x]) ;
									possibleMoves.add(m) ;
									//System.out.println("move " + y + " " + x + " added to list");
								}
							}
						}
				
			
			
			
			
		}
		
		
			private void performMove(){
				
				//System.out.println("perform move");
				Kangaroo k = best.getKangaroo() ;
				Square o = best.getOrigin() ;
				Square d = best.getDest() ;
				timeEnd = System.currentTimeMillis();
				timeTaken = timeEnd - timeStart;
				System.out.println("Time taken: " + timeTaken + " milliseconds");
				Main.getState().getLoop().addTime(timeTaken);
				currentPlayer.performMove(k, o, d) ;
				//System.out.println("move performed: " + d.getxLoc()+ " "+  d.getyLoc());
				Main.getState().getComponent().repaint();
				//if(Math.abs(o.getxLoc()- d.getxLoc()) == 1 || Math.abs(o.getyLoc()- d.getyLoc()) == 1 ) k.terminateTurn();
				if(k.getLapCounter() == 3) k.finishKangaroo();
				
				if((Math.abs(o.getxLoc()- d.getxLoc()) > 1 || Math.abs(o.getyLoc()- d.getyLoc()) > 1 ) && k.getLapCounter()<3) 
					{

						//System.out.println("New Minimax Created");
						
						
						new MiniMax(b, k, best);
						
						//Main.getState().getLoop().getBoardAr());
						
				
				
					}
			
		
		

			}
}
