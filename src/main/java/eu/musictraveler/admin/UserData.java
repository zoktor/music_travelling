package eu.musictraveler.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserData {
	Integer userId;
	String name;
	String email;

}
