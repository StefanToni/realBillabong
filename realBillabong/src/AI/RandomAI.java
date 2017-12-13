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
	
	public RandomAI(Square[][] bo){
		System.out.println("new random ai...");
		currentPlayer = Main.getState().getLoop().getCurrentPlayer() ;
		board = bo;//Main.getState().getLoop().getBoard().getBoardArray() ;
		possibleMoves = new ArrayList<Move>() ;
		checkForMoves(); 
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
								System.out.println("move added to list");
							}
						}
					}
				}
				
			}
		}
		selectMove() ;
		
		
	}
	
	public void selectMove(){
		System.out.println("select move");
		int s = possibleMoves.size() ;
		Random rnd = new Random() ;
		int r = rnd.nextInt(s) ;
		finalMove = possibleMoves.get(r) ;
		System.out.println("move " + r + " selected");
		performMove() ;
	}
	
	public void performMove(){
		System.out.println("perform move");
		Kangaroo k = finalMove.getKangaroo() ;
		Square o = finalMove.getOrigin() ;
		Square d = finalMove.getDest() ;
		currentPlayer.performMove(k, o, d) ;
		System.out.println("move performed");
		Main.getState().getComponent().repaint();
		//if(Math.abs(o.getxLoc()- d.getxLoc()) == 1 || Math.abs(o.getyLoc()- d.getyLoc()) == 1 ) k.terminateTurn();
		if((Math.abs(o.getxLoc()- d.getxLoc()) > 1 || Math.abs(o.getyLoc()- d.getyLoc()) > 1 )) new RandomAI(board);
	}

}
