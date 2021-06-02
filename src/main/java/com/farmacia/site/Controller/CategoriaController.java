package com.farmacia.site.Controller;

import java.util.List;
import java.util.Objects;
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
import com.farmacia.site.Repository.CategoriaRepository;
import com.farmacia.site.Service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
	
	private @Autowired CategoriaRepository repository;
	private @Autowired CategoriaService serivce;
	
	@GetMapping
	public ResponseEntity<Object> findAllCategoria(){
		List<Categoria> categorias = repository.findAll();
		if(categorias.isEmpty()) {
			return ResponseEntity.status(200).body("Não existe categorias cadastradas");
		}
		return ResponseEntity.status(200).body(categorias);
	}
	@GetMapping("/buscarId")
	public ResponseEntity<Categoria> findByIdCategoria(@RequestParam("id") long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@GetMapping("/buscar") 
	public ResponseEntity<Categoria> findByDescricaoCategoria(@RequestParam("descricao") String descricao){
		return repository.findByDescricao(descricao)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria descricao){
		return serivce.salvarCategoria(descricao).map(resp -> ResponseEntity.ok(repository.save(descricao)))
				.orElse(ResponseEntity.status(404).build());
	}
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Categoria> putCategoria(@PathVariable long id, @Valid @RequestBody Categoria categoria){
		return serivce.alterarCategoria(id, categoria)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@DeleteMapping("/deletar")
	public ResponseEntity<String> deleteCategoria(@RequestParam long id){
		Optional<Categoria> categoria = repository.findById(id);
		if (categoria.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Excluido com sucesso!");
		}
		return ResponseEntity.status(200).body("Categoria não encontrada!");
	}

}
