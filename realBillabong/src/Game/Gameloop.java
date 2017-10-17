package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ListIterator;

import realBillabong.Main;

public class Gameloop implements MouseListener{
	
	private int x, y;
	private ArrayList<Player> players ;
	private Board board ; //what shall be passed to the graphics, no more
	private Player currentPlayer ;
	private MouseAdapter mouse;
	private boolean hasClicked = false ;
	private int placeNumber;
	
	public int getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}

	public boolean isHasClicked() {
		return hasClicked;
	}

	public void setHasClicked(boolean hasClicked) {
		this.hasClicked = hasClicked;
	}

	public MouseAdapter getMouse() {
		return mouse;
	}

	public void setMouse(MouseAdapter mouse) {
		this.mouse = mouse;
	}

	public Gameloop(int p, int a){
		mouse = Main.getState().getMouse() ;
		board = new  Board() ;
		players = new ArrayList<Player>() ;
		for(int i = 0; i < p ; i++){
			HumanPlayer hPlayer = new HumanPlayer(this) ;
			players.add(hPlayer) ;
		}
		for(int i = 0; i < a ; i++){
			AIPlayer aiPlayer = new AIPlayer() ;
			players.add(aiPlayer) ;
		}
		placeNumber = players.size()*5;
		currentPlayer = players.get(0) ;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void getNextPlayer() {
		boolean done = false;
		int i = 0;
		while(i < players.size() && done != true){
            if (players.get(i) == currentPlayer) {
            	if (i == players.size()-1){
            		setCurrentPlayer(players.get(0));
            		done = true;
            	}
            	else {
            		setCurrentPlayer(players.get(i+1));
            		done = true;
            	}
            }
            i++;
        }
	}
	
	public void placementPhase(){
		setCurrentPlayer(players.get(0));
		int placeNumber = players.size()*5;
		
		System.out.println("start placing");
		x = mouse.getActualX();
		y = mouse.getActualY();
		if (x >= 0 && x <= 16 ) {
			currentPlayer.placePiece(x, y);
		}
		/*for(int i = 0 ; i < 5 ; i++){
			for(int p = 0 ; p < players.size() ; p++){
				
				currentPlayer  = players.get(p) ;
				currentPlayer.placePiece() ;
				
			}
		}*/
		//gamePhase() ;
	}
	
	
	public void gamePhase(){
		System.out.println("start playing");
	}
	
	public void endGame(){
		 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
