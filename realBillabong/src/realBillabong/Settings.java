package realBillabong;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.DrawGraphics;
import Game.Gameloop;
import Game.MouseAdapter;
import Game.Square;
import Graphics.BoardComponent;

public class Settings implements GameState {
	
	private JButton start ;
	private JButton end ;
	private JPanel pane ;
	private JComponent board ;
	private DrawGraphics graphics ;
	private Square[][] boardArray ;
	private MouseAdapter mouse ;
	
	public Settings() {
		start = new JButton() ;
		start.setSize(100, 100);
		start.setText("Start");
		pane = new JPanel() ;
		pane.setSize(1000, 1000);
		
		pane.add(start);
		Main.frame.dispose();
		Main.frame = new JFrame() ;
		Main.frame.setSize(1000, 1000);
		Main.frame.setVisible(true) ;
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ;
		Main.frame.add(pane);
		System.out.println("finished Settings frame..");
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeState(GameState s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MouseAdapter getMouse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gameloop getLoop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardComponent getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
