package Game;

public class Board {
	
	private Square[][] boardArray = new Square[14][16] ;
	private Kangaroo referee ;

	public Square[][] getBoardArray() {
		return boardArray ;
	}

	public void setBoardArray(Square[][] newBoard) {
		boardArray = newBoard ;
	}

	public Kangaroo getReferee() {
		return referee;
	}

	public void setReferee(Kangaroo referee) {
		this.referee = referee;
	}

}
