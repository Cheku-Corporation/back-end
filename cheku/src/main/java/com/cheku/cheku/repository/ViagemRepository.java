package com.cheku.cheku.repository;

import com.cheku.cheku.model.reafazer.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long>  {

}


