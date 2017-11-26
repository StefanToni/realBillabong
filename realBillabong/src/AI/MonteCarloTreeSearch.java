package AI;
import java.util.List;

import Game.Board;

public class MonteCarloTreeSearch {
	  private static final int WIN_SCORE = 5;
	    private int level;
	    private int oponent;

	    public MonteCarloTreeSearch() {
	        this.level = 3;
	    }

	    public int getLevel() {
	        return level;
	    }

	    public void setLevel(int level) {
	        this.level = level;
	    }

	    private int getMillisForCurrentLevel() {
	        return 2 * (this.level - 1) + 1;
	    }

	    public Board findNextMove(Board board, int playerNo) {
	        long start = System.currentTimeMillis();
	        long end = start + 60 * getMillisForCurrentLevel();

	        oponent = 3 - playerNo;
	        Tree tree = new Tree();
	        Nodee rootNode = tree.getRoot();
	        rootNode.getState().setBoard(board);
	        rootNode.getState().setPlayerNo(oponent);

	        while (System.currentTimeMillis() < end) {
	            // Phase 1 - Selection
	            Nodee promisingNode = selectPromisingNode(rootNode);
	            // Phase 2 - Expansion
	            if (promisingNode.getState().getBoard().checkStatus() == Board.IN_PROGRESS)
	                expandNode(promisingNode);

	            // Phase 3 - Simulation
	            Nodee nodeToExplore = promisingNode;
	            if (promisingNode.getChildArray().size() > 0) {
	                nodeToExplore = promisingNode.getRandomChildNodee();
	            }
	            int playoutResult = simulateRandomPlayout(nodeToExplore);
	            // Phase 4 - Update
	            backPropogation(nodeToExplore, playoutResult);
	        }

	        Nodee winnerNode = rootNode.getChildWithMaxScore();
	        tree.setRoot(winnerNode);
	        return winnerNode.getState().getBoard();
	    }

	    private Nodee selectPromisingNode(Nodee rootNode) {
	        Nodee node = rootNode;
	        while (node.getChildArray().size() != 0) {
	            node = UCT.findBestNodeWithUCT(node);
	        }
	        return node;
	    }

	    private void expandNode(Nodee node) {
	        List<State> possibleStates = node.getState().getAllPossibleStates();
	        possibleStates.forEach(state -> {
	            Nodee newNode = new Nodee(state);
	            newNode.setParent(node);
	            newNode.getState().setPlayerNo(node.getState().getOpponent());
	            node.getChildArray().add(newNode);
	        });
	    }

	    private void backPropogation(Nodee nodeToExplore, int playerNo) {
	        Nodee tempNode = nodeToExplore;
	        while (tempNode != null) {
	            tempNode.getState().incrementVisit();
	            if (tempNode.getState().getPlayerNo() == playerNo)
	                tempNode.getState().addScore(WIN_SCORE);
	            tempNode = tempNode.getParent();
	        }
	    }

	    private int simulateRandomPlayout(Nodee node) {
	        Nodee tempNode = new Nodee(node);
	        State tempState = tempNode.getState();
	        int boardStatus = tempState.getBoard().checkStatus();

	        if (boardStatus == oponent) {
	            tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
	            return boardStatus;
	        }
	        while (boardStatus == Board.IN_PROGRESS) {
	            tempState.togglePlayer();
	            tempState.randomPlay();
	            boardStatus = tempState.getBoard().checkStatus();
	        }

	        return boardStatus;
	    }

}