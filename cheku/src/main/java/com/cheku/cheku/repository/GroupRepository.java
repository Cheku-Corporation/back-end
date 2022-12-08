package com.cheku.cheku.repository;

import com.cheku.cheku.model.Group;
import com.cheku.cheku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
    List<Group> findAll();

    Group findById(long id);


}
