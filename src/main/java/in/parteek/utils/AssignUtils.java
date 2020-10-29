/**
 * 
 */
package in.parteek.utils;

import java.io.*;
import java.util.*;

import org.springframework.core.io.ClassPathResource;

import java.time.*;

import in.parteek.beans.*;
import in.parteek.beans.Vote.PARTY;

/**
 * Created on : 2019-02-18, 6:43:14 p.m.
 *
 * @author Parteek Dheri
 */
public class AssignUtils {
	private static Random rd = new Random();
	private static final String STREET_PATH = "Street.list";
	private static final String CITY_PATH = "City.list";
	private static final String PROVINCE_PATH = "Province.list";
	private static final String FNAME_PATH = "FName.list";
	private static final String LNAME_PATH = "LName.list";
	private static ArrayList<Vote.PARTY> parties = new ArrayList<PARTY>();
	static {
		parties.addAll(EnumSet.allOf(Vote.PARTY.class));
		Collections.shuffle(parties);
	}

	private static final int VOTER_NUM = 350;

	private static List<String> cities = getListFromFile(CITY_PATH);
	private static List<String> streets = getListFromFile(STREET_PATH);
	private static List<String> provinces = getListFromFile(PROVINCE_PATH);
	private static List<String> fName = getListFromFile(FNAME_PATH);
	private static List<String> lName = getListFromFile(LNAME_PATH);

	public static Vote getRandomVote() {
		int random = 3 + rd.nextInt(350);
		if (random > 220) {
			return null;
		}
		int index = (int) Math.log(random);
		Vote vote = new Vote();
		vote.setParty(parties.get(index - 1));

		return vote;
	}

	public static HashSet<Voter> generateVoters() {
		HashSet<Voter> set = new HashSet<Voter>();
		for (int i = 0; i < VOTER_NUM; i++) {
			Voter v = new Voter();
			v.setAddress(generateRandomAddress());
			try {
				v.setBirthdate(getRandomDOB().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v.setFirstName(getRandomListMember(fName));
			v.setLastName(getRandomListMember(lName));
			try {
				v.setSIN(generateRandomSIN());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			set.add(v);
		}
		return set;
	}

	public static String generateRandomSIN() {
		String digits = "0123456789";
		StringBuilder sb = new StringBuilder();
		sb.append(digits.charAt(1 + rd.nextInt(digits.length() - 1)));
		for (int i = 0; i < 8; i++) {
			sb.append(digits.charAt(rd.nextInt(digits.length())));
		}
		return sb.toString();
	}

	public static String generateRandomPostal() {
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(alphabets.charAt(rd.nextInt(alphabets.length())));
			sb.append(digits.charAt(rd.nextInt(digits.length())));
		}
		// insert space in middle
		sb.insert(3, ' ');
		return sb.toString();
	}

	public static Address generateRandomAddress() {
		Address address = new Address();
		address.setNumber(rd.nextInt(999) + "");
		address.setStreet(getRandomListMember(streets));
		address.setCity(getRandomListMember(cities));
		address.setProvince(getRandomListMember(provinces));
		address.setPostal(generateRandomPostal());
		return address;
	}

	private static String getRandomListMember(List<String> list) {
		return list.get(rd.nextInt(list.size()));
	}

	private static List<String> getListFromFile(String filePath) {
		InputStream input = null;

		try {
			File file = new ClassPathResource(filePath).getFile();
			input = new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner s = new Scanner(input);
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()) {
			list.add(s.nextLine());
		}
		s.close();
		return list;
	}

	public static LocalDate getRandomDOB() {
		GregorianCalendar gc = new GregorianCalendar();

		Calendar today = Calendar.getInstance();
		int year = randBetween(today.get(GregorianCalendar.YEAR) - 99, today.get(GregorianCalendar.YEAR) - 18);
		gc.set(GregorianCalendar.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
		gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

		LocalDate localDate = Instant.ofEpochMilli(gc.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}

	public static int calculateAge(LocalDate birthDate) {
		if ((birthDate != null)) {
			return Period.between(birthDate, LocalDate.now()).getYears();
		} else {
			return 0;
		}
	}

	private static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
