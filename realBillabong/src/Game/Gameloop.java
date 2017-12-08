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
	private int placeNumber, currentAI;
	private RooSelectah rooSelector ;
	private SquareSelectah squareSelector;
	private MoveMouseAdapter mover ;
	private Kangaroo k ;
	private Square s ;
	private int piececounter ;
	
	private boolean placementPhase = true;
	private boolean gamePhase = false;
	private ArrayList<AIPlayer> aiPlayers;

	public int getPiececounter() {
		return piececounter;
	}

	public void setPiececounter(int piececounter) {
		this.piececounter = piececounter;
	}

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
		aiPlayers = new ArrayList<AIPlayer>();
		for(int i = 0; i < p ; i++){
			Player hPlayer = new HumanPlayer(this) ;
			hPlayer.setAI(false);
			players.add(hPlayer) ;
		}
		for(int i = 0; i < a ; i++){
			Player aiPlayer = new AIPlayer(this) ;
			aiPlayer.setAI(true);
			players.add(aiPlayer);
			aiPlayers.add((AIPlayer) aiPlayer) ;
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
	
	public ArrayList<AIPlayer> getAIPlayers(){
		return aiPlayers;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	
	/*public void setPlayersColors(){
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setColor(i);
		}
	}*/
	
	public void getNextPlayer() {
		boolean done = false;
		int i = 0;
		while(i < players.size() && done != true){
            if (players.get(i) == currentPlayer) {
            	if (i == players.size()-1){
            		System.out.println(i + " i goes to 0");
            		setCurrentPlayer(players.get(0));
            		if(gamePhase && currentPlayer.ai) {
            			System.out.println("starting AI turn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            			if(currentAI == aiPlayers.size()) currentAI = 0;
            			Square[][] newBoard = aiPlayers.get(currentAI).nextMiniMaxMove();
            			//Main.getState().getLoop().getBoard().setBoardArray(newBoard);
            			board.setBoardArray(newBoard);
            			System.out.println("ai move performed !!!!!!!!!!!!!!!!!!!111!!!!!!!");
            			getNextPlayer();
            		}
            		System.out.println("team number " + currentPlayer.getColor());
            		done = true;
            	}
            	else {
            		System.out.println(" i goes up to " + (i+1));
            		setCurrentPlayer(players.get(i+1));
            		if(gamePhase && currentPlayer.ai) {
            			System.out.println("starting AI turn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            			if(currentAI == aiPlayers.size()) currentAI = 0;
            			Square[][] newBoard = aiPlayers.get(currentAI).nextMiniMaxMove();
            			//Main.getState().getLoop().getBoard().setBoardArray(newBoard);
            			board.setBoardArray(newBoard);
            			
            			System.out.println("ai move performed !!!!!!!!!!!!!!!!!!!111!!!!!!!");
            			getNextPlayer();
            		}
            		
            		
            		System.out.println("team number " + currentPlayer.getColor());
            		done = true;
            	}
            }
            i++;
        }
	}
	
	public void placementPhase(){
		
		setCurrentPlayer(players.get(0));
		placeNumber = players.size()*5;
		
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
		placementPhase = false;
		gamePhase() ;
	}
	
	
	public void gamePhase(){
		gamePhase = true;
		Main.getState().deleteMouse();
		mouse = null;
		System.out.println("start playing");
		mover = new MoveMouseAdapter() ;
		Main.getState().getPane().addMouseListener(mover);
		currentPlayer = players.get(0) ;
		
		if(currentPlayer.haveIWon()){
			//display winnner thing
			System.out.println("Team number " + currentPlayer.getColor() + " wins !!!") ;
		}
		/*else{
			//check constraints
			//System.out.println(currentPlayer.getColor());
			//currentPlayer.setInput(false);
			//repaint
			Main.getState().getComponent().repaint();
			System.out.println("repaint loop");
			getNextPlayer() ;
			System.out.println("next player selected");
		}*/
		
	}
	
	public void checkInput(){
		if(rooSelector.getSelected() != null){
			k = rooSelector.getSelected() ;
		}
		if(squareSelector.getSelected() != null){
			s = squareSelector.getSelected() ;
		}
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
