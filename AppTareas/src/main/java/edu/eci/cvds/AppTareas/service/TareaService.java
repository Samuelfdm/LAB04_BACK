package edu.eci.cvds.AppTareas.service;

import edu.eci.cvds.AppTareas.model.Tarea;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TareaService {

    private final HashMap<String, Tarea> tareas = new HashMap<>(); //Base de datos simulada

    public Tarea crear(Tarea tarea) {
        return tareas.put("123", tarea);
    }
}
