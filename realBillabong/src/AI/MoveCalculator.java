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
	
	public ArrayList<Square[][]> getNextBoards() {
		return next_boards;
	}
	
	
	public MoveCalculator(Kangaroo kanga) 
	{
		new_board = Main.getState().getLoop().getBoard().getBoardArray();
		k = kanga;
		r = Main.getState().getLoop().getBoard().getReferee();
		kangaroos = Main.getState().getLoop().getBoard().getKangaroos();
		
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
					new_board[i][j].fill(k); // to get new coordinates
					np = k.getPosition();
					new_x = np.getxLoc();
					new_y = np.getyLoc();
					new_board[i][j].empty(); // to restore the board to the original gamestate before checking the move
					
					if(k.checkLegal(old_x, old_y, new_x, new_y,np)) {
						new_board[i][j].fill(k); // to add the new move to the array of newboards
						possible_moves.add(new_board);
						new_board[i][j].empty(); // to restore the board to the original gamestate.
					}				
				}
			}
		}
		return possible_moves;
	}
}
