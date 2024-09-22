package edu.eci.cvds.AppTareas.service;

import edu.eci.cvds.AppTareas.model.Tarea;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;

@Service
public class TareaService {

    private final HashMap<String, Tarea> tareas = new HashMap<>(); //Base de datos simulada

    public TareaService(){

    }

    public Tarea crear(Tarea tarea) {
        // Genera un UUID único para la tarea
        String id = UUID.randomUUID().toString();
        tarea.setId(id);
        tareas.put(id, tarea);
        return tarea;
    }

    public List<Tarea> obtenerTareas(){
        return tareas.keySet().stream().map(tareas::get).toList();
    }

    public Tarea obtenerTarea(String tareaId) {
        return tareas.get(tareaId);
    }
}
