package com.cheku.cheku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.Localization;
import com.cheku.cheku.model.Combustivel;

@Repository
public interface CombustivelRepository extends JpaRepository<Combustivel, Long>  {
}

