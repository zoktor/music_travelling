package eu.musictraveler.client.view;

import java.io.IOException;
import java.util.List;

import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.CompanyOffer;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

//@AuthorizeInstantiation("USER")
public class CompanyOffersJson extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

	private static  ObjectMapper jsonMapper = new ObjectMapper();

	public CompanyOffersJson(final PageParameters parameters)
			throws JsonGenerationException, JsonMappingException, IOException {
		Integer clorId = parameters.get("clorId").toInteger();
		List<CompanyOffer> list = orderService.findCompanyOffers(clorId);
		String json = jsonMapper.writeValueAsString(list);
		RequestCycle.get().getOriginalResponse().write(json);
	}
}
