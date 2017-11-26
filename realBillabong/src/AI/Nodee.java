package AI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Nodee {
	 State state;
	    Nodee parent;
	    List<Nodee> childArray;

	    public Nodee() {
	        this.state = new State();
	        childArray = new ArrayList<>();
	    }

	    public Nodee(State state) {
	        this.state = state;
	        childArray = new ArrayList<>();
	    }

	    public Nodee(State state, Nodee parent, List<Nodee> childArray) {
	        this.state = state;
	        this.parent = parent;
	        this.childArray = childArray;
	    }

	    public Nodee(Nodee nodee) {
	        this.childArray = new ArrayList<>();
	        this.state = new State(nodee.getState());
	        if (nodee.getParent() != null)
	            this.parent = nodee.getParent();
	        List<Nodee> childArray = nodee.getChildArray();
	        for (Nodee child : childArray) {
	            this.childArray.add(new Nodee(child));
	        }
	    }

	    public State getState() {
	        return state;
	    }

	    public void setState(State state) {
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