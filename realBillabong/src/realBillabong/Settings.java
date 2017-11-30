package realBillabong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private int playerNumber;
	private int AINumber;
	private JCheckBox AIButton;
	
	public Settings() {
		play = new JButton() ;
		play.setSize(100, 100);
		play.setText("Play");
		pane = new JPanel(new GridLayout(0,2)) ;
		pane.setSize(700, 700);
		size = new String[]{"small", "normal", "large"};
		list = new JList(size); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		JLabel strin = new JLabel("Choose size");
		strin.setHorizontalAlignment(JLabel.CENTER);
		strin.setSize(new Dimension(1, 1));
		pane.add(strin);
		pane.add(listScroller);
		
		
		
		String[] playerStrings = { "1", "2", "3" };
		JComboBox<String> playerList = new JComboBox<>(playerStrings);
		playerList.setSelectedIndex(0);
		JLabel strin1 = new JLabel("Choose number of players");
		strin1.setHorizontalAlignment(JLabel.CENTER);
		strin1.setSize(new Dimension(1, 1));
		pane.add(strin1);
		pane.add(playerList);
		
		
		String p = (String) playerList.getSelectedItem();
		playerNumber = Integer.parseInt(p);
		AINumber = /*4 - playerNumber;*/ 1;
		
		AIButton = new JCheckBox("Enable AI");
		AIButton.setSelected(false);
		pane.add(AIButton);
		
		
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
				  if (!AIButton.isSelected()) {
					  AINumber = 0;
				  }
				changeState(new Game(playerNumber, AINumber)) ;
			}
			
			
			
		});
		
		
		pane.add(play);
		
		Main.frame.dispose();
		Main.frame = new JFrame() ;
		Main.frame.setSize(700, 700);
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



	@Override
	public JPanel getPane() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteMouse() {
		// TODO Auto-generated method stub
		
	}

}
