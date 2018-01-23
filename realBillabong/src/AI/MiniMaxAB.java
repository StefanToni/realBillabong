package AI;

import java.util.ArrayList;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class MiniMaxAB {
	
	private Player curren ;
	private Player originalGangster ;
	private int level ;
	private Square[][] board ;
	private Diffuser diff ;
	private int tx, ty, tnx, tny = -1;
	private double bestScore ;
	private ArrayList<Player> players ;
	private Move bestMove ; //this is where the move needs to be stored to be getted after 
	double score ;
	private int callcntr ;
	
	public MiniMaxAB(Player og, int lvl, Square[][] bo){
		curren = og ;
		level = lvl ;		
		board = bo ;
		diff = new Diffuser() ;
		originalGangster = og ;
		players = Main.getState().getLoop().getPlayers() ;
		miniMaxABMove(level, board) ;
		performMove() ;
	}
	
	public Move getBestMove(){
		return bestMove ;
	}
	
	private void performMove(){
		
		//System.out.println("perform move");
		Kangaroo k = bestMove.getKangaroo() ;
		Square o = bestMove.getOrigin() ;
		Square d = bestMove.getDest() ;
		//timeEnd = System.currentTimeMillis();
		//timeTaken = timeEnd - timeStart;
		//System.out.println("Time taken: " + timeTaken + " milliseconds");
		//Main.getState().getLoop().addTime(timeTaken);
		Main.getState().getLoop().getCurrentPlayer().performMove(k, o, d) ;
		//System.out.println("move performed: " + d.getxLoc()+ " "+  d.getyLoc());
		Main.getState().getComponent().repaint();
		//if(Math.abs(o.getxLoc()- d.getxLoc()) == 1 || Math.abs(o.getyLoc()- d.getyLoc()) == 1 ) k.terminateTurn();
		if(k.getLapCounter() == 3) k.finishKangaroo();
	}
	
	
	private int checkRooCount(){
		int cntr = 0 ;
		System.out.println("roopostions:");
		for(int i = 0; i < 14; i++){
			for(int j = 0 ;  j < 16; j++){
				if(board[i][j].isOccupied()){
					cntr++;
					System.out.println("x: " + j + ", y: " + i);
				}
			}
		}
		return cntr ;
	}
	
	private double baseCase(){
		System.out.println("base case");
		for(int i = 0; i < curren.getKangaroos().size(); i++){
			int x = curren.getKangaroos().get(i).getPosition().getxLoc() ;
			int y = curren.getKangaroos().get(i).getPosition().getyLoc() ;
			double rooScore = diff.getWeight(x, y) ;

			if(curren.getKangaroos().get(i).lapCounter > 0){

				rooScore = rooScore + 1000000.0 ;
				
				System.out.println("score increased due to lapcounter > 0");
			}
			if(curren.getKangaroos().get(i).lapCounter > 1){
				rooScore = rooScore + 1000000.0 ;
				System.out.println("score increased due to lapcounter > 1");
			}
			if(curren.getKangaroos().get(i).lapCounter > 2){
				rooScore = rooScore + 1000000.0 ;
				System.out.println("score increased due to lapcounter > 2");
			}
			
			score = score + rooScore ;
		}
		if(curren.getKangaroos().size() < 5){
			score = score + 3000000.0 ;
			System.out.println("1 roo gone");
		}
		if(curren.getKangaroos().size() < 4){
			score = score + 3000000.0 ;
			System.out.println("2 roo gone");
		}
		if(curren.getKangaroos().size() < 3){
			score = score + 3000000.0 ;
			System.out.println("3 roo gone");
		}
		if(curren.getKangaroos().size() < 2){
			score = score + 3000000.0 ;
			System.out.println("4 roo gone");
		}
		if(curren.getKangaroos().size() < 1){
			score = score + 3000000.0 ;
			System.out.println("5 roo gone");
		}
		System.out.println("score is : " + score);
		return score ;
	}
	
	private ArrayList<Move> loadMoves(){
		ArrayList<Move> possibleMoves = new ArrayList<Move>() ;
		Kangaroo current; 
		
		for(int i = 0; i < 14; i++){
			for(int j = 0 ;  j < 16; j++){
				if(board[i][j].isOccupied() && board[i][j].getIsHere().getTeam() == curren.getColor()){
					current = board[i][j].getIsHere() ;
					for(int y = 0; y < 14; y++){
						for(int x = 0; x < 16; x++){
							if(tx!=-1 && ((tx == j && ty == i && tnx == x && tny == y)||(tx == i && ty == j && tnx == y && tny == x)) )
							{
								System.out.println( "Move is not added to movelist!" );
							}
							else if(current.checkLegal(j, i, x, y, board[y][x])){
								Move m = new Move(current, board[i][j], board[y][x]) ;
								possibleMoves.add(m) ;
								//System.out.println("move " + y + " " + x + " added to list");
							}
						}
					}
				}
				
			}
		}
		return possibleMoves ;
	}
	
	private void performMove(Kangaroo k, Square o, Square d, Square[][] b){
		k.setPosition(d);
		o.empty();
		d.fill(k);
		
		
		
		if(Math.abs(o.getxLoc()- d.getxLoc()) <= 1 || Math.abs(o.getyLoc()- d.getyLoc()) <= 1){
			
			for(int i = 0; i < 14; i++){
				for(int j = 0 ;  j < 16; j++){
					if(b[i][j].isOccupied() && b[i][j].getIsHere().getTeam() == 10){
						Kangaroo t = b[i][j].getIsHere() ;
						b[i][j].empty();
						t = null ;
					}
				}
			}
			
			if(curren.getColor() == players.size()){
				curren = players.get(0) ;
			}
			else{
				curren = players.get(curren.getColor()) ;
			}
		}
	}
	
	public double miniMaxABMove(int l, Square[][] b){
		callcntr++;
		System.out.println("callcntr is: " + callcntr);
		int rooC = checkRooCount() ;
		rooC -= 8 ; // because of 8 being occupied by default
		System.out.println("there are " + rooC + " roos on the field") ;
		//evaluation/base case

		if(curren.haveIWon() || l == 0){

			return baseCase() ;
			
		}
		
		else{
			//load all possible moves
			ArrayList<Move> possibleMoves = new ArrayList<Move>() ;
			possibleMoves = loadMoves() ;
			
			
			//for all moves perform recursive minimax
			System.out.println(possibleMoves.size() + " possible moves . . . . . .. . . . .. ");
			for(int i = 0; i < possibleMoves.size() - 1; i++){
				System.out.println("i is : " + i + " ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !") ;
				Square[][] temp = new Square[14][16] ;
				for(int a = 0; a < 14; a++){
					for(int c = 0; c < 16; c++){
						Square s =  board[a][c] ;
						temp[a][c] = new Square(s) ;
					}
				}
				performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getOrigin(), possibleMoves.get(i).getDest(), temp);
			
				if(curren == originalGangster){ //max player
					System.out.println("max player");
					bestScore = Integer.MIN_VALUE ;
					/*if(curren.getColor() == players.size() - 1){
						curren = players.get(0) ;
					}
					else{
						curren = players.get(curren.getColor() + 1) ;
					}*/
					score = miniMaxABMove( l - 1, b) ;
					performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin(), temp);
					
					if(score > bestScore){
						Move tempMove = possibleMoves.get(i) ;
						bestScore = score ;
						bestMove = tempMove ;
						System.out.println("Move " + i + " selected");
					}
					System.out.println(bestScore);
					//return bestScore ;					
				}
				else{ // min player
					System.out.println("min player");
					bestScore = Integer.MAX_VALUE ;
					/*if(curren.getColor() == players.size()){
						curren = players.get(0) ;
					}
					else{
						curren = players.get(curren.getColor() + 1) ;
					}*/
					score = miniMaxABMove( l - 1, b) ;
					performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin(), temp);
					
					if(score < bestScore){
						Move tempMove = possibleMoves.get(i) ;
						bestScore = score ;
						bestMove = tempMove ;						
						System.out.println("Move " + i + " selected");
					}
					System.out.println(bestScore);
					//return bestScore ;
				}
			}
			System.out.println(bestScore + " last return ! ! 1 1 1 1 1 1 ");
			return bestScore; 
		}
		
			
	}
		
		
		
		
		
	
	
	/*public void checkForMoves(){
		//System.out.println("checking moves");
		Kangaroo current; 
		
		for(int i = 0; i < 14; i++){
			for(int j = 0 ;  j < 16; j++){
				if(board[i][j].isOccupied() && board[i][j].getIsHere().getTeam() == curren.getColor()){
					current = board[i][j].getIsHere() ;
					for(int y = 0; y < 14; y++){
						for(int x = 0; x < 16; x++){
							if(tx!=-1 && ((tx == j && ty == i && tnx == x && tny == y)||(tx == i && ty == j && tnx == y && tny == x)) )
							{
								System.out.println( "Move is not added to movelist!" );
							}
							else if(current.checkLegal(j, i, x, y, board[y][x])){
								Move m = new Move(current, board[i][j], board[y][x]) ;
								possibleMoves.add(m) ;
								//System.out.println("move " + y + " " + x + " added to list");
							}
						}
					}
				}
				
			}
		}
		
		
		
	}*/
	
	
	
	
}
