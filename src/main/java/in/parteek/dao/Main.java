/**
 * 
 */
package in.parteek.dao;

import java.util.ArrayList;
import java.util.HashMap;

import in.parteek.beans.*;
import in.parteek.utils.AssignUtils;

/**
 * Created on : 2019-02-07, 12:11:49 a.m.
 *
 * @author Parteek Dheri
 */
public class Main {
	public static void main(String[] args) {
		// AssignUtils.getRegersteredVoterSINs();
		testStats();
//		String s = Arrays.toString(AssignUtils.getRandomVote());
//		System.out.println(s);
	}

	public static void testStats() {
		Dao dao = new Dao();
		ArrayList<HashMap<String, Integer>> stats = dao.getStats();

		HashMap<String, Integer> ageStats = stats.get(1);
		for (String name : ageStats.keySet()) {

			String key = name.toString();
			String value = ageStats.get(name).toString();
			System.out.println(key + " " + value);

		}
	}

	public static void testGenerateVoters() {
		Dao dao = new Dao();
		dao.registerMultipleVoter();
		int i = dao.castMultipleVote();
		System.out.println(i);

	}

	public static void testDates() {
		for (int i = 0; i < 992; i++) {
			System.out.println(i + " _ " + AssignUtils.getRandomDOB().toString());
		}
	}

	public static void testDao() {
		Dao dao = new Dao();
		dao.registerMultipleVoter();
	}

	public static void testAssignUtils() {
		Address add;
		add = AssignUtils.generateRandomAddress();

		System.out.println(AssignUtils.generateRandomPostal());
		System.out.println(add);
		for (int i = 0; i < 2; i++) {
			System.out.println(AssignUtils.generateRandomSIN());
		}
		System.out.println("-> Done <-");
	}

}
