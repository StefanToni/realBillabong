package realBillabong;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import Game.DrawGraphics;
import Game.Gameloop;
import Game.MouseAdapter;
import Game.Square;
import Graphics.BoardComponent;
import realBillabong.Main;

public class Settings implements GameState {
	
	private JButton play ;
	private JButton end ;
	private JPanel pane ;
	private DrawGraphics graphics ;
	private Square[][] boardArray ;
	private MouseAdapter mouse ;
	private JList list;
	private String[] size;
	private int Size;
	
	public Settings() {
		play = new JButton() ;
		play.setSize(100, 100);
		play.setText("Play");
		pane = new JPanel() ;
		pane.setSize(1000, 1000);
		size = new String[]{"small", "normal", "large"};
		list = new JList(size); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		pane.add(listScroller);
		
		
		
		play.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				  if (list.getSelectedIndex() == -1) {
				        //No selection, disable fire button.
					  		Main.setSize(40);
				        } 
				        else if (list.getSelectedIndex() == 0) {
				        //Selection, enable the fire button.
				        	Main.setSize(20);
				        }
				    	else if (list.getSelectedIndex() == 1) {
				        //Selection, enable the fire button.
				    		Main.setSize(40);
				        }
				    	else if (list.getSelectedIndex() == 2) {
					        //Selection, enable the fire button.
				    		Main.setSize(60);
				    	}
				changeState(new Game(2, 0)) ;
			}
			
		});
		pane.add(play);
		
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
		Main.changeState(s);
		
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
