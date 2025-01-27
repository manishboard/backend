package kr.manish.manishboard.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.manish.manishboard.entities.Task;

public interface TaskRepository extends JpaRepository<Task, String>{
    // custom finder methods :
	// below methods are implemented by spring boot data jpa
	
	// User findByName(String name);
	// Optional<User> findByEmail(String email);
	// User findByEmailAndPassword(String email, String password);
	// User findByEmailOrPassword(String email, String password);
	
	List<Task> findByDetailsContaining(String name);
}
