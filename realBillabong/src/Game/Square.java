package Game;

public class Square {
	
	private Kangaroo isHere ;
	private int xLoc ;
	private int yLoc ;
	private boolean isOccupied ;
	private boolean isWater ;
	private boolean isSelected;
	
	public Square(int y, int x){
		xLoc = x ;
		yLoc = y ;
		isOccupied = false ;
		isWater = false ;
		isSelected = false;
	}
	
	public Square(Square s) {
		xLoc = s.getxLoc() ;
		yLoc = s.getyLoc() ;
		isOccupied = s.isOccupied() ;
		isWater = s.isWater() ;
		if(s.getIsHere() != null){
			isHere = new Kangaroo(s.getIsHere()) ;// TODO Auto-generated constructor stub
		}
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
		if(isOccupied){
			System.out.println("there already is someone here");
		}
		else{
			isHere = k ;
			isOccupied = true ;
			k.setPosition(this) ;
		}
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
	
	public boolean getIsSelected() {
		return isSelected;
	}
	
	public void setIsSelected(boolean s) {
		isSelected = s;
	}

}
