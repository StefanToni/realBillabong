package realBillabong;

import Game.Board;

public interface GameState {
	
	public void render() ;
	public void changeState(GameState s) ;
	public void update() ;
	//public Board getBoard();
	

}
