package AI;

import java.util.Collections;
import java.util.Comparator;
import java.util.Comparator;
import java.util.List;
import AI.Nodee;

public class UCT {

	  public static double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {
	        if (nodeVisit == 0) {
	            return Integer.MAX_VALUE;
	        }
	        return (nodeWinScore / (double) nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
	    }

	    static Nodee findBestNodeWithUCT(Nodee node) {
	        int parentVisit = node.getState().getVisitCount();
	        return Collections.max(
	          node.getChildArray(),
	          Comparator.comparing(c -> uctValue(parentVisit, c.getState().getWinScore(), c.getState().getVisitCount())));
	    }
}
