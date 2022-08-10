package br.com.vertigo.estagio.tarefas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vertigo.estagio.tarefas.controller.dto.TarefaDto;
import br.com.vertigo.estagio.tarefas.controller.form.AtualizacaoTarefaForm;
import br.com.vertigo.estagio.tarefas.controller.form.TarefaForm;
import br.com.vertigo.estagio.tarefas.model.Tarefa;
import br.com.vertigo.estagio.tarefas.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping
	public Page<TarefaDto> listar(@RequestParam(required = false) String idPesquisa , 
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		if (idPesquisa == null) {
			Page<Tarefa> tarefas = tarefaRepository.findAll(paginacao);
			return TarefaDto.converter(tarefas);
		} else {
			long id = Long.parseLong(idPesquisa);
			Page<Tarefa> tarefas = tarefaRepository.findById(id, paginacao);
			return TarefaDto.converter(tarefas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TarefaDto> detalhar(@PathVariable Long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if (tarefa.isPresent()) {
			return ResponseEntity.ok(new TarefaDto(tarefa.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TarefaDto> cadastrar(@RequestBody TarefaForm form, UriComponentsBuilder uriBuilder) {
		Tarefa tarefa = form.converter();
		tarefaRepository.save(tarefa);
		
		URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).body(new TarefaDto(tarefa));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TarefaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTarefaForm form) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		if (optional.isPresent()) {
			Tarefa tarefa = form.atualizar(id, tarefaRepository);
			return ResponseEntity.ok(new TarefaDto(tarefa));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Tarefa> optional = tarefaRepository.findById(id);
		if (optional.isPresent()) {
			tarefaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
