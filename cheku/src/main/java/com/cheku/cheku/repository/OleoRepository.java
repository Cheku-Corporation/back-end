package com.cheku.cheku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

public interface OleoRepository extends JpaRepository<Oleo, Long>  {
}
