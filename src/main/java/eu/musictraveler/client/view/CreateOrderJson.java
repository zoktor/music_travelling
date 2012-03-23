package eu.musictraveler.client.view;

import java.io.IOException;
import java.util.Date;

import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.ClientOrder;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

//@AuthorizeInstantiation("USER")
public class CreateOrderJson extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

//	private static ObjectMapper jsonMapper = new ObjectMapper();

	public CreateOrderJson(final PageParameters parameters)
			throws JsonGenerationException, JsonMappingException, IOException {
		String location = parameters.get("location").toString();

		ClientOrder c = new ClientOrder();
		c.setLocation(location);
		c.setOrderDate(new Date());
		c.setOrderStateClvaCode("order_state.active");

		orderService.saveOrder(c);

		RequestCycle.get().getOriginalResponse().write("ok");
	}

	

}
