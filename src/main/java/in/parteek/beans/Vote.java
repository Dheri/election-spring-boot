/**
 * 
 */
package in.parteek.beans;

import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.EnumSet;
import java.util.HashMap;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
/**
 * Created on : 2019-02-06, 11:31:35 p.m.
 *
 * @author Parteek Dheri
 */
public class Vote {
	private PARTY party;
	@OneToOne
	private Voter voter;
	@Id
	@GeneratedValue
	private int voteId;
	@Transient
	private EnumSet<PARTY> parties = EnumSet.allOf(PARTY.class);

	public Vote(PARTY party) {
		this.party = party;
	}

	public enum PARTY {
		LIBERAL_PARTY(0), CONSERVATIVE_PARTY(1), NEW_DEMOCRATIC_PARTY(2), BLOC_QUEBECOIS(3), GREEN_PARTY(4);

		private final int value;
		private static Map<Integer, PARTY> map = new HashMap<>();
		static {
			for (PARTY party : PARTY.values()) {
				map.put(party.value, party);
			}
		}

		PARTY(final int newValue) {
			value = newValue;
		}

		public int getValue() {
			return value;
		}

		public static PARTY valueOf(int party) {
			return (PARTY) map.get(party);
		}

		@Override
		public String toString() {
			String value = this.name();
			value.replaceAll("_", " ");
			return value;
		}

	}

}