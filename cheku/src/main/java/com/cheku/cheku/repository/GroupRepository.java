package com.cheku.cheku.repository;

import com.cheku.cheku.auxiliar_classes.NamesGroup;
import com.cheku.cheku.model.Group;
import com.cheku.cheku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    User findByName(String name);

    Group findById(long id);

    @Query(value = "SELECT name FROM groups where admin = id", nativeQuery = true)
    List<String> getAllbyNameString(long id);

    @Query(value = "SELECT name FROM groups", nativeQuery = true)
    List<NamesGroup> getAllbyNameString();

    @Query(value = "SELECT name FROM groups where name = name LIMIT 1", nativeQuery = true)
    NamesGroup getGroupbyNameString(String name);



}
