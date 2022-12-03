package com.cheku.cheku.repository;

import com.cheku.cheku.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface GroupRepository extends CrudRepository<Group, Long> {

}
