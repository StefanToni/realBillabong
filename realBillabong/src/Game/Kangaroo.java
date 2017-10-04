package Game;

public class Kangaroo {
	
	private int lapCounter = 0 ;
	private Square position ;
	private Square prevPosition ;
	private int team ;
	
	
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
	
	private int getLapCounter(){
		return lapCounter ;
	}
	
	private void setLapCounter(int x){
		lapCounter = x ;
	}
	
	private void finsishedLap(){
		lapCounter++ ;
	}
	
	private void walk(Square dest){
		
	}
	
	private void jump(Square dest){
		
	}
	
	

}
