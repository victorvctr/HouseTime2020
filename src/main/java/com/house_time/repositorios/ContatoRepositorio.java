package com.house_time.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Contato;



public interface ContatoRepositorio extends JpaRepository <Contato, Long> {

}
