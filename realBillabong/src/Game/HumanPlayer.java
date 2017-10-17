package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import realBillabong.Main;

public class HumanPlayer extends Player implements MouseListener{  
	
	
	private int team ;
	private MouseAdapter mouse ;
	private int x, y, actualX, actualY ;
	private Gameloop loop ;
	private String name ;
	
	

	public HumanPlayer(Gameloop l) {
		// TODO Auto-generated constructor stub
		super.setTeamCounter(super.getTeamCounter() + 1) ;
		loop =l ;
		team = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
		name = "me" ;
	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void placePiece(int x, int y){

	
		

			//if(loop.isHasClicked()){
				
		loop.getBoard().getBoardArray()[x][y].fill(new Kangaroo(1));
				
			
		}
		 
	
         


	public void makeMove(){
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		placePiece(mouse.getActualX(), mouse.getActualY()) ;
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
