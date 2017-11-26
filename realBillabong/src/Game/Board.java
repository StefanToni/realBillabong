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
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import realBillabong.Main;

public class Board 
{ 
	int x, y, chk=0; 
	private Square[][] board ; 
	private Kangaroo referee ;
	private Kangaroo[] kangaroos ;
	public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;
	
	public Board(){
		board = new Square[14][16] ;
		for(int i = 0 ; i < 14 ; i++){
			for(int j = 0 ; j < 16 ; j++){
				board[i][j] = new Square(i, j) ;
			}
		}
		for (int m = 6 ; m < 8 ; m++) {
			for (int n = 6 ; n < 10 ; n++) {
				board[m][n].setWater(true);
			}
		}
		
		System.out.println("created boardarray in board class..");
	}
	
	/*init is like a main method for applets, initializes the board in a frame and adds the listeners
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
		//mouse listeners gets x and y and creates kangaroos depending on a player
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
	} */
	
	//check1 method looks at how many kangaroos are on the board, if there are 5 of them
	//for each player then, then he cannot add more
	
	
	public void clearSelected() {
		for(int i = 0 ; i < 14 ; i++){
			for(int j = 0 ; j < 16 ; j++){
				board[i][j].setIsSelected(false);
			}
		}
		System.out.println("clearing working");
	}

	public Square[][] getBoardArray() {

		return board ;
	}

	public void setBoardArray(Square[][] newBoard) {
		board = newBoard ;
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

	public List<Square[][]> getEmptyPositions() {
        List<Square[][]> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (!board[i][j].isOccupied())
                    emptyPositions.add(new Square[i][j]);
            }
        }
        return emptyPositions;
    }

	public int checkStatus() {
		if (Main.getState().getLoop().getCurrentPlayer().haveIWon()) {
			return Main.getState().getLoop().getCurrentPlayer().getColor();
		}
		else return -1;
	}
	

}
