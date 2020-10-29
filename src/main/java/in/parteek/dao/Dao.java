/**
 * 
 */
package in.parteek.dao;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.*;
import org.hibernate.cfg.*;

import in.parteek.beans.*;
import in.parteek.utils.AssignUtils;

/**
 * Created on : 2019-01-31, 8:22:42 a.m.
 *
 * @author Parteek Dheri
 */
public class Dao {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public String registerVoter(Voter voter) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String id = (String) session.save(voter);

		session.getTransaction().commit();
		session.close();
		return id;
	}

	public List<Voter> getRegersteredVoters() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Voter> voterList = (List<Voter>) session.createQuery("from Voter").getResultList();

		session.getTransaction().commit();
		session.close();

		return voterList;
	}

	public Set<String> getRegersteredVoterSINs() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Voter> criteria = cb.createQuery(Voter.class);
		Root<Voter> root = criteria.from(Voter.class);
		criteria.select(root.get("SIN"));
		List<?> voterList = session.createQuery(criteria).getResultList();
		session.getTransaction().commit();
		session.close();

		return new HashSet<String>((Collection<? extends String>) voterList);
	}

	public int registerMultipleVoter() {
		HashSet<Voter> voters = AssignUtils.generateVoters();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<Voter> iter = voters.iterator();

		while (iter.hasNext()) {
			session.save(iter.next());
		}

		session.getTransaction().commit();
		session.close();
		return voters.size();
	}

	/**
	 * 
	 * @return 0-> party 1-> vote and 2-> age statistics
	 */
	public ArrayList<HashMap<String, Integer>> getStats() {

		List<Voter> voters = getRegersteredVoters();
		Iterator<Voter> iter = voters.iterator();
//		HashMap<String, HashMap<String, Integer>> stats = new HashMap<String, HashMap<String, Integer>>();
		int totalVoters = 0;
		int totalVotedVoters = 0;
		int[] parties = new int[EnumSet.allOf(Vote.PARTY.class).size()];
		int[] ageGroups = new int[4];
		while (iter.hasNext()) {
			totalVoters++;
			Voter v = iter.next();
			if (v.getVote() == null) {
				continue;
			}
			totalVotedVoters++;
			parties[v.getVote().getParty().getValue()]++;
			int age = AssignUtils.calculateAge(LocalDate.parse(v.getBirthdate()));
			if (age > 60) {
				ageGroups[3]++;
			} else if (age > 45) {
				ageGroups[2]++;
			} else if (age > 30) {
				ageGroups[1]++;
			} else if (age >= 18) {
				ageGroups[0]++;
			} else {
				System.out.println("Huh, A minor I see here." + age);
			}
		}

		HashMap<String, Integer> partyStats = new HashMap<String, Integer>();
		for (int i = 0; i < parties.length; i++) {
			partyStats.put(Vote.PARTY.valueOf(i).toString(), parties[i]);
		}

		HashMap<String, Integer> votedStats = new HashMap<String, Integer>();
		votedStats.put("Voted", totalVotedVoters);
		votedStats.put("Not Voted", (totalVoters - totalVotedVoters));

		HashMap<String, Integer> ageStats = new HashMap<String, Integer>();
		ageStats.put("18 - 30", ageGroups[0]);
		ageStats.put("30 - 45", ageGroups[1]);
		ageStats.put("45 - 60", ageGroups[2]);
		ageStats.put("60 + ", ageGroups[3]);

		ArrayList<HashMap<String, Integer>> stats = new ArrayList<HashMap<String, Integer>>();
		stats.add(partyStats);
		stats.add(votedStats);
		stats.add(ageStats);
		return stats;
	}

	public void castVote(String voter, String party) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Vote vote = new Vote();
		vote.setParty(Vote.PARTY.valueOf(party));
		Voter db_voter = session.get(Voter.class, voter);
		if (db_voter == null) {
			throw new Exception("Voter not Registered");
		}
		if (db_voter.getVote() != null) {
			throw new Exception("You have already voted, can't vote twice");
		}
		db_voter.setVote(vote);
		vote.setVoter(db_voter);
		session.save(vote);

		session.getTransaction().commit();
		session.close();
	}

	public int castMultipleVote() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Set<String> voters = getRegersteredVoterSINs();
		Iterator<String> iter = voters.iterator();
		int count = 0;

		while (iter.hasNext()) {
			Voter v = session.get(Voter.class, iter.next());
			Vote vote = AssignUtils.getRandomVote();
			if (vote == null) {
				continue;
			}
			vote.setVoter(v);
			session.save(vote);
			v.setVote(vote);
			if (++count % 70 == 0) {
				session.flush();
				session.clear();
			}
		}

		session.getTransaction().commit();
		session.close();
		return count;
	}

	public Voter getVoterBySIN(String sin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Voter voter = session.get(Voter.class, sin);

		session.getTransaction().commit();
		session.close();
		return voter;
	}

	public void editVoter(Voter v) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Voter voter = session.get(Voter.class, v.getSIN());
		try {
			voter.clone(v);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return;
	}

	public void deleteVoter(String sin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Voter voter = session.get(Voter.class, sin);
		session.delete(voter);
		
		
		
		session.getTransaction().commit();
		session.close();

	}

}
