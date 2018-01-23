package AI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import realBillabong.Main;

public class Nodee {
	 	MState state;
	    Nodee parent;
	    List<Nodee> childArray;
	    int playerNumber;

	    public Nodee() {
	    	this.playerNumber = Main.getState().getLoop().getPlayers().size();
	        this.state = new MState(playerNumber);
	        childArray = new ArrayList<>();
	    }

	    public Nodee(MState state) {
	        this.playerNumber = Main.getState().getLoop().getPlayers().size();
	        this.state = state;
	        childArray = new ArrayList<>();
	    }

	    public Nodee(MState state, Nodee parent, List<Nodee> childArray) {
	        this.state = state;
	        this.parent = parent;
	        this.childArray = childArray;
	        this.playerNumber = Main.getState().getLoop().getPlayers().size();
	    }

	    public Nodee(Nodee nodee) {
	        this.childArray = new ArrayList<>();
	        this.state = new MState(nodee.getState());
	        if (nodee.getParent() != null)
	            this.parent = nodee.getParent();
	        List<Nodee> childArray = nodee.getChildArray();
	        for (Nodee child : childArray) {
	            this.childArray.add(new Nodee(child));
	        }
	    }

	    public MState getState() {
	        return state;
	    }

	    public void setState(MState state) {
	        this.state = state;
	    }

	    public Nodee getParent() {
	        return parent;
	    }

	    public void setParent(Nodee parent) {
	        this.parent = parent;
	    }

	    public List<Nodee> getChildArray() {
	        return childArray;
	    }

	    public void setChildArray(List<Nodee> childArray) {
	        this.childArray = childArray;
	    }

	    public Nodee getRandomChildNodee() {
	        int noOfPossibleMoves = this.childArray.size();
	        int selectRandom = (int) (Math.random() * ((noOfPossibleMoves - 1) + 1));
	        return this.childArray.get(selectRandom);
	    }

	    public Nodee getChildWithMaxScore() {
	        return Collections.max(this.childArray, Comparator.comparing(c -> {
	            return c.getState().getVisitCount();
	        }));
	    }

}
