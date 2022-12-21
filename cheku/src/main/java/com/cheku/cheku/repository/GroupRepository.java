package com.cheku.cheku.repository;

import com.cheku.cheku.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The GroupRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the group table in the database.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String name);

    List<Group> findAll();

}
