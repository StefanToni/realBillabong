package Game;

public class Square {
	
	private Kangaroo isHere ;
	private int[][] location ;
	private int xLoc ;
	private int yLoc ;
	
	private boolean isOccupied(){
		if(getIsHere() == null){
			return false ;
		}
		else{
			return true ;
		}
	}
	
	private void setIsHere(Kangaroo k){
		isHere = k ;
	}
	
	private Kangaroo getIsHere(){
		return isHere ;
	}
	
	private void fill(Kangaroo k){
		isHere = k ;
	}
	
	private void empty(){
		isHere = null ;
	}

}
