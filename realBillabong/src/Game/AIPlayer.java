package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import realBillabong.Main;


import AI.*;
import realBillabong.Main;

public class AIPlayer extends Player implements MouseListener{
	
	private int color;
	private Gameloop loop ;
	private MouseAdapter mouse ;
	private String name ;
	public boolean ai=true;
	
	public AIPlayer(int color) {
		this.color = color;
		
	}
	
	
	

	
	
	public AIPlayer(Gameloop l) {
		// TODO Auto-generated constructor stub
		//super.setTeamCounter(super.getTeamCounter() + 1) ;
		loop =l ;
		color = super.getTeamCounter() ;
		mouse = Main.getState().getMouse() ;
		name = "AI" ;

	
	}
	
	public void placePiece(int x, int y){

		
		
		
		//if(loop.isHasClicked()){
	Kangaroo k = new Kangaroo(color) ;
	loop.getBoard().getBoardArray()[y][x].fill(k);
	super.getKangaroos().add(k) ;
	
	
			
		
	}
	public Square[][] nextMiniMaxMove() {
		MiniMax_AlphaBeta minMax = new MiniMax_AlphaBeta();
		Square[][] nextMove = minMax.getNextMove();
		
		return nextMove;
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
