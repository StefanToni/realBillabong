package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import realBillabong.Main;

public class MoveMouseAdapter implements MouseListener {
	
	private int x, y, actualX, actualY;
	private Gameloop loop;
	private Kangaroo currentKangaroo;
	private int counter = 0;
	private int squareSize = Main.getSize();

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		System.out.println(x +" , " + y);
		for(int i=0; i < 14; i++)
        {
            for(int j=0;j < 16 ;j++ )
            {
                if((79+(squareSize*j)<=x) && (77+squareSize+(squareSize*j)>=x) &&  (56+(squareSize*i)<=y) && (54+squareSize+(squareSize*i)>=y) )  
                {
               	 setActualY(i);
               	 setActualX(j);
            	 System.out.println(j+ " " + i ); // + Main.getState().getLoop().getCurrentPlayer().getName());
                }
               
            }
        }
//gets the square & kangaroo (Doesn't work yet, it says it wants to make a static reference but I don't see why)
		//this MoveMouseAdapter still has to be added after placement is done
		
		Square[][] boardCopy = Main.getState().getLoop().getBoard().getBoardArray() ;
		Square currentSquare = boardCopy[actualY][actualX];
		
		//gets Kangaroo of current square
		if(counter%2 == 0)//counter is for deciding whether it's the selection of the kangaroo or the square it wants to go to
		{
			currentKangaroo = currentSquare.getIsHere();
					
		}
		
		//moves to the new square
		else if(counter%2 != 0)
		{
			Main.getState().getLoop().getCurrentPlayer().performMove(currentKangaroo, currentSquare);
		}
		
		counter++;
		
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
