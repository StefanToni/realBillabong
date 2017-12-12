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
		new_board = Main.getState().getLoop().getBoard().getBoardArray();
		k = kanga;
		
		next_boards = getNextMoves(k);		
	}
	
	private ArrayList<Square[][]> getNextMoves(Kangaroo k) 
	{
		
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
					k.setPosition(op);
					new_board[i][j].empty(); // to restore the board to the original gamestate before checking the move
					if(k.checkLegal(old_x, old_y, new_x, new_y,np)) {
						// to add the new move to the array of newboards
						new_board[old_x][old_y].empty();
						k.setPosition(np);
						new_board[new_x][new_y].fill(k);
		
						roosNP.add(np);
						possible_moves.add(new_board);
						
						// to restore the board to the original gamestate.
						
						new_board[new_x][new_y].empty();
						k.setPosition(op);
						new_board[old_x][old_y].fill(k); 
						
					}				
				}
			}
		}
		return possible_moves;
	}
}
