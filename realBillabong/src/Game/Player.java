package Game;

import java.util.ArrayList;

public class Player {
	
	private ArrayList<Kangaroo> kangaroos ;
	private int color ;
	private static int teamCounter  ;
	private String name ;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player(int c, ArrayList<Kangaroo> roos){
		 kangaroos = new ArrayList<Kangaroo>() ;
		 kangaroos = roos ;
		 teamCounter++ ;
		 color = teamCounter ;
		 name = "x" ;
		 
		
	}
	
	public Player(){
		kangaroos = new ArrayList<Kangaroo>() ;
		teamCounter++ ;
		color = teamCounter ;
	}
 	
	public ArrayList<Kangaroo> getKangaroos() {
		return kangaroos;
	}

	public void setKangaroos(ArrayList<Kangaroo> kangaroos) {
		this.kangaroos = kangaroos;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public static int getTeamCounter() {
		return teamCounter;
	}

	public static void setTeamCounter(int teamCounter) {
		Player.teamCounter = teamCounter;
	}

	public boolean haveIWon(){
		
		int cntr = 0 ;
		
		for(int i = 0; i < 5; i++){
			if(kangaroos.get(i).getLapCounter() >= 3){
				cntr++ ;
			}
		}
		if(cntr == 5){
			return true ;
		}
		else{
			return false ;
		}
	}
	
	public void performMove(Kangaroo k, Square o, Square d){
		
		k.setPosition(d) ;
		o.empty() ;
		d.fill(k) ;
	}
	
	public void placePiece(int x, int y){
		
	}
	
	public void movePiece(){
		haveIWon() ;
		selectPiece() ;
		selectMove() ;
		performMove() ;
		
		
	}
	
	
	
	

}
