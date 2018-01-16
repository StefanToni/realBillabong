package AI;

import java.util.ArrayList;

import java.util.List;

import Game.Board;
import Game.Kangaroo;
import Game.Player;
import Game.Position;
import Game.Square;
import realBillabong.Main;

	public class MState {
	    private Board board;
	    private int playerNo;
	    private int visitCount;
	    private double winScore;
	    private ArrayList<Kangaroo> kangaroos;
	    private int[] middleCoords = new int[2];
	    private Square b[][];
	    private ArrayList<Move> possibleMoves;
	    private int playerNumber;
	    

	   
	    public MState(MState state) {
	        this.board = new Board(state.getBoard());
	        this.playerNo = state.getPlayerNo();
	        this.visitCount = state.getVisitCount();
	        this.winScore = state.getWinScore();
	        this.setKangaroos(Main.getState().getLoop().getPlayers().get(2).getKangaroos());
	        possibleMoves = new ArrayList<Move>();	        
	    }
	    
	    public MState(int playerNum) {
	    	this.playerNumber = playerNum;
	    	board = new Board();
	    }
	    
	    public MState() {
	        board = new Board();
	    }

	    public MState(Board board) {
	        this.board = Main.getState().getLoop().getBoard(); // dasibogi
	    }

	    Board getBoard() {
	        return board;
	    }

	    void setBoard(Board board) {
	        this.board = board;
	    }

	    int getPlayerNo() {
	        return playerNo;
	    }

	    void setPlayerNo(int playerNo) {
	        this.playerNo = playerNo;
	    }

	    int getOpponent() {
	        return 3 - playerNo;
	    }

	    public int getVisitCount() {
	        return visitCount;
	    }

	    public void setVisitCount(int visitCount) {
	        this.visitCount = visitCount;
	    }

	    double getWinScore() {
	        return winScore;
	    }

	    void setWinScore(double winScore) {
	        this.winScore = winScore;
	    }

	    public List<MState> getAllPossibleStates() {
	        List<MState> possibleStates = new ArrayList<>();
	        Kangaroo k = Main.getState().getLoop().getCurrentPlayer().getKangaroos().get(1);
	        //Getting just one kangaroo and all its possible moves
	        List<Move> availablePositions = checkForMoves(k);
	        getNextPlayer();
	        availablePositions.forEach(p -> {
	            MState newState = new MState(this.board);
	            //Main.getState().getLoop().getCurrentPlayer().performMove(currentKangaroo, currentKangaroo.getPosition(), currentSquare);
	            //newState.getBoard().performMove(newState.getPlayerNo(), p);
	            Main.getState().getLoop().getPlayers().get(2).performMove(k, k.getPosition(), availablePositions.get(p));
	            possibleStates.add(newState);
	        });
	        return possibleStates;
	    }

	    void incrementVisit() {
	        this.visitCount++;
	    }

	    void addScore(double score) {
	        if (this.winScore != Integer.MIN_VALUE)
	            this.winScore += score;
	    }

	    void randomPlay() {
	    	Kangaroo ranKan = Main.getState().getLoop().getCurrentPlayer().getKangaroos().get(((int) (1+ (Math.random() * 5))));
	        List<Move> availablePositions = checkForMoves(ranKan);
	        int totalPossibilities = availablePositions.size();
	        int selectRandom = (int) (Math.random() * ((totalPossibilities - 1) + 1));
	        
	        //this.board.performMove(this.playerNo, availablePositions.get(selectRandom));
	        Main.getState().getLoop().getCurrentPlayer().performMove(ranKan, ranKan.getPosition(), availablePositions.get(selectRandom).getDest());
	    }

	    void togglePlayer() {
	        this.playerNo = 3 - this.playerNo;
	    }

		public ArrayList<Kangaroo> getKangaroos() {
			return kangaroos;
		}

		public void setKangaroos(ArrayList<Kangaroo> kangaroos) {
			this.kangaroos = kangaroos;
		}
		
		public ArrayList<Move> checkForMoves(Kangaroo k){
			//System.out.println("checking moves");
			 
			
			for(int i = 0; i < 14; i++){
				for(int j = 0 ;  j < 16; j++){
						for(int y = 0; y < 14; y++){
							for(int x = 0; x < 16; x++){
								if(k.checkLegal(j, i, x, y, b[y][x])){
									Move m = new Move(k, b[i][j], b[y][x]) ;
									possibleMoves.add(m) ;
									//System.out.println("move " + y + " " + x + " added to list");
								}
							}
						}
					}
					
				}
			return possibleMoves;
			
		}
		
		
	public boolean checkLegal(int ox, int oy, int dx, int dy, Square dest)
	{
		//if(dx>13 || dy > 15) return false;
		int deltaX = dx-ox;
		int deltaY = dy-oy;
		
		if((Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0 || deltaY == 0) && !(dest.isOccupied() || dest.isWater()))
		{
			
			//System.out.println("delta = diagonal or 0");
			
			if(Math.abs(deltaX) <=1 && Math.abs(deltaY) <=1)
			{
				return true;
			}
		
			else  
			{   
				Square[][] boardCopy = board.getBoardArray();
				
				int midSquare_x = ox + ((dx-ox)/2);
				int midSquare_y = oy + ((dy-oy)/2);
			
				Square midSquare = boardCopy[midSquare_y][midSquare_x];
				//System.out.println("Midsquare found");
				if(midSquare.isOccupied() && onlyOne(ox, oy, dx, dy))
				{
					return true;
				}
				
			}
			
			return false;
		}
		
		else return false;
		
		
	}
	
	
	public boolean onlyOne(int ox, int oy, int dx, int dy)
	{	//System.out.println("Only One Tried");
		//current x&y
		
		int deltaX = dx - ox;
		int deltaY = dy - oy;
		
		Square[][] boardCopy = board.getBoardArray();
		
		int cx = ox;
		int cy = oy;
		int middleCounter = 0;
		
		
		//if diagonal
		if(Math.abs(deltaX) == Math.abs(deltaY))
		{
			
			
			if(deltaX<0 && deltaY>0)
			{
				while(cx!=dx && cy != dy )
				{
					cx--;
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied() && !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX>0 && deltaY<0)
			{
				while(cx!=dx && cy != dy )
				{
					cx++;
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX>0 && deltaY>0)
			{
				while(cx!=dx && cy != dy )
				{
					cx++;
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(deltaX<0 && deltaY<0)
			{
				while(cx!=dx && cy != dy )
				{
					cx--;
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			
			if(middleCounter == 1 && middleCoords[0] == (dy+oy)/2 && middleCoords[1] == (dx+ox)/2){
				//System.out.println("only one tried successful");
				return true;
				
			}
			
			else {
				//System.out.println("only one tried fail" + middleCounter);
				return false;
			}
			
		}
		
		//if not diagonal
		else
		{
			if(deltaX == 0 && deltaY > 0)
			{
				while(cy!=dy)
				{
					cy++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX == 0 && deltaY < 0)
			{
				while(cy!=dy)
				{
					cy--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX > 0 && deltaY == 0)
			{
				while(cx!=dx)
				{
					cx++;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
		
			if(deltaX < 0 && deltaY == 0)
			{
				while(cx!=dx)
				{
					cx--;
					if(boardCopy[cy][cx].isWater()) middleCounter++;
					if(boardCopy[cy][cx].isOccupied()&& !boardCopy[cy][cx].isWater())
					{	middleCoords[0] = cy; 
						middleCoords[1] = cx;
						middleCounter++;
					}
				}
			}
			
			if(middleCounter == 1 && middleCoords[0] == (dy+oy)/2 && middleCoords[1] == (dx+ox)/2) return true;
			
			else return false;
			
		}	
		
	}
	
	public void getNextPlayer() {
		boolean done = false;
		int i = 0;
		while(i < playerNumber && done != true){
            if (i == playerNo) {
            	if (i == playerNumber){
            		playerNo = 0;  
            		done = true;
            	}
            	else {
            		playerNo++;
            		done = true;
            	}
            }
            i++;
        }
	}
	
		
}
	
	


