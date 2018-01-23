package AI;

import java.util.ArrayList;

import Game.*;
import realBillabong.*;

public class MoveCalculator
{
	private Square[][] original_board;
	private Square[][] new_board;
	private ArrayList<Square[][]> next_boards;
	private Kangaroo k, r;
	private Kangaroo[] kangaroos;
	private ArrayList<Square> roosNP;
	
	
	public ArrayList<Square[][]> getNextBoards() {
		return next_boards;
	}
	
	public ArrayList<Square> getNewPositions(){
		return roosNP;
	}
	
	public MoveCalculator(Kangaroo kanga) 
	{
		new_board = Main.getState().getLoop().getBoardAr();
		k = kanga;
		
		next_boards = getNextMoves(k);		
	}
	
	private ArrayList<Square[][]> getNextMoves(Kangaroo k) 
	{
		roosNP = new ArrayList<Square>() ;
		ArrayList<Square[][]> possible_moves = new ArrayList<Square[][]>();
		
		Square op = k.getPosition();
		int old_x = op.getxLoc();
		int old_y = op.getyLoc();
		
		Square np = new Square(0,0);
		int new_x;
		int new_y;
		
		for(int i = 0 ; i < 14 ; i++){
			for(int j = 0 ; j < 16 ; j++) {
				if(!(new_board[i][j].isOccupied() || new_board[i][j].isWater())) {
					//new_board[i][j].fill(k); // to get new coordinates
					//np = k.getPosition();
					new_x = new_board[i][j].getxLoc();
					new_y = new_board[i][j].getyLoc();
					//k.setPosition(op);
					//new_board[i][j].empty(); // to restore the board to the original gamestate before checking the move
					if(k.checkLegal(old_x, old_y, new_x, new_y, new_board[new_y][new_x])) {
						// to add the new move to the array of newboards
						new_board[old_y][old_x].empty();
						k.setPosition(new_board[new_y][new_x]);
						new_board[new_y][new_x].fill(k);
						Square[][] tempB = new Square[14][16] ;
						for(int m = 0; m < 14 ; m++){
							for(int n = 0; n < 16 ; n++){
								tempB[m][n] = new_board[m][n];
							}
						}
						roosNP.add(tempB[new_y][new_x]);
						possible_moves.add(tempB);
						
						// to restore the board to the original gamestate.
						
						new_board[new_y][new_x].empty();
						k.setPosition(op);
						new_board[old_y][old_x].fill(k); 
						
					}				
				}
			}
		}
		return possible_moves;
	}
}
