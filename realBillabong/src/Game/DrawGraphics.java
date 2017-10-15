package Game;

import java.awt.Color;
import java.awt.Graphics;

public class DrawGraphics {
	
	Square[][] board ;
	
	public DrawGraphics(Square[][] b){
		board = b ;
	}
	
	public void paint(Graphics g) 
	{ 
		g.setColor(Color.yellow); 
		g.fill3DRect(40,40,340,300,true); 
		g.setColor(Color.BLUE);
		g.fill3DRect(170, 170, 80, 40, true);
		g.setColor(Color.blue);
		g.fill3DRect(210, 200, 5, 130, true);
		

		for (int i=0;i<15;i++) 
		{ 
			g.setColor(Color.black); 
			g.drawLine(50,50+20*i,370,50+20*i); 
		} 
		for (int j=0;j<17;j++) 
		{ 
			g.setColor(Color.black); 
			g.drawLine(50+20*j,50,50+20*j,330); 
		}  

		for(int m=0;m<14;m++) 
		{ 
			for(int n=0;n<16;n++) 
			{ 
				if(board[m][n].getIsHere().getTeam()==1) 
				{ 
					g.setColor(Color.black); 
					g.drawOval((50+(20*n)),(50+(20*m)),20,20); 
					g.setColor(Color.white); 
					g.fillOval((50+(20*n)),(50+(20*m)),20,20); 
				} 
				if(board[m][n].getIsHere().getTeam()==2)
				{ 
					g.setColor(Color.black); 
					g.fillOval((50+(20*n)),(50+(20*m)),20,20); 
				} 
			} 
		} 
	} 


}
