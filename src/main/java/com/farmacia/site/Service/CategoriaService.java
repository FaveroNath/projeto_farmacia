package com.farmacia.site.Service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.site.Model.Categoria;
import com.farmacia.site.Repository.CategoriaRepository;

@Service
public class CategoriaService {

	private @Autowired CategoriaRepository repository;

	/**
	 * Verifica se a categoria nunca foi cadastrada.
	 * @param categoria
	 * @return Um optional contendo a categoria caso exista senão retorna um optional vazio.
	 * @since V.1.0
	 * @author Nathalia Favero
	 */
	public Optional<Categoria> salvarCategoria(Categoria categoria){
		
		Optional<Categoria> categoriaExiste = repository.findByDescricao(categoria.getDescricao());
		if (!categoriaExiste.isPresent()) {
			return Optional.ofNullable(categoria);
		} else {
			return Optional.empty();
		}
				
	}
	/**
	 * Verifica se a categoria a ser alterada já existe.
	 * @param id
	 * @param descricao
	 * @return Um optional contendo a cetegoria se não m optional vazio.
	 * @since V.1.0
	 * @author Nathalia Favero
	 */
	public Optional<Categoria> alterarCategoria(Long id, Categoria descricao){
		Optional<Categoria> categoriaAntiga = repository.findById(id);
		if (categoriaAntiga.isPresent()) {
			Categoria newCategoria = categoriaAntiga.get();
			newCategoria.setDescricao(descricao.getDescricao());
			return Optional.ofNullable(repository.save(newCategoria));
		} else {
			return Optional.empty();
		}
	}
}
