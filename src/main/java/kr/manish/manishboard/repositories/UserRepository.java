package kr.manish.manishboard.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.manish.manishboard.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
    // custom finder methods :
	// below methods are implemented by spring boot data jpa
	
	User findByName(String name);
	
	Optional<User> findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmailOrPassword(String email, String password);
	
	List<User> findByUserNameContaining(String name);
}
