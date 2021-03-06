
package Game;

import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.MouseListener;

import realBillabong.Main;

public class MoveMouseAdapter implements MouseListener {
	
	private int x, y, actualX, actualY;
	private Gameloop loop;
	private Kangaroo currentKangaroo;
	private int counter = 1;
	private int squareSize = Main.getSize();
	private int rightclick = 1;
	private Square rightSquare;
	private boolean DEBUG = false;
//	private HumanPlayer referee;
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		System.out.println("mover " + x +" , " + y);
		
		for(int i=0; i < 14; i++)
        {
            for(int j=0;j < 16 ;j++ )
            {
                if((79+(squareSize*j)<=x) && (77+squareSize+(squareSize*j)>=x) &&  (56+(squareSize*i)<=y) && (54+squareSize+(squareSize*i)>=y) )  
                {
               	 	actualY = i;
               	 	actualX = j;
               	 	System.out.println("mover " + j+ " " + i ); // + Main.getState().getLoop().getCurrentPlayer().getName());
                }      
            }
        }
		
		Square[][] boardCopy = Main.getState().getLoop().getBoard().getBoardArray() ;
		Square currentSquare = boardCopy[actualY][actualX];
		Player currentPlayer = Main.getState().getLoop().getCurrentPlayer();
		
		
		// TODO Auto-generated method stub
		
		
//gets the square & kangaroo (Doesn't work yet, it says it wants to make a static reference but I don't see why)
		//this MoveMouseAdapter still has to be added after placement is done
		
		
		//gets Kangaroo of current square
		if(counter ==1)//counter is for deciding whether it's the selection of the kangaroo or the square it wants to go to
		{ System.out.println("Counter1 executed");
			if(currentSquare.isOccupied() && currentSquare.getIsHere().getTeam() == Main.getState().getLoop().getCurrentPlayer().getColor()){
				if(currentPlayer.firstmove == true)
				{
					currentSquare.setIsSelected(true);
					//if(currentSquare.getIsHere().getTeam() == Main.getState().getLoop().getCurrentPlayer().getColor())
					currentKangaroo = currentSquare.getIsHere();
					counter++ ; 
					if (DEBUG) System.out.println("CurrentKangarooSelected");
				}
				
				else if(currentSquare.getIsHere().moveable == true)
				{
					currentSquare.setIsSelected(true);
					currentKangaroo = currentSquare.getIsHere();
					counter++ ; 
					if (DEBUG) System.out.println("CurrentKangarooSelected");
				}
			}
			
					
		}
		
		//moves to the new square
		else if(counter == 2)
		{	
			if (DEBUG) System.out.println("Counter2 executed");
			if(!currentSquare.isOccupied()){
				
				if (DEBUG) System.out.println("Destination square is not occupied");
				counter = 1 ;
				/// constraints , x,y +-1 or jump
				Main.getState().getLoop().getCurrentPlayer().performMove(currentKangaroo, currentKangaroo.getPosition(), currentSquare);
				currentSquare = null ;
				currentKangaroo = null ; System.out.println("current deleted");
				//Main.getState().getLoop().getNextPlayer();
				
			}
			
		}
		if (DEBUG) System.out.println("clicker is " + counter);
		if(currentKangaroo == null){
			if (DEBUG) System.out.println(" roo is null");
		}
		if(currentSquare == null){
			if (DEBUG) System.out.println(" square is null");
		}
		
	}
		
		

	public int getActualX() {
		return actualX;
	}

	public void setActualX(int actualX) {
		this.actualX = actualX;
	}

	public int getActualY() {
		return actualY;
	}

	public void setActualY(int actualY) {
		this.actualY = actualY;
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
