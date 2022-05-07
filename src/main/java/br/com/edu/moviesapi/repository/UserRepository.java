package br.com.edu.moviesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.moviesapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    public User findByUsername(String username);
}
