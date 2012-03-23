package eu.musictraveler.company.model;


import java.util.Date;

import lombok.Data;

/**
 * @author Pavel Gorohhovatski
 * 
 */
@Data
public  class CompanyOffer {
	Integer coofId;
	Integer cowoId;
	Integer clorId;
	Date fullfiledDate;
	Date expirationDate;
	String companyName;

}
