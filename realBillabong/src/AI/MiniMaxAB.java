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
	private ArrayList<Move> possibleMoves ;
	private int tx, ty, tnx, tny = -1;
	private double bestScore ;
	private ArrayList<Player> players ;
	private Move bestMove ; //this is where the move needs to be stored to be getted after 
	
	public MiniMaxAB(Player og, int lvl, Square[][] bo){
		curren = og ;
		level = lvl ;
		board = bo ;
		diff = new Diffuser() ;
		originalGangster = og ;
		possibleMoves = new ArrayList<Move>() ;
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
	
	public double miniMaxABMove(int l, Square[][] b){
		int rooC = checkRooCount() ;
		rooC -= 8 ;
		System.out.println("there are " + rooC + " roos on the field") ;
		curren = Main.getState().getLoop().getCurrentPlayer() ;
		//evaluation/base case
		double score = 0 ;
		if(curren.haveIWon() || l == 0){
			System.out.println("base case");
			for(int i = 0; i < 5; i++){
				int x = curren.getKangaroos().get(i).getPosition().getxLoc() ;
				int y = curren.getKangaroos().get(i).getPosition().getyLoc() ;
				double rooScore = diff.getWeight(x, y) ;
				if(curren.getKangaroos().get(i).lapCounter > 2){
					rooScore = rooScore + 1000000.0 ;
					System.out.println("score increased due to lapcounter > 2");
				}
				score = score + rooScore ;
			}
			return score ;
		}
		else{
			//load all possible moves
			checkForMoves() ;
			System.out.println(possibleMoves.size() + " possible moves");
			for(int i = 0; i < possibleMoves.size() - 1; i++){
				curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getOrigin(), possibleMoves.get(i).getDest());
			
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
					curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin());
					
					if(score > bestScore){
						Move temp = possibleMoves.get(i) ;
						bestScore = score ;
						bestMove = temp ;
						System.out.println("Move " + i + " selected");
					}
					System.out.println(bestScore);
					return bestScore ;
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
					curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin());
					
					if(score < bestScore){
						Move temp = possibleMoves.get(i) ;
						bestScore = score ;
						bestMove = temp ;						
						System.out.println("Move " + i + " selected");
					}
					System.out.println(bestScore);
					return bestScore ;
				}
			}
			System.out.println(bestScore);
			return bestScore ;
		}
		
			
	}
		
		
		
		
		
	
	
	public void checkForMoves(){
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
		
		
		
	}
	
	
	
	
}
