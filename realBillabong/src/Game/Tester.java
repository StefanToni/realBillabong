package Game;

import realBillabong.MainMenu;

public class Tester {

	public Tester(int tries)
	{
		for(int i = tries; i>0; i--)
		{
			tryGame();
		}
	}
	
	public void tryGame()
	{
		new MainMenu();
		
	}
	
}
