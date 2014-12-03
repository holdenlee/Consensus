import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class CompliantNode implements Node {

	private int numFs;
	private HashMap<Integer, Integer> votes;
	private int rounds;
	
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
		rounds=0;
		votes = new HashMap<Integer,Integer>();
	}

	//id of tx, then id of broadcaster
	public void receiveCandidates(ArrayList<Integer[]> candidates) {
		numFs++;
		for (Integer[] list: candidates) {
			for (int i=0;i<list.length;i++){
				addVote(list[0]);
			}
		}
	}

	public Set<Transaction> getProposals() {
		Set<Transaction> txs = new HashSet<Transaction>();
		//loop through and send off majorities.
		for (Integer i: votes.keySet()) {
			if (votes.get(i)>(numFs/2.0)) {
				txs.add(new Transaction(votes.get(i)));
			}
		}
		return txs;
	}

	public void setFollowees(boolean[] followees) {
		return;
	}

	public void setPendingTransaction(Set<Transaction> pendingTransactions) {
		return;
	}

}
