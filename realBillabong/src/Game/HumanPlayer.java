package Game;

import java.util.ArrayList;

import realBillabong.Main;

public class HumanPlayer extends Player {  
	
	
	private int team ;
	private MouseAdapter mouse ;
	private int x, y, actualX, actualY ;
	private Gameloop loop ;
	
	

	public HumanPlayer(Gameloop l) {
		// TODO Auto-generated constructor stub
		super.setTeamCounter(super.getTeamCounter() + 1) ;
		loop =l ;
		team = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
	
	}
	
	public void placePiece(){

		int x = 99;
		int y = 99;
		while (x > 18 || x < 0) {
		x = loop.getMouse().getActualX() ;
		y = loop.getMouse().getActualY() ;
		}
			//if(loop.isHasClicked()){
				
		loop.getBoard().getBoardArray()[x][y].fill(new Kangaroo(1));
				
			
		}
		 
	
         


	public void makeMove(){
		
	}



}
