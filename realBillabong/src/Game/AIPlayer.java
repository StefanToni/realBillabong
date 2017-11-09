package Game;

import realBillabong.Main;

public class AIPlayer extends Player {
	
	private int color;
	private Gameloop loop ;
	private MouseAdapter mouse ;
	private String name ;
	
	public AIPlayer(int color) {
		this.color = color;
	}
	
	public AIPlayer(Gameloop l) {
		// TODO Auto-generated constructor stub
		//super.setTeamCounter(super.getTeamCounter() + 1) ;
		loop =l ;
		color = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
		name = "AI" ;
	
	}

}
