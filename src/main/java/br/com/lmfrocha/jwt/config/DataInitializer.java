package br.com.lmfrocha.jwt.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.lmfrocha.jwt.model.Const;
import br.com.lmfrocha.jwt.model.Role;
import br.com.lmfrocha.jwt.model.User;
import br.com.lmfrocha.jwt.repository.RoleRepository;
import br.com.lmfrocha.jwt.repository.UserRepository;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository user;

	@Autowired
	RoleRepository role;

	@Autowired
	PasswordEncoder passCode;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<User> users = user.findAll();
		if (users.isEmpty()) {
			createUser("Admin", "admin", passCode.encode("123456"), Const.ROLE_ADMIN);
			createUser("Cliente", "cliente", passCode.encode("123456"), Const.ROLE_CLIENT);
		}
	}

	public void createUser(String name, String mail, String password, String roleName) {
		Role role = new Role(roleName);
		this.role.save(role);
		User usr = new User(name, mail, password, Arrays.asList(role));
		this.user.save(usr);
	}

}
