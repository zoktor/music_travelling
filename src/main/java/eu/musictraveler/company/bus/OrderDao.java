package eu.musictraveler.company.bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import eu.musictraveler.company.model.ClientOrder;
import eu.musictraveler.company.model.CompanyOffer;
import eu.musictraveler.general.bus.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends BaseDao {
	static LinkedList<ClientOrder> clientOrderList;
	static List<CompanyOffer> list = new ArrayList<CompanyOffer>();
	
	{
		clientOrderList = new LinkedList<ClientOrder>();

		for (int i = 0; i < 10; i++) {
			ClientOrder c = new ClientOrder();
			c.setClorId(i);
			c.setLocation(String.valueOf(System.currentTimeMillis()));
			c.setOrderDate(new Date());
			c.setOrderStateClvaCode("order_state.active");
			clientOrderList.add(c);
		}
	}

	public List<ClientOrder> findClientOrders() {
		return clientOrderList;
	}

	public List<CompanyOffer> findCompanyOffers(Integer clorId) {
//		for (int i = 0; i < 11; i++) {
//			CompanyOffer co = new CompanyOffer();
//			co.setClorId(clorId);
//			co.setCoofId(i);
//			co.setExpirationDate(new Date());
//			co.setFullfiledDate(new Date());
//			list.add(co);
//		}
		return list;
	}

	public void saveOrder(ClientOrder clientOrder) {
		clientOrder.setClorId(clientOrderList.size());
		clientOrderList.addFirst(clientOrder);
	}

	public ClientOrder getClientOrder(Integer clorId) {
		return clientOrderList.get(clorId);
	}

	public void createOffer(CompanyOffer co) {
		co.setCoofId(findClientOrders().size());
		list.add(co);
	}

}