package br.com.lmfrocha.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lmfrocha.jwt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
