package com.cheku.cheku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.Localization;

/**
 * The LocalizationRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the localization table in the database.
 */

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long>  {

    @Query(value = "SELECT * FROM localization WHERE trip_id = ?1 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Localization getLast(Long trip_id);
}
