package edu.eci.cvds.AppTareas.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.eci.cvds.AppTareas.model.Tarea;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("file")
public class FileTareaRepository implements TareaPersistence {

    // Inyectamos la ruta del archivo
    private String filePath;
    private final Gson gson = new Gson();
    private final Type listType = new TypeToken<ArrayList<Tarea>>() {}.getType();

    public FileTareaRepository(@Value("${tarea.file.path}") String filePath) {
        this.filePath = filePath;
        inicializarArchivo();  // Verifica si el archivo existe, de lo contrario lo crea
    }

    public void inicializarArchivo() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Crea el archivo si no existe
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo: " + filePath, e);
        }
    }

    @Override
    public Tarea save(Tarea tarea) {
        List<Tarea> tareas = findAll();

        // Encontramos la tarea existente
        Optional<Tarea> tareaExistente = tareas.stream()
                .filter(t -> t.getId().equals(tarea.getId()))
                .findFirst();

        if (tareaExistente.isPresent()) {
            // Reemplazamos la tarea existente en su posición original
            int index = tareas.indexOf(tareaExistente.get());
            tareas.set(index, tarea);
        } else {
            // Si la tarea no existe, la añadimos al final
            tareas.add(tarea);
        }

        escribirEnArchivo(tareas); // Guardamos la lista actualizada
        return tarea;
    }

    @Override
    public Optional<Tarea> findById(String id) {
        return findAll().stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public List<Tarea> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String content = reader.lines().reduce("", (acc, line) -> acc + line);
            if (content.isEmpty()) {
                return new ArrayList<>();  // Si el archivo está vacío, retorna una lista vacía
            }
            return gson.fromJson(content, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer tareas desde archivo", e);
        }
    }

    @Override
    public void deleteById(String id) {
        List<Tarea> tareas = findAll();
        tareas.removeIf(t -> t.getId().equals(id));
        escribirEnArchivo(tareas);
    }

    private void escribirEnArchivo(List<Tarea> tareas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(tareas);
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir tareas en el archivo", e);
        }
    }
}
