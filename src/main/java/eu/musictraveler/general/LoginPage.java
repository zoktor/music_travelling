package eu.musictraveler.general;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public final class LoginPage extends BasePage
{
	private static final long serialVersionUID = 1L;

    public LoginPage()
    {
        this(null);
    }

    public LoginPage(final PageParameters parameters)
    {
       add(new SignInPanel("signInPanel",true));
    }
}