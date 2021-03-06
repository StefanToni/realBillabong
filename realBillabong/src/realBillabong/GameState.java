package realBillabong;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Game.Board;
import Game.Gameloop;
import Game.MouseAdapter;
import Graphics.BoardComponent;

public interface GameState {
	
	public void render() ;
	public void changeState(GameState s) ;
	public void update() ;
	public MouseAdapter getMouse() ;
	//public Board getBoard();
	public Gameloop getLoop();
	public BoardComponent getComponent() ;
	public JPanel getPane() ;
	public void deleteMouse();
	

}
