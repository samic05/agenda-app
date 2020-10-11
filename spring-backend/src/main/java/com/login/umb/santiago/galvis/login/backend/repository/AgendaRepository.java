package com.login.umb.santiago.galvis.login.backend.repository;

import com.login.umb.santiago.galvis.login.backend.model.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long> {


}
