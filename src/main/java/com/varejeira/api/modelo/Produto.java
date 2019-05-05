package com.varejeira.api.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private List<EnumInteresse> pedidos;

	public Produto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<EnumInteresse> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<EnumInteresse> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", pedidos=" + pedidos + "]";
	}

}
