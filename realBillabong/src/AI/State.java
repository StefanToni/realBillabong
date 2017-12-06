package AI;

import java.util.ArrayList;

import java.util.List;

import Game.Board;
import Game.Kangaroo;
import Game.Position;
import Game.Square;
import realBillabong.Main;

	public class State {
	    private Board board;
	    private int playerNo;
	    private int visitCount;
	    private double winScore;
	    private ArrayList<Kangaroo> kangaroos;
	    

	   
	    public State(State state) {
	        this.board = Main.getState().getLoop().getBoard();
	        this.playerNo = state.getPlayerNo();
	        this.visitCount = state.getVisitCount();
	        this.winScore = state.getWinScore();
	        this.setKangaroos(Main.getState().getLoop().getPlayers().get(2).getKangaroos());
	        
	    }
	    public State() {
	        board = new Board();
	    }

	    public State(Board board) {
	        this.board = Main.getState().getLoop().getBoard(); // dasibogi
	    }

	    Board getBoard() {
	        return board;
	    }

	    void setBoard(Board board) {
	        this.board = board;
	    }

	    int getPlayerNo() {
	        return playerNo;
	    }

	    void setPlayerNo(int playerNo) {
	        this.playerNo = playerNo;
	    }

	    int getOpponent() {
	        return 3 - playerNo;
	    }

	    public int getVisitCount() {
	        return visitCount;
	    }

	    public void setVisitCount(int visitCount) {
	        this.visitCount = visitCount;
	    }

	    double getWinScore() {
	        return winScore;
	    }

	    void setWinScore(double winScore) {
	        this.winScore = winScore;
	    }

	    public List<State> getAllPossibleStates() {
	        List<State> possibleStates = new ArrayList<>();
	        List<Square[][]> availablePositions = this.board.getEmptyPositions();
	        availablePositions.forEach(p -> {
	            State newState = new State(this.board);
	            newState.setPlayerNo(3 - this.playerNo);
	            //Main.getState().getLoop().getCurrentPlayer().performMove(currentKangaroo, currentKangaroo.getPosition(), currentSquare);
	            newState.getBoard().performMove(newState.getPlayerNo(), p);
	            possibleStates.add(newState);
	        });
	        return possibleStates;
	    }

	    void incrementVisit() {
	        this.visitCount++;
	    }

	    void addScore(double score) {
	        if (this.winScore != Integer.MIN_VALUE)
	            this.winScore += score;
	    }

	    void randomPlay() {
	        List<Square[][]> availablePositions = this.board.getEmptyPositions();
	        int totalPossibilities = availablePositions.size();
	        int selectRandom = (int) (Math.random() * ((totalPossibilities - 1) + 1));
	        //this.board.performMove(this.playerNo, availablePositions.get(selectRandom));
	        Main.getState().getLoop().getPlayers().get(2).performMove(k, o, availablePositions.get(selectRandom));
	    }

	    void togglePlayer() {
	        this.playerNo = 3 - this.playerNo;
	    }

		public ArrayList<Kangaroo> getKangaroos() {
			return kangaroos;
		}

		public void setKangaroos(ArrayList<Kangaroo> kangaroos) {
			this.kangaroos = kangaroos;
		}
	}
	
	


