package Game;

import java.util.ArrayList;
import java.util.List;

import AI.MiniMax;
import realBillabong.Main;

public class Player {
	
	public boolean firstmove = true;
	private ArrayList<Kangaroo> kangaroos ;
	private int color ;
	private static int teamCounter  ;
	private String name ;
	private boolean input = false ;
	private Kangaroo selectedK ;
	private Square selectedS ;
	private int finishCounter = 0 ;
	public boolean ai;
	private Board b;
	public int moveCounter = 0;
	public int tt = 0;
	
	public Board getB() {
		return b;
	}
	public int getMoveCounter()
	{
		return moveCounter;
	}
	public void setB(Board b) {
		this.b = b;
	}
	public boolean getAI()
	{
		return ai;
	}
	public void setAI(Boolean x)
	{
		ai = x;
	}
	
	public boolean isInput() {
		return input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player(int c, ArrayList<Kangaroo> roos){
		 kangaroos = new ArrayList<Kangaroo>() ;
		 kangaroos = roos ;
		 teamCounter++ ;
		 color = teamCounter ;
		 name = "x" ;
		
	}
	
	public Player(){
		kangaroos = new ArrayList<Kangaroo>() ;
		teamCounter++ ;
		color = teamCounter ;

	}
 	
	public ArrayList<Kangaroo> getKangaroos() {
		return kangaroos;
	}

	public void setKangaroos(ArrayList<Kangaroo> kangaroos) {
		this.kangaroos = kangaroos;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public static int getTeamCounter() {
		return teamCounter;
	}

	public static void setTeamCounter(int teamCounter) {
		Player.teamCounter = teamCounter;
	}

	public void incrementFinishCounter()
	{
		finishCounter++;
		
	}
	
	private void win()
	{
		System.out.println("Player " + teamCounter + " won!");
	}
	
	public boolean haveIWon(){
		
		if(finishCounter == 5 || kangaroos.size() == 0) 
			{
				win(); 
				return true;
			}
		
		
		else{
			return false ;
		}
	}
	
	
	public void performMove(Kangaroo k, Square o, Square d){
		System.out.println("Trying move");
		try
		{
			k.move(o, d);
			moveCounter++;
			Main.getState().getLoop().incrementCounter();
			System.out.println("perform move executed ");
		} catch (Exception e)
		
		{
			
			System.out.println("Moving is not valid in this situation.");

			
			//new MiniMax(Main.getState().getLoop().getBoardAr());
			k.terminateTurn();
		}
		
		
		Main.getState().getComponent().repaint();
	}
	
	
	public void placePiece(int x, int y){
		
	}
	
	
	
	public void placeRoo()
	{	System.out.println("I'm being used");
		List<Square> smartOptions = new ArrayList<Square>();
		List<Square> options = Main.getState().getLoop().getBoard().getEmptyPositions();
		for(Square squ:options)
		{
			 if(squ.getxLoc()> 7 && squ.getyLoc() > 6)
			 {
				 smartOptions.add(squ);
			 }
		}
		
		Square temp = smartOptions.get((int)(Math.random()*(smartOptions.size()-1)));
		placePiece(temp.getxLoc(), temp.getyLoc());
	}
	
	public void incrementTurns()
	{
		tt++;
		Main.getState().getLoop().incrementTurns();
	}
	
	public int getPlayerTurns()
	{
		return tt;
	}
	//public Kangaroo selectPiece(){
	//	Kangaroo k = ;//what i click
	//	return k ;
	//}
	
	
//	public void performMove(Kangaroo k, Square s){
//		k.move(k.getPosition(), s);
//		
//		Main.getState().getComponent().repaint();
//		//input = true ;
//	}
	
	public void deleteRoo(Kangaroo kangaroo) {
		for(int i = 0; i< kangaroos.size(); i++)
		{
			if(kangaroos.get(i) == kangaroo)
			{
				kangaroos.remove(i);
				kangaroo = null;
			}
		}
		
	}
	
	
	
	

}
