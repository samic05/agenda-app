package com.login.umb.santiago.galvis.login.backend.service;

import com.login.umb.santiago.galvis.login.backend.model.Agenda;
import com.login.umb.santiago.galvis.login.backend.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendaService implements ServiceCaller<Agenda> {

    private final AgendaRepository agendaRepository;

    @Override
    public List<Agenda> getAll() {
        List<Agenda> agendaList = new ArrayList<>();
        agendaRepository.findAll().forEach(agendaList::add);
        System.out.println(agendaList);
        return agendaList;
    }

    @Override
    public Agenda save(Agenda request) {
        System.out.println(request);
        return agendaRepository.save(request);
    }
}
