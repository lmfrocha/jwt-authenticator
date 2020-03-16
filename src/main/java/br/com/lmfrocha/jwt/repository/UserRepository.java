package br.com.lmfrocha.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lmfrocha.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByMail(String mail);
	
}
