package Game;

import java.applet.Applet;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawGraphics
{ 
	Board board = new Board(); 
	int [][] a = board.getBoardArray(); 
	public Graphics g;
	public DrawGraphics(Graphics g) {
		this.g = g;
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
				if(a[m][n]==1) 
				{ 
					g.setColor(Color.black); 
					g.drawOval((50+(20*n)),(50+(20*m)),20,20); 
					g.setColor(Color.white); 
					g.fillOval((50+(20*n)),(50+(20*m)),20,20); 
				} 
				if(a[m][n]==2)
				{ 
					g.setColor(Color.black); 
					g.fillOval((50+(20*n)),(50+(20*m)),20,20); 
				} 
			} 
		} 
	} 


	
	

}
