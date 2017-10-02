package com.will.betterforms.repositories;

import com.will.betterforms.Models.Secret;
import com.will.betterforms.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long>{
    List<Secret> findAllByOwner(User owner);

}
