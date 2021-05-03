package com.challengedbc.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challengedbc.demo.model.Associado;

public interface AssociadoRepository  extends JpaRepository<Associado,Long> {

	Optional<Associado> findById(Integer id);

}
