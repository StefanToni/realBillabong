package Game;

public class Board {
	
	private Square[][] boardArray ;
	private Kangaroo referee ;
	private Kangaroo[] kangaroos ;

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

	public Kangaroo[] getKangaroos() {
		return kangaroos;
	}

	public void setKangaroos(Kangaroo[] kangaroos) {
		this.kangaroos = kangaroos;
	}
	

}
