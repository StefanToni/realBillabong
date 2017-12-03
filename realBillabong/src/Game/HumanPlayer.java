package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import realBillabong.Main;

public class HumanPlayer extends Player implements MouseListener{  
	
	
	private int color ;
	private MouseAdapter mouse ;
	private int x, y, actualX, actualY ;
	private Gameloop loop ;
	private String name ;
	public boolean ai=false;
	
	

	public HumanPlayer(Gameloop l) {
		// TODO Auto-generated constructor stub
		//super.setTeamCounter(super.getTeamCounter() + 1) ;
		loop =l ;
		color = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
		name = "me" ;
	
	}
	
	public void placePiece(int x, int y){

	
		
		
			//if(loop.isHasClicked()){
		Kangaroo k = new Kangaroo(color) ;
		loop.getBoard().getBoardArray()[x][y].fill(k);
		super.getKangaroos().add(k) ;
		
		
				
			
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
