package eu.musictraveler.client.view;

import java.io.IOException;

import eu.musictraveler.general.Session;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LoginJson extends WebPage {
	private static final long serialVersionUID = 1L;

	private static ObjectMapper jsonMapper = new ObjectMapper();

	public LoginJson(final PageParameters parameters)
			throws JsonGenerationException, JsonMappingException, IOException {
		String login = parameters.get("login").toString();
		String hash = parameters.get("pass").toString();
		
		Session session = (Session) getSession();
		
		if(!session.isSignedIn()){
			session.signIn(login, hash);
		}
		
		if (session.isSignedIn()) {
			String json = jsonMapper.writeValueAsString(session.getUserData());
			RequestCycle.get().getOriginalResponse().write(json);
			return;
		}

		RequestCycle.get().getOriginalResponse().write("fail");
	}

}
