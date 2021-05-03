package com.challengedbc.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challengedbc.demo.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta,Long> {

	Optional<Pauta> findById(Integer id);

}
