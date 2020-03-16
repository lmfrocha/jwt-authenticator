package br.com.lmfrocha.jwt.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmfrocha.jwt.model.User;
import br.com.lmfrocha.jwt.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository user;
	
	@Secured({ "ROLE_ADMIN" })
	@PostMapping
	public ResponseEntity<?> save(@RequestBody User user) {
		user = this.user.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping
	public ResponseEntity<?> edit(@RequestBody User user) {
		user = this.user.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_CLIENT"})
	@GetMapping
	public ResponseEntity<Page<User>> list (@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		Pageable pageable =  (Pageable) PageRequest.of(page, size, Sort.by("name"));
		return new ResponseEntity<Page<User>>((Page<User>) user.findAll((Sort) pageable),HttpStatus.OK);
	}
	
}