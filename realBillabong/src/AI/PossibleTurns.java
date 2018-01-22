package AI;

import java.util.ArrayList;

import Game.Kangaroo;
import Game.Player;
import Game.Square;
import realBillabong.Main;

public class PossibleTurns {
	
	
	private void checkAllMoves(Square[][] b)
	{	
		Player currentPlayer = Main.getState().getLoop().getCurrentPlayer();
		Kangaroo current; 
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		ArrayList<Kangaroo> kList = currentPlayer.getKangaroos();
//		for(int i = 0; i < 14; i++){
//			for(int j = 0 ;  j < 16; j++){
//				
//				if(b[i][j].isOccupied() && b[i][j].getIsHere().getTeam() == currentPlayer.getColor()){
//					current = b[i][j].getIsHere() ;
//					for(int y = 0; y < 14; y++){
//						for(int x = 0; x < 16; x++){
//							
//							if(current.checkLegal(j, i, x, y, b[y][x])){
//								Move m = new Move(current, b[i][j], b[y][x]) ;
//								Boolean legal = true;
//								while(legal == true)
//								{
//									
//								}
//								possibleMoves.add(m) ;
//								//System.out.println("move " + y + " " + x + " added to list");
//							}
//						}
//					}
//				}
//				
//			}
//		}
		
		for(int i = 0; i< kList.size(); i++)
		{
			current = kList.get(i);
			int ax = current.getPosition().getxLoc();
			int by = current.getPosition().getyLoc();
			
			for(int y = 0; y < 14; y++){
				for(int x = 0; x < 16; x++){
					
					
					if(current.checkLegal(ax, by, x, y, b[y][x])){
						Move m = new Move(current, b[by][ax], b[y][x]) ;
						Boolean legal = true;
						while(legal == true)
						{
							
						}
						possibleMoves.add(m) ;
						//System.out.println("move " + y + " " + x + " added to list");
					}
				}
			}
		}
		
	}

}
