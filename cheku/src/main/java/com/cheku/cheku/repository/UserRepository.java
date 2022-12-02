package com.cheku.cheku.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {

}
