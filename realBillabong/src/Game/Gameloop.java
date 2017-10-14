package Game;

import java.util.ArrayList;

import realBillabong.Main;

public class Gameloop {
	
	private ArrayList<Player> players = new ArrayList<Player>() ;
	private Board board ; //what shall be passed to the graphics, no more
	private  Player currentPlayer ;
	
	public Gameloop(int p, int a){
		board = Main.getState().getBoard() ;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	private void placementPhase(){
		
		int p = players.size() ;
		
		for(int i = 0 ; i < 5 ; i++){
			for(int p =0 ; i < p ; i++){
				currentPlayer  = players.get(p) ;
				currentPlayer.placePiece();
			}
		}	
	}
	
	private void gamePhase(){
		
	}
	
	
}
