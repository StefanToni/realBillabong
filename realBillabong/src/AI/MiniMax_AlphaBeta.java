package AI;

import java.util.*;
import Game.*;
import realBillabong.*;

public class MiniMax_AlphaBeta
{
	
	private ArrayList<ArrayList<Square[][]>> allAIMoves = new ArrayList<ArrayList<Square[][]>>();
	private ArrayList<ArrayList<Square[][]>> allHumanMoves = new ArrayList<ArrayList<Square[][]>>();
	private Evaluator eval = new Evaluator();
	private Player currentPlayer = Main.getState().getLoop().getCurrentPlayer();
	private int currentPlayerColor = Main.getState().getLoop().getCurrentPlayer().getColor();
	private Player nextPlayer = Main.getState().getLoop().getPlayers().get(0);
	private ArrayList<Kangaroo> AIroos = currentPlayer.getKangaroos();
	private ArrayList<Kangaroo> Humanroos = nextPlayer.getKangaroos();
	int colorAI = Main.getState().getLoop().getAIPlayers().get(0).getColor();
	private Square[][] successorMove;
	
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
		Kangaroo k = AIroos.get(0);
		Square op = k.getPosition();
		
		/*
		 * Base case: when depth is decremented to 0, evaluatePosition simply returns the result
		 * of the evaluate function
		 */
		if(depth == 0){
			if(color == colorAI) {
				int evaluation = eval.Evaluate(successorMove, k, op, op);
				System.out.println("Evaluated to: " + evaluation);
				return evaluation;
			}
			else {
				int evaluation = eval.Evaluate(successorMove, k, op, op);
				System.out.println("Evaluated to: " + evaluation);
				return evaluation;
			}
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
			for(ArrayList<Square[][]> moves : allAIMoves){ //for child in node
				for(Square[][] move : moves) {
					successorMove = move;
					newBeta = Math.min(newBeta, evaluatePosition(move, alpha, beta, depth -1, currentPlayer.getColor()+1));
				}
				
				if(newBeta<= alpha) break;
			}
			return newBeta; //returns the highest score of the possible moves		
		}
		//maximizing player--this is the course of action determined if this is the maximizing player, or black
		else{ 
		
			int newAlpha = alpha;
			for(ArrayList<Square[][]> moves : allHumanMoves){ //for child in node
				for(Square[][] move : moves) {
					successorMove = move;
					newAlpha = Math.max(newAlpha, evaluatePosition(move, alpha, beta, depth -1, currentPlayer.getColor())); 
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
		for(Kangaroo kanga : AIroos) {
			MoveCalculator moveCalc = new MoveCalculator(kanga);
			allAIMoves.add(moveCalc.getNextBoards());
		}
		
		/*
		 * Iterate through the board, collect all possible moves of the maximizing player
		 */
		for(Kangaroo kanga : Humanroos) {
			MoveCalculator moveCalc = new MoveCalculator(kanga);
			allHumanMoves.add(moveCalc.getNextBoards());
		}
		
		bestMove = allAIMoves.get(0).get(0);
		bestMoveScore = evaluatePosition(allAIMoves.get(0).get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, currentPlayer.getColor());
		
		for(int i = 0; i < allAIMoves.size(); i++) {
			for(int j = 0; j < allAIMoves.get(i).size(); j++) {
				int p = evaluatePosition(allAIMoves.get(i).get(j), Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH, currentPlayer.getColor());
				
				if(p >= bestMoveScore){
					bestMove = allAIMoves.get(i).get(j);
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
