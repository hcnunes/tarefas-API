package br.com.vertigo.estagio.tarefas.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.vertigo.estagio.tarefas.model.Tarefa;
import br.com.vertigo.estagio.tarefas.repository.TarefaRepository;

public class AtualizacaoTarefaForm {

	private String titulo;

	private LocalDate data;
	
	private boolean estaCompleta;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setEstaCompleta(boolean estaCompleta) {
		this.estaCompleta = estaCompleta;
	}

	public Tarefa atualizar(Long id, TarefaRepository tarefaRepository) {
		Tarefa tarefa = tarefaRepository.getOne(id);

		if (this.data != null) {
			tarefa.setData(this.data);
		}

		if (this.titulo != null) {
			tarefa.setTitulo(this.titulo);
		}
		
		if (this.estaCompleta != tarefa.isEstaCompleta()) {
			tarefa.setEstaCompleta(estaCompleta);
		}

		return tarefa;
	}

}
