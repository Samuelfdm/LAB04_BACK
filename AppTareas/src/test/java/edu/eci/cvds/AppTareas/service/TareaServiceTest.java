package edu.eci.cvds.AppTareas.service;

import edu.eci.cvds.AppTareas.model.Tarea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TareaServiceTest {

    private TareaService tareaService;
    private Tarea tarea1;
    private Tarea tarea2;

    @BeforeEach
    void setUp() {
        tareaService = new TareaService();

        // Inicializar las tareas antes de cada prueba
        tarea1 = new Tarea();
        tarea1.setNombre("Tarea 1");
        tarea1.setDescripcion("Descripción de la tarea 1");
        tarea1.setEstado(false);

        tarea2 = new Tarea();
        tarea2.setNombre("Tarea 2");
        tarea2.setDescripcion("Descripción de la tarea 2");
        tarea2.setEstado(false);
    }

    @Test
    void testCrearTarea() {
        Tarea tareaCreada = tareaService.crear(tarea1);

        assertNotNull(tareaCreada.getId()); // Asegurarse de que se generó un ID
        assertEquals("Tarea 1", tareaCreada.getNombre());
        assertFalse(tareaCreada.getEstado()); // Verificar que el estado es false
    }

    @Test
    void testObtenerTareas() {
        // Crear varias tareas
        tareaService.crear(tarea1);
        tareaService.crear(tarea2);

        // Verificar que se pueden obtener las tareas
        List<Tarea> tareas = tareaService.obtenerTareas();
        assertEquals(2, tareas.size());
    }

    @Test
    void testObtenerTarea() {
        // Crear tarea
        Tarea tareaCreada = tareaService.crear(tarea1);

        // Verificar que se obtiene la tarea
        Tarea tareaObtenida = tareaService.obtenerTarea(tareaCreada.getId());
        assertNotNull(tareaObtenida);
        assertEquals(tareaCreada.getId(), tareaObtenida.getId());
    }
}

