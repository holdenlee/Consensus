import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class CompliantNode implements Node {

	private int numFs;
	private HashMap<Integer, Integer> votes;
	
	public void addVote(int id) {
		Integer id2=new Integer(id);
		if (votes.containsKey(id2)) {
			votes.put(id, votes.get(id)+1);
		} else {
			votes.put(id, new Integer(1));
		}
	}
	
	public CompliantNode(double p_graph, double p_malicious,
			double p_txDistribution, int numRounds) {
		// TODO Auto-generated constructor stub
		numFs=0;
	}

	//id of tx, then id of broadcaster
	public void receiveCandidates(ArrayList<Integer[]> candidates) {
		for (Integer[] list: candidates) {
			for (int i=0;i<list.length;i++){
				addVote(list[0]);
			}
		}
	}

	public Set<Transaction> getProposals() {
		//loop through and send off majorities.
		return new HashSet<Transaction>();
	}

	public void setFollowees(boolean[] followees) {
		return;
	}

	public void setPendingTransaction(Set<Transaction> pendingTransactions) {
		return;
	}

}
