/**
 * 
 */
package in.parteek.beans;

import javax.persistence.*;
import lombok.*;

@Embeddable

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * Created on : 2019-02-06, 11:29:06 p.m.
 *
 * @author Parteek Dheri
 */
public class Address {

	String number;
	String street;
	String city;
	String province;
	String postal;
}
