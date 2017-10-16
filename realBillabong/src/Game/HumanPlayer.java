package Game;

import java.util.ArrayList;

public class HumanPlayer extends Player {  
	
	
	private int team ;
	
	

	public HumanPlayer() {
		// TODO Auto-generated constructor stub
		super.setTeamCounter(super.getTeamCounter() + 1) ;
		team = super.getTeamCounter() ;
	}

	public void makeMove(){
		
	}

}
