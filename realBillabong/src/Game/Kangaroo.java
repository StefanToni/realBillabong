package Game;

import realBillabong.Main;

public class Kangaroo {
	
	public boolean moveable = false;
	public int lapCounter = 0 ;
	private Square position;
	public Square or ;
	private Square prevPosition ;
	private int team ;
	private int[] middleCoords = new int[2];
	
	
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
	
	public int getLapCounter()
	{
		return lapCounter;
	}
	
	public void finishKangaroo()
	{
		System.out.println("Kangaroo has finished");
		//maybe an in-game notification 
	}
	
	public void checkLap(int xFirst, int yFirst, int xNow, int yNow)
	{
		System.out.println("Lap checked");
		if(getRightLeft(xFirst, yFirst, xNow, yNow))
		{
			lapCounter++;
			System.out.println("Lapcounter incremented to " + lapCounter);
			if(lapCounter == 3)
				{
					Main.getState().getLoop().getCurrentPlayer().haveIWon();
				}
		}
		else if(getLeftRight(xFirst, yFirst, xNow, yNow))
		{
			lapCounter--;
			System.out.println("Lapcounter decreased to " + lapCounter);
		}
		
	}
	//These check if the kangaroo goes over the blue line & in which direction
	public boolean getRightLeft(int xFirst, int yFirst, int xNow, int yNow)
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
	
	public boolean getLeftRight(int xFirst, int yFirst, int xNow, int yNow)
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
	
	
	public void terminateTurn(){
		
		System.out.println("Turn terminated");
		this.moveable = false;
		Main.getState().getLoop().getCurrentPlayer().firstmove = true;
		or.empty();
		Main.getState().getLoop().getNextPlayer();
		
		
	}

	/*
	public void move(Square origin, Square dest){
				
		
		if(checkLegal(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc(), dest) && (this.moveable || Main.getState().getLoop().getCurrentPlayer().firstmove)) 
		{
			
			checkLap(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc());
			origin.empty();
			if(Main.getState().getLoop().getCurrentPlayer().firstmove == true) 
				{
					origin.fill(new Kangaroo(10));
					or = origin;
					this.moveable = true;
				}
			
			Main.getState().getLoop().getCurrentPlayer().firstmove = false;
			if(lapCounter == 3) finishKangaroo();
			else dest.fill(this);
			System.out.println("MOVED");
			
			dest.setIsSelected(true);
			if(Math.abs(origin.getxLoc()- dest.getxLoc()) == 1 || Math.abs(origin.getyLoc()- dest.getyLoc()) == 1){
			
				terminateTurn();
			}
		}
		
		else 
		{	System.out.println("MOVE NOT LEGAL");
			return;
		}
		
		
	}*/
public void move(Square origin, Square dest){
				
		
		if(checkLegal(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc(), dest) && (moveable || Main.getState().getLoop().getCurrentPlayer().firstmove)) 
		{	System.out.println("Legality checked");
			
			checkLap(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc());
			origin.empty();
			if(Main.getState().getLoop().getCurrentPlayer().firstmove == true) 
				{
					origin.fill(new Kangaroo(10));
					or = origin;
					moveable = true;
				}
			
			Main.getState().getLoop().getCurrentPlayer().firstmove = false;
			if(lapCounter == 3) finishKangaroo();
			else dest.fill(this);
			
			System.out.println("MOVED FROM: " + origin.getyLoc()+ ", " + origin.getxLoc()+   " TO: " + dest.getyLoc()+ ", " + dest.getxLoc());
			
			dest.setIsSelected(true);
			if(Math.abs(origin.getxLoc()- dest.getxLoc()) == 1 || Math.abs(origin.getyLoc()- dest.getyLoc()) == 1){
			
				terminateTurn();
			}
		}
		
		else 
		{	System.out.println("MOVE NOT LEGAL");
			return;
		}
		/*for(int i = 0; i < 13; i++ ){
			for(int j = 0; j < 15; j++){
				if( board[i][j].isOccupied() ){
					System.out.print(" " + board[i][j].getIsHere().getTeam()+ " ");
				}
				else{
					System.out.print(" X ");
					
				}
			}
			System.out.println();
		}*/
		
		
	}	
	public boolean checkLegal(int ox, int oy, int dx, int dy, Square dest)
	{
		int deltaX = dx-ox;
		int deltaY = dy-oy;
		
		if((Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0 || deltaY == 0) && !(dest.isOccupied() || dest.isWater()))
		{
			
			//System.out.println("delta = diagonal or 0");
			
			if(Math.abs(deltaX) <=1 && Math.abs(deltaY) <=1)
			{
				return true;
			}
		
			else  
			{   
				Square[][] boardCopy = Main.getState().getLoop().getBoard().getBoardArray();
				
				int midSquare_x = ox + ((dx-ox)/2);
				int midSquare_y = oy + ((dy-oy)/2);
			
				Square midSquare = boardCopy[midSquare_y][midSquare_x];
				//System.out.println("Midsquare found");
				if(midSquare.isOccupied() && onlyOne(ox, oy, dx, dy))
				{
					return true;
				}
				
			}
			
			return false;
		}
		
		else return false;
		
		
	}
	
	public boolean onlyOne(int ox, int oy, int dx, int dy)
	{	//System.out.println("Only One Tried");
		//current x&y
		
		int deltaX = dx - ox;
		int deltaY = dy - oy;
		
		Square[][] boardCopy = Main.getState().getLoop().getBoard().getBoardArray();
		
		int cx = ox;
		int cy = oy;
		int middleCounter = 0;
		
		
		//if diagonal
		if(Math.abs(deltaX) == Math.abs(deltaY))
		{
			
			
			if(deltaX<0 && deltaY>0)
			{
				while(cx!=dx && cy != dy )
				{
					cx--;
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied() && !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX>0 && deltaY<0)
			{
				while(cx!=dx && cy != dy )
				{
					cx++;
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX>0 && deltaY>0)
			{
				while(cx!=dx && cy != dy )
				{
					cx++;
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX<0 && deltaY<0)
			{
				while(cx!=dx && cy != dy )
				{
					cx--;
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			
			if(middleCounter == 1 && middleCoords[0] == (dy+oy)/2 && middleCoords[1] == (dx+ox)/2){
				//System.out.println("only one tried successful");
				return true;
				
			}
			
			else {
				//System.out.println("only one tried fail" + middleCounter);
				return false;
			}
			
		}
		
		//if not diagonal
		else
		{
			if(deltaX == 0 && deltaY > 0)
			{
				while(cy!=dy)
				{
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX == 0 && deltaY < 0)
			{
				while(cy!=dy)
				{
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX > 0 && deltaY == 0)
			{
				while(cx!=dx)
				{
					cx++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX < 0 && deltaY == 0)
			{
				while(cx!=dx)
				{
					cx--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(middleCounter == 1 && middleCoords[0] == (dy+oy)/2 && middleCoords[1] == (dx+ox)/2) return true;
			
			else return false;
			
		}	
		
	}
	
	
}
