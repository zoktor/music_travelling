package eu.musictraveler.admin.bus;

import eu.musictraveler.admin.UserData;

/**
 * 
 * @author Pavel Gorohhovatski
 * 
 */
public interface UserService {

	UserData getUser(String login, String pass);
}