package com.will.betterforms.repositories;

import com.will.betterforms.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
