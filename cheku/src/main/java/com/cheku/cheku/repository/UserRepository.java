package com.cheku.cheku.repository;


import com.cheku.cheku.model.reafazer.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {

}
