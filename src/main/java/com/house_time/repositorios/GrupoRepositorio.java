package com.house_time.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Grupo;

public interface GrupoRepositorio extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByNome(String nome);

}
