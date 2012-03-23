package eu.musictraveler.company.view;

import java.util.ArrayList;
import java.util.List;

import eu.musictraveler.client.ClientOrderListener;
import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.ClientOrder;
import eu.musictraveler.general.BasePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.push.AbstractPushEventHandler;
import org.wicketstuff.push.IPushChannel;
import org.wicketstuff.push.IPushEventContext;
import org.wicketstuff.push.IPushNode;
import org.wicketstuff.push.cometd.CometdPushService;

//@AuthorizeInstantiation("COMPANY_USER")
public class OrderListPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

	public OrderListPage() {
		super();
		List<IColumn<ClientOrder>> columns = new ArrayList<IColumn<ClientOrder>>();

		columns.add(new PropertyColumn<ClientOrder>(new Model<String>("id"),
				"clorId", "clorId") {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(
					Item<ICellPopulator<ClientOrder>> cellItem,
					String componentId, IModel<ClientOrder> model) {
				ClientOrder clientOrder = model.getObject();
				String id = clientOrder.getClorId().toString();
				PageParameters param = new PageParameters();
				param.set("clorId", id);
				BookmarkablePageLink<ClientOrder> link = new BookmarkablePageLink<ClientOrder>(
						"link", ClientOrderPage.class, param);
				link.add(new Label("label", id));
				cellItem.add(new LinkPanel<ClientOrder>(componentId, model,
						link));

			}

		});
		columns.add(new PropertyColumn<ClientOrder>(new Model<String>(
				"Offers count"), "offersCount", "offersCount"));
		columns.add(new PropertyColumn<ClientOrder>(new Model<String>(
				"Order state"), "orderStateClvaCode", "orderStateClvaCode"));
		columns.add(new PropertyColumn<ClientOrder>(new Model<String>(
				"Location"), "location"));
		columns.add(new PropertyColumn<ClientOrder>(new Model<String>(
				"Order date"), "orderDate"));
		AjaxTable<ClientOrder> table = new AjaxTable<ClientOrder>("table",
				columns, new SortableClientOrderDataProvider(), 11);

		add(table);

		// initCometd(clientOrderLabel, notifier);
		initCometd(table);
	}

	@Override
	protected void configureResponse(WebResponse response) {
		super.configureResponse(response);
		response.setHeader("Cache-Control",
				"no-cache, max-age=0,must-revalidate, no-store");
		response.setHeader("Expires", "-1");
		response.setHeader("Pragma", "no-cache");
	}

	private void initCometd(final AjaxTable<ClientOrder> table) {
		final CometdPushService pushService = CometdPushService.get();
		IPushChannel<ClientOrder> channel = pushService
				.createChannel("clientOrderList");
		final IPushNode<ClientOrder> pushNode = pushService.installNode(this,
				new AbstractPushEventHandler<ClientOrder>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onEvent(final AjaxRequestTarget target,
							final ClientOrder clientOrder,
							final IPushNode<ClientOrder> node,
							final IPushEventContext<ClientOrder> ctx) {
						notifier.create(target, "New Client Order",
								clientOrder.getLocation());
						table.modelChanged();
						target.add(table);
					}

				});

		pushService.connectToChannel(pushNode, channel);

		orderService.addClientOrderListener(new ClientOrderListener() {

			@Override
			public void onMessage(ClientOrder clientOrder) {
				if (pushService.isConnected(pushNode))
					pushService.publish(pushNode, clientOrder);
				else
					orderService.removeListener(this);
			}

		});
	}

}

class AjaxTable<T> extends AjaxFallbackDefaultDataTable<T> {

	private static final long serialVersionUID = 1L;

	public AjaxTable(String id, List<IColumn<T>> columns,
			ISortableDataProvider<T> dataProvider, int rowsPerPage) {
		super(id, columns, dataProvider, rowsPerPage);
	}

}
