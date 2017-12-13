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
	int [][] boardValues;
	private Kangaroo referee ;
	private Kangaroo[] kangaroos ;
	public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;
	int totalMoves;
	
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
	public Board(int boardSize) {
		boardValues = new int[14][16];
	}
	
public void move(Kangaroo k, Square origin, Square dest){
				
		
		if(k.checkLegal(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc(), dest) && (k.moveable || Main.getState().getLoop().getCurrentPlayer().firstmove)) 
		{
			
			k.checkLap(origin.getxLoc(), origin.getyLoc(), dest.getxLoc(), dest.getyLoc());
			origin.empty();
			if(Main.getState().getLoop().getCurrentPlayer().firstmove == true) 
				{
					origin.fill(new Kangaroo(10));
					k.or = origin;
					k.moveable = true;
				}
			
			Main.getState().getLoop().getCurrentPlayer().firstmove = false;
			if(k.lapCounter == 3) k.finishKangaroo();
			else dest.fill(k);
			
			System.out.println("MOVED FROM: " + origin.getyLoc()+ ", " + origin.getxLoc()+   " TO: " + dest.getyLoc()+ ", " + dest.getxLoc());
			
			dest.setIsSelected(true);
			if(Math.abs(origin.getxLoc()- dest.getxLoc()) == 1 || Math.abs(origin.getyLoc()- dest.getyLoc()) == 1){
			
				k.terminateTurn();
			}
		}
		
		else 
		{	System.out.println("MOVE NOT LEGAL");
			return;
		}
		for(int i = 0; i < 13; i++ ){
			for(int j = 0; j < 15; j++){
				if( board[i][j].isOccupied() ){
					System.out.print(" " + board[i][j].getIsHere().getTeam()+ " ");
				}
				else{
					System.out.print(" X ");
					
				}
			}
			System.out.println();
		}
		
		
	}	
	
	public Board(int[][] boardValues) {
		this.boardValues = boardValues;
		
	}
	public Board(int[][] boardValues, int totalMoves) {
        this.boardValues = boardValues;
        this.totalMoves = totalMoves;
    }
	

	//k.check1 method looks at how many kangaroos are on the board, if there are 5 of them
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

	/*public List<Square[][]> getEmptyPositions() {
        List<Square[][]> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (!board[i][j].isOccupied())
                    emptyPositions.add(new Square[i][j]);
            }
        }
        return emptyPositions;
    }*/
	
	public List<Square> getEmptyPositions() {
        List<Square> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if (!board[i][j].isOccupied())
                    emptyPositions.add(board[i][j]);
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

//	public void performMove(int playerNo, Square[][] p) {
//		// TODO Auto-generated method stub
//		this.totalMoves++;
//		boardValues[p.getX()][p.getY()];
//		
//	}
	

}
