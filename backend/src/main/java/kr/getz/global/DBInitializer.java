package kr.getz.global;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kr.getz.user.domain.User;
import kr.getz.user.domain.UserType;
import kr.getz.user.service.UserService;

@Component
public class DBInitializer implements CommandLineRunner {

	private final UserService userService;

	public DBInitializer(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();
	}

	public void createUser() {
		User user = new User("test@test.com", "123qwe", "nick-test", UserType.USER);
		userService.createUser(user);
		User user1 = new User("test1@test.com", "123qwe", "nick", UserType.USER);
		userService.createUser(user1);
	}
}
