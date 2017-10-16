package realBillabong;

import Game.Board;
import Game.MouseAdapter;

public interface GameState {
	
	public void render() ;
	public void changeState(GameState s) ;
	public void update() ;
	public MouseAdapter getMouse() ;
	//public Board getBoard();
	

}
