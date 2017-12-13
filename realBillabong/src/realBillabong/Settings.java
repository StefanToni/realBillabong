package realBillabong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
	private int playerNumber=1;
	private int AINumber=1;
	private JCheckBox AIButton;
	private boolean AIButtonSelected;
	
	
	public Settings() {
		//PLAY BUTTON////////
		play = new JButton() ;
		play.setSize(100, 100);
		play.setText("Play");
		/////////////////////
		
		//PANEL SIZE AND GRIDLAYOUT//
		pane = new JPanel(new GridLayout(4,2)) ;
		pane.setSize(700, 800);
		/////////////////////////////
		
		//LIST WITH SIZE/////////////
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
		//////////////////////////////
		
		//PLAYER NUMBER///////////////
		String[] playerStrings = {"0", "1", "2", "3", "4" };
		JComboBox<String> playerList = new JComboBox<>(playerStrings);
		playerList.setSelectedIndex(1);
		JLabel strin1 = new JLabel("Choose number of players");
		strin1.setHorizontalAlignment(JLabel.CENTER);
		pane.add(strin1);
		pane.add(playerList);
		strin1.setSize(new Dimension(1, 1));
		//////////////////////////////
		
		//AI NUMBER///////////////////
		JComboBox<String> aiList = new JComboBox<>(playerStrings);
		aiList.setSelectedIndex(1);
		JLabel strin2 = new JLabel("Choose number of AI players");
		strin2.setHorizontalAlignment(JLabel.CENTER);
		strin2.setSize(new Dimension(1, 1));
		pane.add(strin2);
		pane.add(aiList);
		//////////////////////////////
		
		//AIBUTTON////////////////////
		AIButton = new JCheckBox("press space to move AI");
		AIButton.setSelected(true);
		AIButton.setHorizontalAlignment(JLabel.CENTER);
		
		pane.add(AIButton);
		AIButtonSelected = true;
		//////////////////////////////
		
		//AIBUTTON LISTENER///////////
		AIButton.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) { 
            	if (AIButton.isSelected()) {
            		AIButtonSelected = true;
            	}
            	else {
            		AIButtonSelected = false;
            	}
                
            }    
         });    
		//////////////////////////////
		
		
		//ACTION LISTENER FOR AI//////
		playerList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				aiList.removeAllItems();
				String p = (String) playerList.getSelectedItem();
				playerNumber = Integer.parseInt(p);
				
				aiList.addItem("0");
				for(int i = 1; i<5-playerNumber; i++)
				{
					String t = "" + i;
					aiList.addItem(t);
				}
				
				String aiN = (String) aiList.getSelectedItem();
				AINumber = Integer.parseInt(aiN);
				
			}
		});
		//////////////////////////////
		
		//AINumber = /*4 - playerNumber;*/ 0;
		
		//AIButton = new JCheckBox("Enable AI");
		//AIButton.setSelected(false);
		//pane.add(AIButton);
		
		
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
				  
				  String p = (String) playerList.getSelectedItem();
					playerNumber = Integer.parseInt(p);
				  String aiN = (String) aiList.getSelectedItem();
					AINumber = Integer.parseInt(aiN);
				changeState(new Game(playerNumber, AINumber, AIButtonSelected)) ;
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
