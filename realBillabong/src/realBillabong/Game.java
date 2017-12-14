package realBillabong;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import AI.Diffuser;
import Game.Board;
import Game.DrawGraphics;
import Game.Gameloop;
import Game.MouseAdapter;
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
	private MouseListener mouse ;
	private Keyboard keyboard;
	private boolean AIWORK = false;

	
	public MouseAdapter getMouse() {
		return (MouseAdapter)mouse;
	}

	public void setMouse(MouseListener mouse) {
		this.mouse = mouse;
	}
	
	public void clearMouse()
	{
		pane.removeMouseListener(mouse);
	}

	

	public Game(int p, int a, boolean AI) {


		keyboard = new Keyboard();
		numberPlayers = p ;
		numberOfAI = a ;
		AIWORK = AI;
		loop = new Gameloop(p, a) ;
		start = new JButton() ;
		start.setSize(100, 100);
		start.setText("Start");
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//new Diffuser();
				//Main.getState().getLoop().getNextPlayer();
				if (Main.getState().getLoop().getCurrentPlayer().getAI()) {
					Main.getState().getLoop().aiMove();
				}
				
			}
		});
		
		//add actionlisteners
		loop.setAIWORK(AIWORK);
		
		end = new JButton() ;
		end.setSize(100, 100);
		end.setText("End") ;
		end.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
					}
				});
		
		
		component = new BoardComponent(loop.getBoard().getBoardArray()) ;
		//component.setSquareSize(Size);
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
		mouse = new MouseAdapter() ;
		
		
		pane.add(component) ;
		pane.add(start);
		pane.add(end ) ;
		pane.addMouseListener(mouse);
		Main.frame.dispose();
		Main.frame = new JFrame() ;
		Main.frame.setSize(1000, 1000);
		Main.frame.setVisible(true) ;
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ;
		Main.frame.add(pane);
		System.out.println("finished frame..");
		//loop.placementPhase() ;
		
	}

	public void deleteMouse()
	{
		pane.removeMouseListener(mouse);
	}
	public JPanel getPane() {
		return pane;
	}


	public void setPane(JPanel pane) {
		this.pane = pane;
	}


	public Gameloop getLoop() {
		return loop;
	}


	public void setLoop(Gameloop loop) {
		this.loop = loop;
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


	@Override
	public BoardComponent getComponent() {
		// TODO Auto-generated method stub
		return component;
	}

}
