/**
 * 
 */
package eu.musictraveler.company.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Pavel Gorohhovatski
 * 
 */
@Data
public class ClientOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	Integer clorId;
	Integer offersCount;
	String orderStateClvaCode;
	String location;
	Date orderDate;
	Date orderRealizationDate;

}
