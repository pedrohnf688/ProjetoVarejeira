package com.varejeira.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varejeira.api.modelo.Venda;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Integer> {

}
