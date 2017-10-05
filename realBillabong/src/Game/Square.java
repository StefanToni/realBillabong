package Game;

public class Square {
	
	private Kangaroo isHere ;
	private int[][] location ;
	private int xLoc ;
	private int yLoc ;
	
	public boolean isOccupied(){
		if(getIsHere() == null){
			return false ;
		}
		else{
			return true ;
		}
	}
	
	public void setIsHere(Kangaroo k){
		isHere = k ;
	}
	
	private Kangaroo getIsHere(){
		return isHere ;
	}
	
	public void fill(Kangaroo k){
		isHere = k ;
	}
	
	public void empty(){
		isHere = null ;
	}

	public int[][] getLocation() {
		return location;
	}

	public void setLocation(int[][] location) {
		this.location = location;
	}

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	

}
