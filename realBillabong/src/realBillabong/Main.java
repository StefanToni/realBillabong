package realBillabong;

import javax.swing.JFrame;

public class Main {
	
	private static GameState currentState ;
	public static JFrame frame ;
	private static int size = 40;
	
	public static int getSize() {
		return size;
	}

	public static void setSize(int s) {
		size = s;
	}

	//ideally make a statemachine that has different states, menu and play (potentially even split play into 2 phases, placement and moving)	
	public static void main(String[] args){
		
		
		
		currentState = new MainMenu() ;
		
		frame = new JFrame() ;
		frame.setSize(700, 700);
		frame.setVisible(true) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ;
		frame.add(((MainMenu) currentState).getPanel()) ;
		
		
		
		
		while(frame.getDefaultCloseOperation() != JFrame.EXIT_ON_CLOSE && currentState != null){
			currentState.update();
			currentState.render();
		}
		
				
	}
	
	public static void changeState(GameState newState){
		
		currentState = newState ;
	}
	
	public static GameState getState(){
		return currentState ;
	}

}
