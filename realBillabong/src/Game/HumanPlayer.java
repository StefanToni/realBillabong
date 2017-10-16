package Game;

import java.util.ArrayList;

import realBillabong.Main;

public class HumanPlayer extends Player {  
	
	
	private int team ;
	private MouseAdapter mouse ;
	private int x, y, actualX, actualY ;
	
	

	public HumanPlayer() {
		// TODO Auto-generated constructor stub
		super.setTeamCounter(super.getTeamCounter() + 1) ;
		team = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
	
	}
	
	public void placePiece(){
		x = mouse.getX();
		y = mouse.getY();
		 for(int i=0; i < 14; i++)
         {
             for(int j=0;j < 16 ;j++ )
             {

                 if((51+(20*j)<=x) && (69+(20*j)>=x) &&  (51+(20*i)<=y) && (69+(20*i)>=y) )  
                 {
                	 actualY = i;
                	 actualX = j;
                 }
                 else{
                	 System.out.println("smt went wrong...");
                 }
	}

	public void makeMove(){
		
	}



}
