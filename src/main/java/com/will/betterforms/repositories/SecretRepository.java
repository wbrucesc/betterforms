package com.will.betterforms.repositories;

import com.will.betterforms.Models.Secret;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long>{


}
