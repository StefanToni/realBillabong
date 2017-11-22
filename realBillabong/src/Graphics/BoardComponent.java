package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import Game.Player;
import Game.Square;
import realBillabong.Main;

public class BoardComponent extends JComponent {
	
	private Square[][] boardArray ;
	private int SquareSize = 20;
	private int current;
	
	public BoardComponent(Square[][] b){
		boardArray = b ;
		System.out.println("loaded boardarray in paint class..");
		SquareSize = Main.getSize();
		
		
	}
	
	@Override
	public Dimension getPreferredSize()
	{
	    return (new Dimension(800, 800));
	}
	
	@Override
	public void paintComponent(Graphics g){
		System.out.println("painting...");// to test, comment out later
		Graphics2D g2 = (Graphics2D) g ;
		
		g.setColor(Color.yellow); 
		g.fill3DRect(40,40,(SquareSize*16)+20,(SquareSize*14)+20,true); 
		g.setColor(Color.BLUE);
		g.fill3DRect(50+(SquareSize*6), 50+(SquareSize*6), SquareSize*4, SquareSize*2, true);
		g.setColor(Color.blue);
		g.fill3DRect(50+(SquareSize*8), 50+(SquareSize*8), SquareSize/4, SquareSize*6, true);
		

		for (int i=0;i<15;i++) 
		{ 
			g.setColor(Color.black); 
			g.drawLine(50,50+SquareSize*i,50+(SquareSize*16),50+(SquareSize*i)); 
		} 
		for (int j=0;j<17;j++) 
		{ 
			g.setColor(Color.black); 
			g.drawLine(50+(SquareSize*j),50,50+(SquareSize*j),50+(SquareSize*14)); 
		}  

		for(int m=0;m<14;m++) 
		{ 
			for(int n=0;n<16;n++) 
			{ 
				if(boardArray[m][n].getIsHere()== null){
					continue ;
				}
				if(boardArray[m][n].getIsHere().getTeam()==1) 
				{ 
					g.setColor(Color.black); 
					g.drawOval((50+(SquareSize*n)),(50+(SquareSize*m)),SquareSize,SquareSize); 
					g.setColor(Color.white); 
					g.fillOval((50+(SquareSize*n)),(50+(SquareSize*m)),SquareSize,SquareSize); 
				} 
				if(boardArray[m][n].getIsHere().getTeam()==2)
				{ 
					g.setColor(Color.black); 
					g.fillOval((50+(SquareSize*n)),(50+(SquareSize*m)),SquareSize,SquareSize); 
				} 
				if(boardArray[m][n].getIsHere().getTeam()==10)
				{ 
					g.setColor(Color.black); 
					g.drawOval((50+(SquareSize*n)),(50+(SquareSize*m)),SquareSize,SquareSize) ;
					g.setColor(Color.red); 
					g.fillOval((50+(SquareSize*n)),(50+(SquareSize*m)),SquareSize,SquareSize); 
				}
			
			} 
		}
		
		
		for(int m=0;m<14;m++) 
		{ 
			for(int n=0;n<16;n++) 
			{ 
				if(boardArray[m][n].getIsSelected())
				{
				g2.setStroke(new BasicStroke(2));
				if (Main.getState().getLoop().getCurrentPlayer().getTeamCounter()==0) {
					g2.setColor(Color.BLUE);
					System.out.println("Player 0");
				}
				if (Main.getState().getLoop().getCurrentPlayer().getTeamCounter()==1) {
					g2.setColor(Color.RED);
					System.out.println("Player 1");
				}
				System.out.println(m + " " + n + " is selected"); 
				g2.drawRect(50+(SquareSize*n),50+(SquareSize*m),SquareSize,SquareSize); 
				}
				
			}
		}
		for(int i = 0 ; i < 14 ; i++){
			for(int j = 0 ; j < 16 ; j++){
				boardArray[i][j].setIsSelected(false);
			}
		}
				
	}

	public int getSquareSize() {
		return SquareSize;
	}

	public void setSquareSize(int squareSize) {
		SquareSize = squareSize;
	}

}
