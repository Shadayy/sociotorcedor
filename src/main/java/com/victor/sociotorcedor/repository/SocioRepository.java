package com.victor.sociotorcedor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.victor.sociotorcedor.entity.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>, JpaSpecificationExecutor<Socio> {
	
	Optional<Socio> findByEmail(String email);
}
