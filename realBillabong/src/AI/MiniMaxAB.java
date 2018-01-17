package AI;

import java.util.ArrayList;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class MiniMaxAB {
	
	Player curren ;
	Player originalGangster ;
	int level ;
	Square[][] board ;
	Diffuser diff ;
	double score ;
	ArrayList<Move> possibleMoves ;
	private int tx, ty, tnx, tny = -1;
	double bestScore ;
	ArrayList<Player> players ;
	
	public MiniMaxAB(Player p, Player og, int lvl, Square[][] bo){
		curren = og ;
		level = lvl ;
		board = bo ;
		diff = new Diffuser() ;
		originalGangster = og ;
		possibleMoves = new ArrayList<Move>() ;
		players = Main.getState().getLoop().getPlayers() ;
	}
	
	public double miniMaxABMove(int l, Square[][] b){
		if(curren.haveIWon() || l == 0){
			for(int i = 0; i < 5; i++){
				int x = curren.getKangaroos().get(i).getPosition().getxLoc() ;
				int y = curren.getKangaroos().get(i).getPosition().getyLoc() ;
				double rooScore = diff.getWeight(x, y) ;
				if(curren.getKangaroos().get(i).lapCounter > 2){
					rooScore = rooScore + 1000000.0 ;
				}
				score = score + rooScore ;
			}
			return score ;
		}
		else{
			
			checkForMoves() ;
			for(int i = 0; i < possibleMoves.size() - 1; i++){
				curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getOrigin(), possibleMoves.get(i).getDest());
			
				if(curren == originalGangster){ //max player
					bestScore = Integer.MIN_VALUE ;
					if(curren.getColor() == players.size() - 1){
						curren = players.get(0) ;
					}
					else{
						curren = players.get(curren.getColor() + 1) ;
					}
					score = miniMaxABMove( l - 1, b) ;
					curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin());
					if(score > bestScore){
						bestScore = score ;
					}
					return bestScore ;
				}
				else{ // min player
					bestScore = Integer.MAX_VALUE ;
					if(curren.getColor() == players.size()){
						curren = players.get(0) ;
					}
					else{
						curren = players.get(curren.getColor() + 1) ;
					}
					score = miniMaxABMove( l - 1, b) ;
					curren.performMove(possibleMoves.get(i).getKangaroo(), possibleMoves.get(i).getDest(), possibleMoves.get(i).getOrigin());
					if(score < bestScore){
						bestScore = score ;
					}
					return bestScore ;
				}
			}
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
