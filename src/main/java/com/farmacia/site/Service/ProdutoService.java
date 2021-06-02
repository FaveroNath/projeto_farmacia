package com.farmacia.site.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.site.Model.Produto;
import com.farmacia.site.Repository.ProdutoRepository;

@Service
public class ProdutoService {
private @Autowired ProdutoRepository repository;
	
/**
 * Verica se o produto a ser cadastrado não existe no sistema.
 * @param produtos
 * @return Um optional cotendo os produtos, caso contrário um optional vazio.
 * @since V.1.0
 * @author Nathalia Favero
 */
	public Optional<Produto> salvarProduto(Produto produtos){
		Optional<Produto> produto = repository.findByTitulo(produtos.getTitulo());
		if (!produto.isPresent()) {
			return Optional.ofNullable(repository.save(produtos));
		} else {
			return Optional.empty();
		}
	}
	/**
	 * Verifica se o produto já existe antes de ser alterado.
	 * @param id
	 * @param produto
	 * @return Um optional contento o produto, senão um optional vazio.
	 * @since V.1.0
	 * @author Nathalia Favero
	 */
	public Optional<Produto> alterarProduto(Long id, Produto produto){
		Optional<Produto> produtoAntigo = repository.findById(id);
		if (produtoAntigo.isPresent()) {
			Produto newProduto = produtoAntigo.get();
			newProduto.setTitulo(produto.getTitulo());
			newProduto.setDescricao(produto.getDescricao());
			newProduto.setCategoria(produto.getCategoria());
			return Optional.ofNullable(repository.save(newProduto));
		} else {
			return Optional.empty();
		}
	}
}
