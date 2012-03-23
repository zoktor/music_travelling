package eu.musictraveler.general;

import eu.musictraveler.client.view.AgreementJson;
import eu.musictraveler.client.view.ClientOrderJson;
import eu.musictraveler.client.view.CompanyOffersJson;
import eu.musictraveler.client.view.CreateOrderJson;
import eu.musictraveler.client.view.LoginJson;
import eu.musictraveler.company.view.ClientOrderPage;
import eu.musictraveler.company.view.OrderListPage;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

import com.google.code.jqwicket.JQComponentOnBeforeRenderListener;
import com.google.code.jqwicket.JQContributionConfig;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see eu.musictraveler.Start#main(String[])
 */
@Component
public class WicketApplication extends AuthenticatedWebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<OrderListPage> getHomePage() {
		return OrderListPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return Session.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));

		JQContributionConfig config = new JQContributionConfig();
		config.withJQueryUiJs("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js");
		config.withJQueryUiCss("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css");
		getComponentPreOnBeforeRenderListeners().add(
				new JQComponentOnBeforeRenderListener(config));

		mountPage("/client/order/create", CreateOrderJson.class);
		mountPage("/client/order/list", ClientOrderJson.class);
		mountPage("/client/order/view", ClientOrderPage.class);
		mountPage("/client/company/offers/list", CompanyOffersJson.class);
		mountPage("/client/agreement", AgreementJson.class);
		mountPage("/client/login", LoginJson.class);
		mountPage("/logout", LogOutPage.class);
		mountPage("/login", LoginPage.class);

	}
}
