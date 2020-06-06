package com.house_time.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.house_time.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	
	boolean existsById(long codigo);
	boolean existsByEmail(String email);
	Usuario findByEmail(String email);
	
	
	

	
	Usuario findByDataNascimento(LocalDate data);
	
	Usuario findByEmailAndAtivoTrue(String email);
	Usuario findByCpfAndAtivoTrue(String cpf);
	
	@Query("select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = ?1")
	List<String> listaPermissoes(Usuario u);
	
}