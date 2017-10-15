package Game;

public class Square {
	
	private Kangaroo isHere ;
	private int xLoc ;
	private int yLoc ;
	private boolean isOccupied ;
	
	public Square(int y, int x){
		xLoc = x ;
		yLoc = y ;
		isOccupied = false ;
	}
	
	public boolean isOccupied(){
		return isOccupied ;
	}
	
	public void setIsOccupied(boolean o){
		isOccupied = o ;
	}
	
	public void setIsHere(Kangaroo k){
		isHere = k ;
	}
	
	public Kangaroo getIsHere(){
		return isHere ;
	}
	
	public void fill(Kangaroo k){
		isHere = k ;
		isOccupied = true ;
	}
	
	public void empty(){
		isHere = null ;
		isOccupied = false ;
	}


	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int x) {
		xLoc = x;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int y) {
		yLoc = y;
	}
	

}
