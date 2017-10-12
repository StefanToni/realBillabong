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

public class Board extends Applet implements ActionListener 
{ 
	Graphics g;
	int i, j, x, y, player = 0;
	DrawGraphics painter = new DrawGraphics(g);
	private int[][] boardArray ;
	private Kangaroo referee ;
	private Kangaroo[] kangaroos ;
	public Board() {
		boardArray = new int[14][16];
	}
	
	Frame f1; 
	Button b1 = new Button("Restart"); 
	Button b2 = new Button("End"); 
	
	//This one either restarts the game or ends it, depends on button user clicked 
	//for now it is not used since the game cannot be finished yet
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource()==b1) 
		{ 

			for (int i=0;i<14;i++ ) 
			{ 
				for (int j=0;j<16;j++) 
				{ 
					boardArray[i][j]=0; 
					repaint(); 
					f1.setVisible(false); 
				} 
			} 
		} 
		else if (e.getSource()==b2) 
		{ 
			System.exit(0);
		} 
	} 
	
	//basically creating windows in applet
	public void init() 
	{ 

		f1=new Frame (); 
		f1.setLayout(new BorderLayout()); 
		Panel p=new Panel(); 
		p.add(b1); 
		p.add(b2); 
		f1.add("South",p); 
		f1.setSize(250,150); 

		b1.addActionListener(this); 
		b2.addActionListener(this); 

		f1.addWindowListener(new WindowAdapter() 
		{ 
			public void windowClosing(WindowEvent e) 
			{ 
				System.exit(0); 
			} 
		}); 
		//Mouse Listener gets the pressed position and draws a circle depending on player doing it
		addMouseListener(new MouseAdapter() 
		{ 
			public void mousePressed(MouseEvent e) 
			{ 
				int x = e.getX(); 
				int y = e.getY(); 

				for(int i=0; i < 14; i++) 
				{ 
					for(int j=0;j < 16 ;j++ ) 
					{ 

						if((51+(20*j)<=x) && (69+(20*j)>=x) && 	(51+(20*i)<=y) && (69+(20*i)>=y) )  
						{ 
							if(boardArray[i][j]==0) 
							{ 
								if(player==0 && check1()) 
								{ 
									boardArray[i][j]=1; 
									player=1;
								} 
								else if(player==1 && check1()) 
								{ 
									boardArray[i][j]=2; 
									player=0; 

									
								} 
							} 
						} 
					} 
				} 
				repaint(); 
			} 
		}); 
	} 
	
	//check1 looks at how many kangaroos we have on a board
		//stops creating new ones when there are 5 of them on a board
		public boolean check1() {
			
			int white = 0;
			int black = 0;
			
			for(int m=0;m<14;m++) 
			{ 
				for(int n=0;n<16;n++) 
				{ 
					if (boardArray[m][n] == 1) {
						white++;
					}
					if (boardArray[m][n] == 2) {
						black++;
					}
				}
			}
			if (black > 4 && white > 4) {
				return false;
			}
			return true;
		}
	
	
	public int[][] getBoardArray() {
		return boardArray ;
	}

	public void setBoardArray(int[][] newBoard) {
		boardArray = newBoard ;
	}

	public Kangaroo getReferee() {
		return referee;
	}

	public void setReferee(Kangaroo referee) {
		this.referee = referee;
	}


	

}
