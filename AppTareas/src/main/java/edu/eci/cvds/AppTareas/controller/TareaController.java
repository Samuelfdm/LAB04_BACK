package edu.eci.cvds.AppTareas.controller;

import java.util.List;

import edu.eci.cvds.AppTareas.model.Tarea;
import edu.eci.cvds.AppTareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tareas")
public class TareaController {


    private TareaService tareaService;

    public TareaController(TareaService tareaService){
        this.tareaService = tareaService;
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.crear(tarea);
    }
}