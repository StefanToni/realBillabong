package AI;

import java.util.ArrayList;
import java.util.Random;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class RandomAI {
	
	Player currentPlayer ;
	Square[][] board ;
	ArrayList<Move> possibleMoves ;
	Move finalMove ;
	Kangaroo lastKanga = null;
	
	public RandomAI(Square[][] bo){
		System.out.println("new random ai...");
		currentPlayer = Main.getState().getLoop().getCurrentPlayer() ;
		board = bo;//Main.getState().getLoop().getBoard().getBoardArray() ;
		possibleMoves = new ArrayList<Move>() ;
		checkForMoves(); 
	}
	
	public RandomAI(Square[][] bo, Kangaroo lastKanga){
		System.out.println("new random ai...");
		currentPlayer = Main.getState().getLoop().getCurrentPlayer() ;
		board = bo;//Main.getState().getLoop().getBoard().getBoardArray() ;
		this.lastKanga = lastKanga;
		possibleMoves = new ArrayList<Move>() ;
		checkForMovesKanga(); 
	}
	
	
	
	public void checkForMoves(){
		System.out.println("checking moves");
		Kangaroo current; 
		
		for(int i = 0; i < 14; i++){
			for(int j = 0 ;  j < 16; j++){
				if(board[i][j].isOccupied() && board[i][j].getIsHere().getTeam() == currentPlayer.getColor()){
					current = board[i][j].getIsHere() ;
					for(int y = 0; y < 14; y++){
						for(int x = 0; x < 16; x++){
							if(current.checkLegal(j, i, x, y, board[y][x])){
								Move m = new Move(current, board[i][j], board[y][x]) ;
								possibleMoves.add(m) ;
								//System.out.println("move " + y + " " + x + " added to list");
							}
						}
					}
				}
				
			}
		}
		selectMove() ;
		
		
	}
	
	public void checkForMovesKanga(){
		System.out.println("checking moves kanga");
		int lastx = lastKanga.getPosition().getxLoc();
		int lasty = lastKanga.getPosition().getyLoc();
		
		
					for(int y = 0; y < 14; y++){
						for(int x = 0; x < 16; x++){
							if(lastKanga.checkLegal(lastx, lasty, x, y, board[y][x])){
								Move m = new Move(lastKanga, lastKanga.getPosition(), board[y][x]) ;
								possibleMoves.add(m) ;
								//System.out.println("move " + y + " " + x + " added to list");
							}
						}
					}
			
		
		selectMove();
		
		
	}
	
	public void selectMove(){
		System.out.println("select move");
		int s = possibleMoves.size() ;
		Random rnd = new Random() ;
		int r = rnd.nextInt(s) ;
		finalMove = possibleMoves.get(r) ;
		System.out.println("move " + r + " selected");
		System.out.println("Moving from: "+finalMove.getOrigin().getyLoc() +" "+finalMove.getOrigin().getxLoc()+" to:  "+ finalMove.getDest().getyLoc()+" "+finalMove.getDest().getxLoc());
		pMove() ;
	}
	
	public void pMove(){
		//System.out.println("perform move");
		Kangaroo k = finalMove.getKangaroo() ;
		Square o = finalMove.getOrigin() ;
		Square d = finalMove.getDest() ;
		currentPlayer.performMove(k, o, d) ;
		System.out.println("move pformed");
		Main.getState().getComponent().repaint();
		
		//if(Math.abs(o.getxLoc()- d.getxLoc()) == 1 || Math.abs(o.getyLoc()- d.getyLoc()) == 1 ) k.terminateTurn();
		
		if((Math.abs(o.getxLoc()- d.getxLoc()) > 1 || Math.abs(o.getyLoc()- d.getyLoc()) > 1 )) 
			{
			
			System.out.println("New RAI made");
			Main.getState().getComponent().repaint();

			Main.getState().getComponent().repaint();

			Main.getState().getComponent().repaint();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Not sleeping");
				e.printStackTrace();
			}
			
			new RandomAI(board, k);
			}
		
	
		
		
	}

}
