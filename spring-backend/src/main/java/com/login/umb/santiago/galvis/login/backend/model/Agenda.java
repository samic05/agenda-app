package com.login.umb.santiago.galvis.login.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgenda;
    private String fecha;
    private String asunto;
    private String actividad;

    @Override
    public String toString() {
        return "Agenda{" +
                "idAgenda=" + idAgenda +
                ", fecha='" + fecha + '\'' +
                ", asunto='" + asunto + '\'' +
                ", actividad='" + actividad + '\'' +
                '}';
    }
}
