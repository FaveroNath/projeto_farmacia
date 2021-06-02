package com.farmacia.site.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.site.Model.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
	
	Optional<Categoria> findByDescricao(String descricao);
	
	List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
