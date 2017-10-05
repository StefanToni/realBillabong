package Game;

public class Player {
	
	Kangaroo[] kangaroos ;
 	
	private boolean haveIWon(){
		
		int cntr = 0 ;
		
		for(int i = 0; i < 5; i++){
			if(kangaroos[i].getLapCounter() >= 3){
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
	
	private void placePiece(){
		
	}
	
	private void movePiece(){
		haveIWon() ;
		selectPiece() ;
		selectMove() ;
		performMove(  ) ;
		
		
	}
	
	

}
