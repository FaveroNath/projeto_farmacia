package com.farmacia.site.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.site.Model.Categoria;
import com.farmacia.site.Model.Produto;
import com.farmacia.site.Repository.ProdutoRepository;
import com.farmacia.site.Service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

	private @Autowired ProdutoRepository repository;
	private @Autowired ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Object> findAllProduto(){
		List<Produto> produtos = repository.findAll();
		if(!produtos.isEmpty()) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.ok("Não existe produtos cadastrados!");
		}
	}
	
	@GetMapping("/buscaId")
	public ResponseEntity<Produto> findByIDProduto(@RequestParam("id") long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<Produto> findByIDProduto(@RequestParam("titulo") String titulo){
		return repository.findByTitulo(titulo)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return service.salvarProduto(produto)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@PutMapping("/alterar")
	public ResponseEntity<Produto> putProduto(@RequestParam("id") long id, @Valid @RequestBody Produto produto){
		return service.alterarProduto(id, produto).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@DeleteMapping("/deletar")
	public ResponseEntity<String> deleteProduto(@RequestParam long id){
		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Excluido com sucesso!");
		}
		return ResponseEntity.status(200).body("Produto não encontrado!");
	}

}
