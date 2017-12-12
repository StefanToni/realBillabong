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
	
	public Move(Kangaroo ka, int oy, int ox, int dy, int dx){
		k = ka ;
		o = new Square(oy, ox) ;
		d = new Square(dy, dx) ;
		
	}
	
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
