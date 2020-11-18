package com.victor.sociotorcedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.entity.SocioCampanhaID;

public interface SocioCampanhaRepository extends JpaRepository<SocioCampanha, SocioCampanhaID>, JpaSpecificationExecutor<SocioCampanha>{
	
	@Query("SELECT DISTINCT id.idCampanha FROM SocioCampanha WHERE id.idTimeCoracao = :idTimeCoracao")
	List<Long> findDistincIdCampanhatByIdTimeCoracao(@Param("idTimeCoracao") Long idTimeCoracao);
	
	@Query("SELECT id.idCampanha FROM SocioCampanha WHERE id.idSocio = :idSocio")
	List<Long> findAllIdCampanhaByIdIdSocio(Long idSocio);
}
