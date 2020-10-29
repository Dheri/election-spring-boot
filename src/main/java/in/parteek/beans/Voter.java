/**
 * 
 */
package in.parteek.beans;

import java.time.*;
import java.util.*;
import java.util.regex.*;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

/**
 * Created on : 2019-02-06, 11:19:53 p.m.
 *
 * @author Parteek Dheri
 */
public class Voter {
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private Address address;
	@Id
	private String SIN;
	@OneToOne(cascade = CascadeType.ALL)
	private Vote vote;

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param address
	 * @param SIN
	 * @throws Exception
	 */
	public Voter(String firstName, String lastName, String birthdate, Address address, String SIN) throws Exception {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		setBirthdate(birthdate);
		this.address = address;
		setSIN(SIN);
	}

	// for cloning voter with same sin
	public void clone(Voter v) throws Exception {
		this.firstName = v.firstName;
		this.lastName = v.lastName;
		this.setBirthdate(v.getBirthdate());
		this.address = v.getAddress();
		setSIN(v.getSIN());
		return;
	}

	public void setSIN(String sin) throws Exception {
		String pattern = "^[1-9]\\d{8}$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(sin);

		if (m.find()) {
			this.SIN = sin;
		} else {
			throw new Exception("Invalid SIN");
		}

	}

	public void setBirthdate(String birthdate) throws Exception {
		LocalDate bDate = LocalDate.parse(birthdate);
		if (Period.between(bDate, LocalDate.now()).getYears() > 100) {
			throw new Exception("Invalid age");
		}

		if (Period.between(bDate, LocalDate.now()).getYears() >= 18) {
			this.birthdate = bDate;
		} else {
			throw new Exception("Invalid age");
		}
	}

	public String getBirthdate() {
		if (birthdate == null) {
			return "1998-03-14";
		}
		return this.birthdate.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(SIN);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Voter) {
			return this.getSIN().equals(((Voter) o).getSIN());
		}
		return false;
	}

}
