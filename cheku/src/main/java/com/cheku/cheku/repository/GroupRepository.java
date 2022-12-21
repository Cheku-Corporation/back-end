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
    /** Finds a group by its name. */
    Group findByGroupName(String name);

    /** Lists all the groups from the group table. */
    List<Group> findAll();

    Group findByGroupNameEncripted(String idGroup);
}
