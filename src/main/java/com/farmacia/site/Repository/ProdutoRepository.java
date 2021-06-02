package com.farmacia.site.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.site.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Optional<Produto> findByTitulo(String titulo);
	//List<Produto> findByAllTituloContainingIgoneCase(String titulo);
}
