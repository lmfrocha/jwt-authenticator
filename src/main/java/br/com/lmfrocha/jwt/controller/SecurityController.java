package br.com.lmfrocha.jwt.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lmfrocha.jwt.model.User;

@Controller
@RequestMapping("/user-auth")
public class SecurityController {

	@ResponseBody
	@Secured({"ROLE_CLIENT", "ROLE_ADMIN"})
	public User user() {
		return (User) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
	}
	
}
