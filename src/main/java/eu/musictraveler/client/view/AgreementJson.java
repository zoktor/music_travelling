package eu.musictraveler.client.view;

import java.io.IOException;

import eu.musictraveler.company.bus.OrderService;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


//@AuthorizeInstantiation("USER")
public class AgreementJson extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

	private static ObjectMapper jsonMapper = new ObjectMapper();

	public AgreementJson(final PageParameters parameters)
			throws JsonGenerationException, JsonMappingException, IOException {
		Integer coofId = parameters.get("offerId").toInteger();
		boolean sucess = orderService.createAgreement(coofId);

		String json = jsonMapper.writeValueAsString(new AgreementResponse(
				sucess));
		RequestCycle.get().getOriginalResponse().write(json);
	}

}
