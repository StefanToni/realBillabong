package Game;

import java.util.ArrayList;

public class Player {
	
	ArrayList<Kangaroo> kangaroos ;
	int color ;
	
	public Player(int c, ArrayList<Kangaroo> roos){
		 kangaroos = new ArrayList<Kangaroo>() ;
		 kangaroos = roos ;
		 color = c ;
		
		
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
	
	public void placePiece(){
		
	}
	
	public void movePiece(){
		haveIWon() ;
		selectPiece() ;
		selectMove() ;
		performMove() ;
		
		
	}
	
	
	
	

}
