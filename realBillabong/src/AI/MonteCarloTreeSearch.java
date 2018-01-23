package AI;
import java.util.List;
import AI.Nodee;
import AI.Tree;
import Game.Board;
import Game.Player;
import realBillabong.Main;


public class MonteCarloTreeSearch {
	  private static final int WIN_SCORE = 5;
	    private int level;
	    private int oponent;
	    private Board gameBoard;
	    private Player initialPlayer;

	    public MonteCarloTreeSearch() {
	        this.level = 3;
	        gameBoard = Main.getState().getLoop().getBoard();
	        initialPlayer = Main.getState().getLoop().getCurrentPlayer();
	        findNextMove(gameBoard, initialPlayer.getColor());
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
	        
	        oponent = 1;
	        Tree tree = new Tree();
	        Nodee rootNode = tree.getRoot();
	        rootNode.getState().setBoard(board);
	        rootNode.getState().setPlayerNo(playerNo);

	        while (System.currentTimeMillis() < end) {
	            // Phase 1 - Selection
	            Nodee promisingNode = selectPromisingNode(rootNode);
	            // Phase 2 - Expansion
	            if (promisingNode.getState().getBoard().checkStatusMCTS() == Board.IN_PROGRESS)
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
	        List<MState> possibleStates = node.getState().getAllPossibleStates();
	        possibleStates.forEach(state -> {
	            Nodee newNode = new Nodee(state);
	            newNode.setParent(node);
	            newNode.getState().getNextPlayer();
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
	        MState tempState = tempNode.getState();
	        int boardStatus = tempState.getBoard().checkStatusMCTS();

	        if (boardStatus > 0) {
	            tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
	            return boardStatus;
	        }
	        while (boardStatus == Board.IN_PROGRESS) {
	            tempState.getNextPlayer();
	            tempState.randomPlay();
	            boardStatus = tempState.getBoard().checkStatusMCTS();
	        }

	        return boardStatus;
	    }
	    
	    

}
