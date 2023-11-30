package com.souza.tokiomarine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.souza.tokiomarine.model.Transferencia;
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer>{

}
