package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import realBillabong.Main;

public class MouseAdapter implements MouseListener {

	private int x, y, actualX, actualY;
	private Gameloop loop;
	private int squareSize = Main.getSize();

	
	@Override
	public void mouseClicked(MouseEvent e) {
		int k = Main.getState().getLoop().getPlaceNumber();
		if (k>0)
		{
			
			
			if(Main.getState().getLoop().getCurrentPlayer().getAI())
			{ 	System.out.println("AI detected");
				Main.getState().getLoop().getCurrentPlayer().placeRoo();
				Main.getState().getComponent().repaint();
				Main.getState().getLoop().getNextPlayer();
		        Main.getState().getLoop().setPlaceNumber(k-1);
			}	
			
			else{
		
				
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
							System.out.println(j+ " " + i );
						}
                
               
					}
				}
				if (!Main.getState().getLoop().getBoard().getBoardArray()[actualY][actualX].isOccupied() ) {
				
			
					//Main.getState().getLoop().setPlaceNumber(k-1);
					if (k > 0) {
						System.out.println(Main.getState().getLoop().getCurrentPlayer().getColor());
				
				
						Main.getState().getLoop().getCurrentPlayer().placePiece(actualY, actualX);
				
				
						//Main.getState().getLoop().setCurrentPlayer(Main.getState().getLoop().getPlayers().get(Main.getState().getLoop().getCurrentPlayer().getColor()+1));
						//loop.setHasClicked(true);
						Main.getState().getComponent().repaint();
						Main.getState().getLoop().getNextPlayer();
						Main.getState().getLoop().setPlaceNumber(k-1);
				
								}
			
			
					}
				}
		}
		
		else{
			Main.getState().getLoop().gamePhase() ;
			System.out.println("start boyyyyyy");
			
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