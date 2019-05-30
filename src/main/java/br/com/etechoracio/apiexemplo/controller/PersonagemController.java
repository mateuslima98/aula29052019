package br.com.etechoracio.apiexemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.apiexemplo.dao.PersonagemDAO;
import br.com.etechoracio.apiexemplo.model.Personagem;

@RestController
//Acessar a Classe"Personagem"//

@RequestMapping("/personagens")
public class PersonagemController {
	// Acessar o metodo//

	// precisa do dao estanciado //
	@Autowired
	private PersonagemDAO dao;

	@GetMapping
	public List<Personagem> listar() {
		// Pode trazer um , dois ou nenhum.//

		return dao.findAll();
	}
	// Quando quiser pegar algo so usar GET Find e Select = get

	@GetMapping("/{id}")
	public ResponseEntity<Personagem> buscar(@PathVariable Long id) {
		Optional<Personagem> resultado = dao.findById(id);

		if (resultado.isPresent()) {

			return ResponseEntity.ok(resultado.get());
		}

		// = ao Else // Mostrar vazio "Build"//
		return ResponseEntity.notFound().build();

	}
	@PostMapping
	@ResponseStatus()
	//Inserir = inclusao no banco - Post = Incluir Recurso //
	public void inserir(@RequestBody Personagem p) {
		dao.save(p);
	}

	// Ctrl + shift + o = Exlcuir o que nao esta sendo utilizado//
	//get -> "Pegar" recurso//
	//Find = GET //
	
	//Path = url//
	// RequestBody Incluir o personagem no corpo da requesição //
	
}
