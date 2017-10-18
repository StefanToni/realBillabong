package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import realBillabong.Main;

public class SquareSelectah implements MouseListener {
	
	private boolean done = false ;
	private int x, y, actualX, actualY ;
	private Square selected ;
	private int squareSize = Main.getSize();

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

	public Square getSelected() {
		return selected;
	}

	public void setSelected(Square selected) {
		this.selected = selected;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

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
            	 System.out.println(j+ " " + i + Main.getState().getLoop().getCurrentPlayer().getName());
                }
               
            }
        }
		if (!Main.getState().getLoop().getBoard().getBoardArray()[actualY][actualX].isOccupied() ){
			selected = Main.getState().getLoop().getBoard().getBoardArray()[actualY][actualX] ;
			done = true ;
		}

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
