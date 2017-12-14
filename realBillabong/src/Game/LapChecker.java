package Game;

import realBillabong.Main;

public class LapChecker {
	public LapChecker()
	{
		
	}
	
	public int checkLap(int xFirst, int yFirst, int xNow, int yNow, int lapCounter)
	{
		//System.out.println("Lap checked");
		if(getRightLeft(xFirst, yFirst, xNow, yNow))
		{
			lapCounter++;
			//System.out.println("Lapcounter incremented to " + lapCounter);
			
			
		}
		else if(getLeftRight(xFirst, yFirst, xNow, yNow))
		{
			lapCounter--;
			//System.out.println("Lapcounter decreased to " + lapCounter);
			
		}
		
		return lapCounter;
		
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
}
