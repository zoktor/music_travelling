package eu.musictraveler.admin.bus;

import javax.annotation.Resource;

import eu.musictraveler.admin.UserData;

import org.springframework.stereotype.Service;

/**
 * @author Pavel Gorohhovatski
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;

	@Override
	public UserData getUser(String login, String pass) {
		return userDao.getUser(login, pass);
	}

}