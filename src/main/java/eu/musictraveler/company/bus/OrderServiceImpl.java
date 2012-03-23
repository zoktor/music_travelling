package eu.musictraveler.company.bus;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import eu.musictraveler.client.ClientOrderListener;
import eu.musictraveler.company.model.ClientOrder;
import eu.musictraveler.company.model.CompanyOffer;
import eu.musictraveler.general.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Gorohhovatski
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderDao orderDao;

	private final Set<ClientOrderListener> listeners = new CopyOnWriteArraySet<ClientOrderListener>();
	private static final ExecutorService executorService = Executors
			.newFixedThreadPool(5);

	@Override
	public List<ClientOrder> findClientOrders() {
		return orderDao.findClientOrders();
	}

	@Override
	public List<CompanyOffer> findCompanyOffers(Integer clorId) {
		return orderDao.findCompanyOffers(clorId);
	}

	@Override
	public boolean createAgreement(Integer coofId) {
		if (coofId > 3) {
			return false;
		}
		return true;
	}

	@Override
	public void saveOrder(ClientOrder clientOrder) {
		orderDao.saveOrder(clientOrder);
		notifyClientOrderListeners(clientOrder);
	}

	@Override
	public ClientOrder getClientOrder(Integer clorId) {
		return orderDao.getClientOrder(clorId);
	}

	@Override
	public void addClientOrderListener(final ClientOrderListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	private void notifyClientOrderListeners(final ClientOrder clientOrder) {

		executorService.submit(new Runnable() {
			@Override
			public void run() {
				for (final ClientOrderListener listener : listeners) {
					listener.onMessage(clientOrder);
				}
			}
		});
	}

	@Override
	public void removeListener(ClientOrderListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	@Override
	public void createOffer(Integer clorId) {
		SecurityContext ctx = SecurityContextHolder.getContext();
		UserDetails userDetails = (UserDetails) ctx.getAuthentication()
				.getPrincipal();

		CompanyOffer co = new CompanyOffer();
		co.setClorId(clorId);
		co.setCompanyName(userDetails.getUsername());
		co.setExpirationDate(new Date());
		co.setFullfiledDate(new Date());
		
		orderDao.createOffer(co);
	}

}