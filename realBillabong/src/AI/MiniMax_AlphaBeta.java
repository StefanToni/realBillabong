package AI;

import java.util.*;
import Game.*;
import realBillabong.*;

public class MiniMax_AlphaBeta
{
	
	
	Evaluator eval = new Evaluator();
	
	/**
	 * The evaluatePosition function takes a board, initial alpha, initial beta, depth, and color as parameters
	 * and computes a number that describes how advantageous for the AI a particular board is.  The function is 
	 * recursive, and every time it evaluates itself it decreases the depth by 1.  When the depth reaches 0, the
	 * function returns the result of running the evaluate function on the board.  If the depth is not 0, the function
	 * generates all possible moves from that position for the color specified, and then runs evaluatePosition for 
	 * each of the boards generated by each possible move. 
	 * @param b
	 * @param alpha
	 * @param beta 
	 * @param depth
	 * @param color
	 * @return an int giving a score of how good a particular board is, with higher numbers corresponding to better boards for the AI
	 */
	public int evaluatePosition(Square[][] boardArray, int alpha, int beta, int depth, boolean color){		
		System.out.println("Begin evaluating position: depth-" + depth + "for- "+ color);
		
		Board b = Main.getState().getLoop().getBoard();
		
		
		Player currentPlayer = Main.getState().getLoop().getCurrentPlayer();
		ArrayList<Kangaroo> roos = currentPlayer.getKangaroos();
		Kangaroo k = roos.get(0);
		Square op = k.getPosition();

		/*
		 * Base case: when depth is decremented to 0, evaluatePosition simply returns the result
		 * of the evaluate function
		 */
		if(depth == 0){
			int evaluation = eval.Evaluate(b, k, op, np);
			System.out.println("Evaluated to: " + evaluation);
			return evaluation;
		}
		
		if(color == false){ //minimizing player--sequence of events that occurs
			ArrayList<ArrayList<Square[][]>> allMoves = new ArrayList<ArrayList<Square[][]>>(); //this arraylist keeps track of possible moves from the given position
			
			/*
			 * Iterate through the board, collect all possible moves of the minimizing player
			 */
			for(Kangaroo kanga : roos) {
				MoveCalculator moveCalc = new MoveCalculator(kanga);
				allMoves.add(moveCalc.getNextBoards());
			}
			/*
			 * This for loop goes through all possible moves and calls evaluatePosition on them,
			 * changing the color.  Alpha-beta pruning is used here to remove obviously poor moves.
			 * These are determined by the variables alpha and beta.  All moves where the beta,
			 * which is the score of the minimizing (in this case white player) is less than or
			 * equal to alpha are discarded.  
			 */
			int newBeta = beta;
			for(ArrayList<Square[][]> moves : allMoves){ //for child in node
				for(Square[][] move : moves) {					
					newBeta = Math.min(newBeta, evaluatePosition(move, alpha, beta, depth -1, !color));
				}
				
				if(newBeta<= alpha) break;
			}
			return newBeta; //returns the highest score of the possible moves
		}else{ //maximizing player--this is the course of action determined if this is the maximizing player, or black
			ArrayList<ArrayList<Square[][]>> allMoves = new ArrayList<ArrayList<Square[][]>>();
			/*
			 * These for loops iterate through the board and add all possible pieces to the ArrayList of
			 * moves.  
			 */
			for(Kangaroo kanga : roos) {
				MoveCalculator moveCalc = new MoveCalculator(kanga);
				allMoves.add(moveCalc.getNextBoards());
			}
		
			/*
			 * This for loop cycles through all possible moves and 
			 * calculates a new alpha if the successor board evaluates
			 * to a higher number than what is currently the highest score,
			 * which is stored in alpha.  
			 */
			int newAlpha = alpha;
			for(ArrayList<Square[][]> moves : allMoves){ //for child in node
				newAlpha = Math.max(newAlpha, evaluatePosition(successorBoard, alpha, beta, depth -1, !color)); //think about how to change moves
				if(beta<= newAlpha) break;
			}
			return newAlpha; //returns the highest score of the possible moves
		}
	}

}
