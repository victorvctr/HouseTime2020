package com.house_time.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Endereco;

public interface EnderecoRepositorio extends JpaRepository <Endereco, Long> {

}
