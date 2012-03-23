package eu.musictraveler.company.bus;

import java.util.List;

import eu.musictraveler.client.ClientOrderListener;
import eu.musictraveler.company.model.ClientOrder;
import eu.musictraveler.company.model.CompanyOffer;

public interface OrderService{
	
	List<ClientOrder> findClientOrders();
	List<CompanyOffer> findCompanyOffers(Integer clorId);
	
	boolean createAgreement(Integer coofId);

	void saveOrder(ClientOrder clientOrder);
	void addClientOrderListener(ClientOrderListener listener);
	void removeListener(ClientOrderListener listener);

	ClientOrder getClientOrder(Integer clorId);
	void createOffer(Integer clorId);
}