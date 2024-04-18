package com.example.springbootpubsub.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.example.springbootpubsub.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    // Define custom query methods if needed
    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsernameCustomQuery(@Param("username") String username);
}
