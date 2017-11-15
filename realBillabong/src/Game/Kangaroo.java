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
	
	private void checkLap(int xFirst, int yFirst, int xNow, int yNow)
	{
		if(getRightLeft(xFirst, yFirst, xNow, yNow))
		{
			setLapCounter(lapCounter++);
		}
		else if(getLeftRight(xFirst, yFirst, xNow, yNow))
		{
			setLapCounter(lapCounter--);
		}
		
	}
	//These check if the kangaroo goes over the blue line & in which direction
	private boolean getRightLeft(int xFirst, int yFirst, int xNow, int yNow)
	{ //13& 15
		if(xFirst>7 && xNow<=7 && yFirst>5 && yNow>5)
		{
			return true;
		}
		
		else if(xFirst>7 && xNow<=7 && yFirst<=6 && yNow>=8)
		{
			return true;
		}
		
		else if(xFirst>7 && xNow<=7 && yFirst>=8 && yNow<=6)
		{
			return true;
		}
		
		else return false;
	}
	
	private boolean getLeftRight(int xFirst, int yFirst, int xNow, int yNow)
	{ //13& 15
		if(xNow>7 && xFirst<=7 && yNow>5 && yFirst>5)
		{
			return true;
		}
		
		else if(xNow>7 && xFirst<=7 && yNow<=6 && yFirst>=8)
		{
			return true;
		}
		
		else if(xNow>7 && xFirst<=7 && yNow>=8 && yFirst<=6)
		{
			return true;
		}
		
		else return false;
	}
	
	
	
	public void walk(Square origin, Square dest){
		if(!(dest.isOccupied() || dest.isWater())) {
			System.out.println("Trying to move");
			if((Math.abs(dest.getxLoc()-origin.getxLoc()) == 1) || // horizontal move
				(Math.abs(dest.getyLoc()-origin.getyLoc()) == 1) || // vertical move
				(Math.abs(dest.getxLoc()-origin.getxLoc()) == 1 && Math.abs(dest.getyLoc()-origin.getyLoc()) == 1)) // diagonal move
			{	
				System.out.println("Moving");
				checkLap(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc());
				origin.empty();
				dest.fill(this);
				//this.setPosition(dest); // Also covered in Square class, method Fill
			}
			else {
				return;
			}
		}
		else {
			return;
		}
	}
	
	public void jump(Square origin, Square dest){
		int midSquare_x = origin.getxLoc()+((dest.getxLoc()-origin.getxLoc())/2);
		int midSquare_y = origin.getyLoc()+((dest.getyLoc()-origin.getyLoc())/2);

		Square midSquare = new Square(midSquare_x, midSquare_y);
		
		if(!(dest.isOccupied() || dest.isWater())) {
			if(midSquare.isOccupied()) 
			{   System.out.println("Moving");
				checkLap(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc());
				origin.empty();
				dest.fill(this);
				//this.setPosition(dest);// Also covered in Square class, method Fill
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
