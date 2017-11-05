package AI;

import Game.AIPlayer;
import Game.Player;
import Game.Board;
import Game.Gameloop;

import java.util.*;

public class AIPlayerAlfaBeta extends AIPlayer
{
	AlphaBeta alphabeta;
	
	public AIPlayerAlfaBeta(int color, int maxDepth) {
		super(color);
		alphabeta = new AlphaBeta(color, maxDepth);
	}
	
	public GameState getNextGameState(Board b) {
		GameState gameState = alphabeta.decision(b);
		return gameState;
	}
}
