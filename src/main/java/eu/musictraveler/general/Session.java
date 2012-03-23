package eu.musictraveler.general;

import eu.musictraveler.admin.UserData;
import eu.musictraveler.admin.bus.UserService;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Session extends AuthenticatedWebSession {
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	@SpringBean
	UserService userService;

	UserData userData;

	public Session(Request request) {
		super(request);
		Injector.get().inject(this);

		if (authenticationManager == null) {
			throw new IllegalStateException(
					"AdminSession requires an authenticationManager.");
		}
	}

	@Override
	public boolean authenticate(final String login, final String hash) {
    	userData = userService.getUser(login, hash);
//		return userData != null && userData.getUserId() != null;

		boolean authenticated = false;
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							login, hash));
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			authenticated = authentication.isAuthenticated();
		} catch (AuthenticationException e) {
			// logger.warn(format("User '%s' failed to login. Reason: %s",
			// username,
			// e.getMessage()));
			authenticated = false;
		}
		return authenticated;

	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();

		if (isSignedIn()) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				roles.add(authority.getAuthority());
			}
		}
		return roles;
	}

	public UserData getUserData() {
		return userData;
	}
}