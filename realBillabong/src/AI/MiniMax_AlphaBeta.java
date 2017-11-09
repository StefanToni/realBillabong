package AI;

import java.util.*;

public class MiniMax_AlphaBeta
{
	 public enum SearchAlgorithm{
	        MINIMAX, ALPHA_BETA_PRUNING
	    }

	    public void apply(Node n, int maxDepth, SearchAlgorithm algorithm, Evaluator eval){
	        if(algorithm == SearchAlgorithm.MINIMAX){
	            minimax(n, maxDepth, eval);
	        }else if(algorithm == SearchAlgorithm.ALPHA_BETA_PRUNING){
	            alphaBetaPruning(n, maxDepth, eval);
	        }
	    }

	    private void minimax(Node n, int maxDepth, Evaluator eval) {
	        if (n.isEndGameNode()) {
	            System.out.println("H1");
	            return;
	        }
	        ArrayDeque<Node> searchStack = new ArrayDeque<Node>();
	        searchStack.push(n);

	        while (searchStack.isEmpty() == false) {
	            Node node = searchStack.pop();

	            Move nextMove = node.getNextMove();
	            boolean isRoot = node.isRoot();

	            if (node.isTerminal(maxDepth)) {
	                node.setValue(eval.evaluate(node));
	                if (!isRoot) {
	                    Node parent = node.getParent();
	                    if (parent.setValue(node.getValue())) {
	                        parent.setNextMove(node.getMoveLeadingHere());
	                    }
	                }
	            } else {
	                if (node.hasMoreChildren()) {
	                    searchStack.push(node);
	                    searchStack.push(node.getNextChild());
	                } else {
	                    if (nextMove != null) {
	                        if (!isRoot) {
	                            Node parent = node.getParent();
	                            if (parent.setValue(node.getValue())) {
	                                parent.setNextMove(node.getMoveLeadingHere());
	                            }
	                        }
	                    }
	                }
	            }

	            node.setVisited(true);
	            //print(node);
	        }
	    }


	    private void alphaBetaPruning(Node n, int maxDepth, Evaluator eval) {
	        if (n.isEndGameNode()) {
	            System.out.println("H1");
	            return;
	        }
	        ArrayDeque<Node> searchStack = new ArrayDeque<Node>();
	        searchStack.push(n);

	        while (searchStack.isEmpty() == false) {
	            Node node = searchStack.pop();

	            Move nextMove = node.getNextMove();
	            boolean isRoot = node.isRoot();

	            if (node.isTerminal(maxDepth)) {
	                node.setValue(eval.evaluate(node));
	                if (!isRoot) {
	                    Node parent = node.getParent();
	                    if (parent.setValue(node.getValue())) {
	                        parent.setNextMove(node.getMoveLeadingHere());
	                    }
	                }
	            } else {
	                if (node.hasMoreChildren()) {
	                    if (!isRoot) {
	                        Integer nodeValue = node.getValue();
	                        if(nodeValue!=null){
	                            Node parent = node.getParent();
	                            Integer parentValue = parent.getValue();
	                            if(parentValue==null){
	                                searchStack.push(node);
	                                searchStack.push(node.getNextChild());
	                            }else{
	                                NodeType type = parent.getType();
	                                if(type == NodeType.MAX){
	                                    //Pruning:
	                                    if(node.getValue()>=parentValue){
	                                        searchStack.push(node);
	                                        searchStack.push(node.getNextChild());
	                                    }
	                                }else{
	                                    //Pruning:
	                                    if(node.getValue()<=parentValue){
	                                        searchStack.push(node);
	                                        searchStack.push(node.getNextChild());
	                                    }
	                                }
	                            }
	                        }
	                        else{
	                            searchStack.push(node);
	                            searchStack.push(node.getNextChild());
	                        }
	                    }else{
	                        searchStack.push(node);
	                        searchStack.push(node.getNextChild());
	                    }
	                } else {
	                    if (nextMove != null) {
	                        if (!isRoot) {
	                            Node parent = node.getParent();
	                            if (parent.setValue(node.getValue())) {
	                                parent.setNextMove(node.getMoveLeadingHere());
	                            }
	                        }
	                    }
	                }
	            }

	            node.setVisited(true);
	            //print(node);
	        }
	    }

}
