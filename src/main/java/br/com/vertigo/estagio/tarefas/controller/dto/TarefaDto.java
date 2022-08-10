package br.com.vertigo.estagio.tarefas.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.vertigo.estagio.tarefas.model.Tarefa;

public class TarefaDto {

	private Long id;
	private String titulo;
	private LocalDate data;
	private boolean estaCompleta;

	public TarefaDto(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.data = tarefa.getData();
		this.setEstaCompleta(tarefa.isEstaCompleta());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isEstaCompleta() {
		return estaCompleta;
	}

	public void setEstaCompleta(boolean estaCompleta) {
		this.estaCompleta = estaCompleta;
	}

	public static List<TarefaDto> converter(List<Tarefa> tarefa) {
		return tarefa.stream().map(TarefaDto::new).collect(Collectors.toList());
	}

	public static Page<TarefaDto> converter(Page<Tarefa> tarefa) {
		return tarefa.map(TarefaDto::new);
	}

}
