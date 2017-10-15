package realBillabong;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.Board;
import Game.DrawGraphics;
import Game.Gameloop;
import Game.Square;
import Graphics.BoardComponent;

public class Game implements GameState {
	
	private Gameloop loop ;
	private int numberPlayers ;
	private int numberOfAI ;
	private JButton start ;
	private JButton end ;
	private JPanel pane ;
	private JComponent board ;
	private DrawGraphics graphics ;
	private Square[][] boardArray ;
	private BoardComponent component ;
	
	public Game(int p, int a){
		loop = new Gameloop(p, a) ;
		numberPlayers = p ;
		numberOfAI = a ;
		start = new JButton() ;
		start.setSize(100, 100);
		start.setText("Start");
		//add actionlisteners
		end = new JButton() ;
		end.setSize(100, 100);
		end.setText("End") ;
		component = new BoardComponent(loop.getBoard().getBoardArray()) ;
		System.out.println("created component..");
		//add actionlistener
		pane = new JPanel() ;
		pane.setSize(1000, 1000);
		//pane.setLayout(new BorderLayout()) ;
		/*for(int i = 0 ;i < 14 ; i++){
			for(int j = 0 ; j < 16; j++){
				System.out.print(boardArray[i][j].isOccupied());
			}
			System.out.println();
		}*/
		//System.out.println(component.toString());
		pane.add(component) ;
		pane.add(start);
		pane.add(end ) ;
		Main.frame.dispose();
		Main.frame = new JFrame() ;
		Main.frame.setSize(1000, 1000);
		Main.frame.setVisible(true) ;
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ;
		Main.frame.add(pane);
		System.out.println("finished frame..");
		
		
		
	}
	

	public JComponent getBoardComponent() {
		return board;
	}



	public void setBoard(JComponent board) {
		this.board = board;
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changeState(GameState s) {
		// TODO Auto-generated method stub
		
	}

}
