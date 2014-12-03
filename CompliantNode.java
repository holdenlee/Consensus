import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class CompliantNode implements Node {

	private int numFs;
	private HashMap<Integer, Integer> votes;
	private int currentRound;
	private int totalRounds;
	Set<Transaction> selfTxs;
	
	public double threshold() {
		return 0.5*currentRound/totalRounds;
	}
	
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
		currentRound=0;
		votes = new HashMap<Integer,Integer>();
		totalRounds = numRounds;
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
		Set<Transaction> txs = new HashSet<Transaction>();
		//loop through and send off majorities.
		for (Transaction t: selfTxs) {
			addVote(t.id);
		}
		
		for (Integer i: votes.keySet()) {
			if (votes.get(i)>((numFs+1)*threshold())) {
				txs.add(new Transaction(i));
			}
		}
		selfTxs = new HashSet<Transaction>(txs);
		//System.out.println("txs: "+txs.size()+ " selfTxs: "+selfTxs.size());
		currentRound ++;
		return txs;
	}

	public void setFollowees(boolean[] followees) {
		for (int i=0; i<followees.length; i++) {
			if (followees[i]) {
				numFs++;
			}
		}
		return;
	}

	public void setPendingTransaction(Set<Transaction> pendingTransactions) {
		selfTxs = new HashSet<Transaction>(pendingTransactions);
		return;
	}

}
