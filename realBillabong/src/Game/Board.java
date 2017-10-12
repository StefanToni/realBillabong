package Game;

import java.applet.Applet;
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
	int i, j, x, y, chk=0; 
	int [][] a = new int[14][16]; 

	Frame f1; 
	Button b1 = new Button("Restart"); 
	Button b2 = new Button("End"); 

	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource()==b1) 
		{ 

			for (int i=0;i<14;i++ ) 
			{ 
				for (int j=0;j<16;j++) 
				{ 
					a[i][j]=0; 
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
							if(a[i][j]==0) 
							{ 
								if(chk==0 && check1()) 
								{ 
									a[i][j]=1; 
									chk=1;
								} 
								else if(chk==1 && check1()) 
								{ 
									a[i][j]=2; 
									chk=0; 

									
								} 
							} 
						} 
					} 
				} 
				repaint(); 
			} 
		}); 
	} 
	
	public boolean check1() {
		
		int white = 0;
		int black = 0;
		
		for(int m=0;m<14;m++) 
		{ 
			for(int n=0;n<16;n++) 
			{ 
				if (a[m][n] == 1) {
					white++;
				}
				if (a[m][n] == 2) {
					black++;
				}
			}
		}
		if (black > 4 && white > 4) {
			return false;
		}
		return true;
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


	
	private Square[][] boardArray ;
	private Kangaroo referee ;
	private Kangaroo[] kangaroos ;

	public Square[][] getBoardArray() {
		return boardArray ;
	}

	public void setBoardArray(Square[][] newBoard) {
		boardArray = newBoard ;
	}

	public Kangaroo getReferee() {
		return referee;
	}

	public void setReferee(Kangaroo referee) {
		this.referee = referee;
	}

	public Kangaroo[] getKangaroos() {
		return kangaroos;
	}

	public void setKangaroos(Kangaroo[] kangaroos) {
		this.kangaroos = kangaroos;
	}
	

}
