package eu.musictraveler.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LogOutPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public LogOutPage(final PageParameters parameters) {
		getSession().invalidate();
	}
}