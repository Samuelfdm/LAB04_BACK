package edu.eci.cvds.AppTareas.service;

import edu.eci.cvds.AppTareas.model.Tarea;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TareaService {

    private final HashMap<String, Tarea> tareas = new HashMap<>(); //Base de datos simulada

    public TareaService(){

    }

    public Tarea crear(Tarea tarea) {
        return tareas.put("123", tarea);
    }

    public List<Tarea> obtenerTareas(){
        return tareas.keySet().stream().map(tareas::get).toList();
    }
}
