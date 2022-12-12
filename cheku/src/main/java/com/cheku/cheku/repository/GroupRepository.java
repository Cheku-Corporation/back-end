package com.cheku.cheku.repository;

import com.cheku.cheku.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String name);
    List<Group> findAll();



}
