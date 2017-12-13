
package realBillabong;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Board;
import Game.Gameloop;
import Game.MouseAdapter;
import Graphics.BoardComponent;

public class MainMenu implements GameState {
	
	private JPanel panel ;
	private JButton settings ;
	private JButton play ;
	private static int playerNm = 0;
	private static int AINm = 2;
	
	public MainMenu(){
		panel = new JPanel() ;
		panel.setSize(200, 200) ;
		panel.setLayout(new FlowLayout());
		play = new JButton() ;
		play.setText("Play!!");
		play.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				changeState(new Game(playerNm, AINm, false)) ;
			}
			
		});
		
		panel.add(play) ;
		
		settings = new JButton() ;
		settings.setText("Settings..");
		settings.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				changeState(new Settings());
			}
		});
		panel.add(settings) ;
		
	}
	
	
	
	public JPanel getPanel() {
		return panel;
	}



	public void setPanel(JPanel panel) {
		this.panel = panel;
	}



	public JButton getSettings() {
		return settings;
	}



	public void setSettings(JButton settings) {
		this.settings = settings;
	}



	public JButton getPlay() {
		return play;
	}



	public void setPlay(JButton play) {
		this.play = play;
	}



	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeState(GameState s) {
		// TODO Auto-generated method stub
		Main.changeState(s) ;
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

