package eu.musictraveler.company.view;

import eu.musictraveler.company.bus.OrderService;
import eu.musictraveler.company.model.ClientOrder;
import eu.musictraveler.general.BasePage;

import org.apache.wicket.IClusterable;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

//@AuthorizeInstantiation("COMPANY_USER")
public class ClientOrderPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	OrderService orderService;

	public ClientOrderPage(final PageParameters parameters) {
		final Integer clorId = parameters.get("clorId").toInteger();
		ClientOrder clientOrder = orderService.getClientOrder(clorId);
		
		Form<?> form = new Form("form"){
			 @Override
			protected void onSubmit() {
				super.onSubmit();
				orderService.createOffer(clorId);
			}
		};
		
		String string = "a";
		
        form.add(new TextField<String>("msgInput",new Model<String>(string)));

        add(form);
	}
}


