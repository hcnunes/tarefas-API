package br.com.vertigo.estagio.tarefas.controller.form;

import java.time.LocalDate;

import br.com.vertigo.estagio.tarefas.model.Tarefa;

public class TarefaForm {

	private String titulo;
	private LocalDate data;
	private boolean estaCompleta = false;

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

	public Tarefa converter() {
		return new Tarefa(titulo, data, estaCompleta);
	}

}
