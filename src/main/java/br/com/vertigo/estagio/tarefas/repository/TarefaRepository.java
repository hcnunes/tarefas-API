package br.com.vertigo.estagio.tarefas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vertigo.estagio.tarefas.model.Tarefa;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findById(long id);
	
	Page<Tarefa> findById(long id, Pageable paginacao);

}
