package Game;

import java.util.ArrayList;

import realBillabong.Main;

public class Gameloop {
	
	private ArrayList<Player> players ;
	private Board board ; //what shall be passed to the graphics, no more
	private Player currentPlayer ;
	private MouseAdapter mouse;
	private boolean hasClicked = false ;
	
	public boolean isHasClicked() {
		return hasClicked;
	}

	public void setHasClicked(boolean hasClicked) {
		this.hasClicked = hasClicked;
	}

	public MouseAdapter getMouse() {
		return mouse;
	}

	public void setMouse(MouseAdapter mouse) {
		this.mouse = mouse;
	}

	public Gameloop(int p, int a){
		mouse = Main.getState().getMouse() ;
		board = new  Board() ;
		players = new ArrayList<Player>() ;
		for(int i = 0; i < p ; i++){
			HumanPlayer hPlayer = new HumanPlayer(this) ;
			players.add(hPlayer) ;
		}
		for(int i = 0; i < a ; i++){
			AIPlayer aiPlayer = new AIPlayer() ;
			players.add(aiPlayer) ;
		}
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void placementPhase(){
		
		System.out.println("start placing");
		
		for(int i = 0 ; i < 5 ; i++){
			for(int p = 0 ; p < players.size() ; p++){
				
				currentPlayer  = players.get(p) ;
				currentPlayer.placePiece() ;
				
			}
		}
		gamePhase() ;
	}
	
	public void gamePhase(){
		System.out.println("start playing");
	}
	
	
}
