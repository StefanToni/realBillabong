package AI;

import Game.Board;
import Game.GameStateMouseAdapter;

import java.util.*;

public class AlphaBeta
{
	int color;
	int maxDepth;
	Random rand;

	public AlphaBeta(int color, int maxDepth) {
		this.color = color;
		this.maxDepth = maxDepth;
		rand = new Random();
	}
	
	private float maxValue(Board b, ArrayList<GameState> state, float alpha, float beta, int depth) {
		if(depth > maxDepth)
			return eval(b, state, color);
		
		ArrayList<GameState> GameStates = b.getGameStatesAfter(color, state);
		if(GameStates.size() == 0) // TODO add draw
			return Float.NEGATIVE_INFINITY;
		
		for(int i = 0; i < GameStates.size(); i++) {
			state.add(GameStates.get(i));
			float tmp = minValue(b, state, alpha, beta, depth + 1);
			state.reGameState(state.lastIndexOf(GameStates.get(i)));
			if(tmp > alpha) {
				alpha = tmp;
			}
			
			if(beta <= alpha)
				break;
			
			//if (max >= beta)
			//	return max;
			
			//if (max > alpha)
			//	alpha = max;
		}
		
		return alpha;
	}
	
	private float minValue(Board b, ArrayList<GameState> state, float alpha, float beta, int depth) {
		if(depth > maxDepth)
			//return eval(); //TODO create eval().
		
		ArrayList<GameState> GameStates = b.getGameStatesAfter(!color, state);
		if(GameStates.size() == 0) // TODO add draw
			return Float.POSITIVE_INFINITY;
		
		for(int i = 0; i < GameStates.size(); i++) {
			state.add(GameStates.get(i));
			float tmp = maxValue(b, state, alpha, beta, depth + 1);
			state.reGameState(state.lastIndexOf(GameStates.get(i)));
			if(tmp < beta) {
				beta = tmp;
			}
			
			if(beta <= alpha)
				break;
				
				
			//if (min <= beta)
			//	return min;
			
			//if (min < beta)
			//	beta = min;
		}
		
		return beta;
	}
	
	public GameState decision(final Board b) {
		// get maximum GameState
		
		final ArrayList<GameState> GameStates = b.getGameStates(color);
		if(GameStates.size() == 0)
			return null;
 		
		Vector<Future<Float>> costs = new Vector<Future<Float>>(GameStates.size());
		costs.setSize(GameStates.size());
		
 		ExecutorService exec = Executors.newFixedThreadPool(GameStates.size());
 		try {
 		    for (int i = 0; i < GameStates.size(); i++) {
 		    	final GameState GameState = GameStates.get(i);
 		        Future<Float> result = exec.submit(new Callable<Float>() {

 		            @Override
 		            public Float call() {
 		            	ArrayList<GameState> state = new ArrayList<GameState>();
 		            	state.add(GameState);
 		            	
 		            	float tmp = minValue(b, state, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
 		            	return tmp;
 		            }
 		        });
 		        costs.set(i, result);
 		    }
 		} finally {
 		    exec.shutdown();
 		}

 		// max
 		int maxi = -1;
		float max = Float.NEGATIVE_INFINITY;
 		for(int i = 0; i < GameStates.size(); i++) {
 			float cost;
			try {
				cost = costs.get(i).get();
			} catch (Exception e) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
				}
				continue;
			}
 			if(cost >= max) {
 				if(Math.abs(cost-max) < 0.1) // add a little random element
 					if(rand.nextBoolean())
 						continue;

 				max = cost;
 				maxi = i;
 			}
 		}
 		
 		return GameStates.get(maxi);
	}
	
	private float eval(Board b, ArrayList<GameState> GameStates, int currentColor) {
		// TODO Add heuristics.
	}
}
