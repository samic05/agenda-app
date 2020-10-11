package com.login.umb.santiago.galvis.login.backend.controller;

import com.login.umb.santiago.galvis.login.backend.model.Agenda;
import com.login.umb.santiago.galvis.login.backend.service.AgendaService;
import com.login.umb.santiago.galvis.login.backend.service.ServiceCaller;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {

    private final ServiceCaller<Agenda> agendaService;
    @PostMapping("get-all")
    public ResponseEntity<List<Agenda>> LoginUser() {
        return ResponseEntity.ok(agendaService.getAll());
    }

    @PostMapping("save")
    public ResponseEntity<Agenda> saveUser(@RequestBody Agenda agenda) {
        return ResponseEntity.ok(agendaService.save(agenda));
    }

}
