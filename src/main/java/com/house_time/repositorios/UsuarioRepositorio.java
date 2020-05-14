package com.house_time.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
