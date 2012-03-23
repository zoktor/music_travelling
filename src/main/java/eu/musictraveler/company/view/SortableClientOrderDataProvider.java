package eu.musictraveler.company.view;

import java.util.Iterator;
import java.util.List;

import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.ClientOrder;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SortableClientOrderDataProvider extends
		SortableDataProvider<ClientOrder> {

	@SpringBean
	OrderService orderService;
	List<ClientOrder> list;

	public SortableClientOrderDataProvider() {
		// set default sort
		Injector.get().inject(this);
		setSort("clorId", SortOrder.ASCENDING);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Iterator<? extends ClientOrder> iterator(int arg0, int arg1) {
		return list.iterator();
	}

	public IModel<ClientOrder> model(ClientOrder clientOrder) {
		return new Model<ClientOrder>(clientOrder);
	}


	@Override
	public int size() {
		return (list = orderService.findClientOrders()).size();
	}

}
