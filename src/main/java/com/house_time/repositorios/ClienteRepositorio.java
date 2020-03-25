package com.house_time.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
