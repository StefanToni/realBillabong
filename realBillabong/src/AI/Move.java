package AI;

import Game.Kangaroo;
import Game.Square;

public class Move {

	Kangaroo k;
	Square o;
	Square d;
	
	public Move(Kangaroo k, Square o, Square d){
		this.k = k ;
		this.o = o;
		this.d = d ;
	}
	
	//public Move(Kangaroo ka, int oy, Square o, Square d){
	//	k = ka ;
	//	this.o = new Square(oy, ox) ;
	//	d = new Square(dy, dx) ;
	//	
	//}
	
	public Kangaroo getKangaroo(){
		return k ;
	}
	
	public Square getOrigin(){
		return o ;
	}
	
	public Square getDest(){
		return d ;
	}
}
