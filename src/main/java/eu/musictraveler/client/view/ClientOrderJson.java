package eu.musictraveler.client.view;

import java.io.IOException;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.ClientOrder;

//@AuthorizeInstantiation("USER")
public class ClientOrderJson extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

	private static ObjectMapper jsonMapper = new ObjectMapper();

	public ClientOrderJson(final PageParameters parameters)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<ClientOrder> list = orderService.findClientOrders();
		String json = jsonMapper.writeValueAsString(list);
		RequestCycle.get().getOriginalResponse().write(json);
	}
}
