package Game;

public class Square {
	
	private Kangaroo isHere ;
	private int xLoc ;
	private int yLoc ;
	private boolean isOccupied ;
	private boolean isWater ;
	
	public Square(int y, int x){
		xLoc = x ;
		yLoc = y ;
		isOccupied = false ;
		isWater = false ;
	}
	
	public boolean isWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
		if (isWater) {
			fill(new Kangaroo(99));
		}
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
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
		k.setPosition(this) ;
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
