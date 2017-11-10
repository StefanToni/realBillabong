package Game;

public class Kangaroo {
	
	private int lapCounter = 0 ;
	private Square position ;
	private Square prevPosition ;
	private int team ;
	
	public Kangaroo(int t){
		team = t ;
	}
	
	
	public Square getPosition() {
		return position;
	}

	public void setPosition(Square position) {
		this.position = position;
	}

	public Square getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(Square prevPosition) {
		this.prevPosition = prevPosition;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public int getLapCounter(){
		return lapCounter ;
	}
	
	private void setLapCounter(int x){
		lapCounter = x ;
	}
	
	private void finsishedLap(){
		lapCounter++ ;
	}
	
	private void walk(Square origin, Square dest){
		if(!(dest.isOccupied() || dest.isWater())) {
			if((Math.abs(dest.getxLoc()-origin.getxLoc()) == 1) || // horizontal move
				(Math.abs(dest.getyLoc()-origin.getyLoc()) == 1) || // vertical move
				(Math.abs(dest.getxLoc()-origin.getxLoc()) == 1 && Math.abs(dest.getyLoc()-origin.getyLoc()) == 1)) // diagonal move
			{
				this.setPosition(dest);
			}
			else {
				return;
			}
		}
		else {
			return;
		}
	}
	
	private void jump(Square origin, Square dest){
		Square midSquare = null;
		midSquare.setxLoc(origin.getxLoc()+((dest.getxLoc()-origin.getxLoc())/2));
		midSquare.setyLoc(origin.getyLoc()+((dest.getyLoc()-origin.getyLoc())/2));
		
		if(!(dest.isOccupied() || dest.isWater())) {
			if(midSquare.isOccupied()) {
				this.setPosition(dest);
			}
			else {
				return;
			}
		}
		else {
			return;
		}
	}
	
	

}
