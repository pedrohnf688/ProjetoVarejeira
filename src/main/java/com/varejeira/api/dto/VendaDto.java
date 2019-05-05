package com.varejeira.api.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.varejeira.api.modelo.Cliente;
import com.varejeira.api.modelo.Funcionario;
import com.varejeira.api.modelo.Produto;

public class VendaDto {

	private Integer id;
	private String dataAtual;
	private String cpf;
	private int pontos;
	private List<Produto> listaProdutos;
	
	public VendaDto() {
		super();
	}

	
	
	
}
