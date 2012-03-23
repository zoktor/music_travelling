package eu.musictraveler.admin.bus;

import eu.musictraveler.admin.UserData;
import eu.musictraveler.general.bus.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao {
	static Integer count = 0;

	UserData getUser(String login, String pass) {
		count++;
		return new UserData(count, login, "micky@pesh.mine.nu.ee");
	}

}