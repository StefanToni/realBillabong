package AI;

import java.util.*;
import Game.*;
import realBillabong.*;

public class MiniMax_AlphaBeta
{
	
	private ArrayList<ArrayList<Square[][]>> allMoves = new ArrayList<ArrayList<Square[][]>>();
	private Evaluator eval = new Evaluator();
	private Player currentPlayer = Main.getState().getLoop().getCurrentPlayer();
	private ArrayList<Kangaroo> roos = currentPlayer.getKangaroos();
	int colorAI = Main.getState().getLoop().getAIPlayers().get(0).getColor();
	
	private static final int DEPTH = 1;
	int bestMoveScore;
	Square[][] bestMove;
	
	/**
	 * The evaluatePosition function takes a boardArray, initial alpha, initial beta, depth, and color as parameters
	 * and computes a number that describes how advantageous for the AI a particular board is.  The function is 
	 * recursive, and every time it evaluates itself it decreases the depth by 1.  When the depth reaches 0, the
	 * function returns the result of running the evaluate function on the boardArray.  If the depth is not 0, the function
	 * generates all possible moves from that position for the color specified, and then runs evaluatePosition for 
	 * each of the boards generated by each possible move. 
	 * @param b
	 * @param alpha
	 * @param beta 
	 * @param depth
	 * @param color
	 * @return an int giving a score of how good a particular board is, with higher numbers corresponding to better boards for the AI
	 */
	
	public int evaluatePosition(Square[][] boardArray, int alpha, int beta, int depth, int color){		
		System.out.println("Begin evaluating position: depth-" + depth + "for- "+ color);
		
		Board b = Main.getState().getLoop().getBoard();		
		Kangaroo k = roos.get(0);
		Square op = k.getPosition();
		
		/*
		 * Base case: when depth is decremented to 0, evaluatePosition simply returns the result
		 * of the evaluate function
		 */
		if(depth == 0){
			int evaluation = eval.Evaluate(b, k, op, op);
			System.out.println("Evaluated to: " + evaluation);
			return evaluation;
		}
		
		//minimizing player--sequence of events that occurs
		if(color == colorAI){ 		
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
					newBeta = Math.min(newBeta, evaluatePosition(move, alpha, beta, depth -1, 1));
				}
				
				if(newBeta<= alpha) break;
			}
			return newBeta; //returns the highest score of the possible moves		
		}
		//maximizing player--this is the course of action determined if this is the maximizing player, or black
		else{ 
			
			/*
			allMoves.clear();

			for(Kangaroo kanga : roos) {
				MoveCalculator moveCalc = new MoveCalculator(kanga);
				allMoves.add(moveCalc.getNextBoards());
			}*/
		
			int newAlpha = alpha;
			for(ArrayList<Square[][]> moves : allMoves){ //for child in node
				for(Square[][] move : moves) {
					newAlpha = Math.max(newAlpha, evaluatePosition(move, alpha, beta, depth -1, 2)); 
				}
				if(beta<= newAlpha) break;
			}
			return newAlpha; //returns the highest score of the possible moves
		}
	}

	
	private void calcNextMove() {
		/*
		 * Iterate through the board, collect all possible moves of the minimizing player
		 */
		for(Kangaroo kanga : roos) {
			MoveCalculator moveCalc = new MoveCalculator(kanga);
			allMoves.add(moveCalc.getNextBoards());
		}
		
		bestMove = allMoves.get(0).get(0);
		bestMoveScore = evaluatePosition(allMoves.get(0).get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, 2);
		
		for(int i = 0; i < allMoves.size(); i++) {
			for(int j = 0; j < allMoves.get(i).size(); j++) {
				int p = evaluatePosition(allMoves.get(i).get(j), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, 2);
				
				if(p >= bestMoveScore){
					bestMove = allMoves.get(i).get(j);
					bestMoveScore = p;
				}
			}
		}
	}

	public Square[][] getNextMove() {
		calcNextMove();
		
		return bestMove;
	}
}
