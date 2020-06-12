package com.house_time.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.house_time.models.Produto;

public interface ProdutoRepositorio extends JpaRepository <Produto,Long> {

	List<Produto> findByFabricante(String string);

}
