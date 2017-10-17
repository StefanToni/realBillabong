package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import realBillabong.Main;

public class MouseAdapter implements MouseListener {

	private int x, y, actualX, actualY;
	private Gameloop loop;

	
	@Override
	public void mouseClicked(MouseEvent e) {

		x = e.getX();
		y = e.getY();
		System.out.println(x +" , " + y);
		for(int i=0; i < 14; i++)
        {
            for(int j=0;j < 16 ;j++ )
            {
                if((79+(20*j)<=x) && (97+(20*j)>=x) &&  (56+(20*i)<=y) && (74+(20*i)>=y) )  
                {
               	 setActualY(i);
               	 setActualX(j);
            	 System.out.println(j+ " " + i + Main.getState().getLoop().getCurrentPlayer().getName());
                }
               
            }
        }
		int k = Main.getState().getLoop().getPlaceNumber();
		Main.getState().getLoop().setPlaceNumber(k-1);
		if (k > 0) {
			System.out.println(Main.getState().getLoop().getCurrentPlayer().getColor());
			Main.getState().getLoop().getCurrentPlayer().placePiece(actualY, actualX);
			//Main.getState().getLoop().setCurrentPlayer(Main.getState().getLoop().getPlayers().get(Main.getState().getLoop().getCurrentPlayer().getColor()+1));
			//loop.setHasClicked(true);
			Main.getState().getComponent().repaint();
			Main.getState().getLoop().getNextPlayer();
			
		}
		
	}
	
	public void addLoop(Gameloop l){
		loop = l;
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
	
	public void setActualX(int x) {
		this.actualX = x;
	}
	
	public void setActualY(int y) {
		this.actualY = y;
	}
	
	public int getActualX() {
		return actualX;
	}
	
	public int getActualY() {
		return actualY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
