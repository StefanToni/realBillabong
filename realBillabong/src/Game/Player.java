package Game;

import java.util.ArrayList;
import java.util.List;

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

	private void incrementFinishCounter()
	{
		finishCounter++;
		if(finishCounter == 5) win();
	}
	
	private void win()
	{
		System.out.println("Player " + teamCounter + " won!");
	}
	public boolean haveIWon(){
		
		int cntr = 0 ;
		
		for(int i = 0; i < 5; i++){
			if(kangaroos.get(i).getLapCounter() == 3)
			{ 
				// isn't it two laps? Yes but the first cross at the start is counted too
				cntr++ ;
			}
		}
		if(cntr == 5){
			return true ;
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
		} catch (Exception e)
		{
			System.out.println("Moving is not valid in this situation.");
		}
		
		
		Main.getState().getComponent().repaint();
	}
	
	
	public void placePiece(int x, int y){
		
	}
	
	public void movePiece(){
		Kangaroo k = selectPiece() ;
		Square s = selectMove() ;
		performMove(k, s) ;
		
		
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
		placePiece(temp.getyLoc(), temp.getxLoc());
	}
	
	//public Kangaroo selectPiece(){
	//	Kangaroo k = ;//what i click
	//	return k ;
	//}
	
	public Square selectMove(){
		Square s ;
		return s ;
	}
	
	public void performMove(Kangaroo k, Square s){
		k.getPosition().empty();
		s.fill(k);
		//input = true ;
	}
	
	
	
	

}
