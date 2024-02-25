package com.example.tareaapirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parques_andalucia")
public class ParquesController {


    @Autowired
    private ParquesRepository repository;

    @Autowired
    private SecurityService security;

    @GetMapping("/")
    public List<Parques> getAll(){return repository.findAll();}

    @GetMapping("/id/{id}")
    public Parques getParquesbyID(@PathVariable Long id){return repository.getParquesById(id);}

    @GetMapping("/nombre/{nombre}")
    public Parques getParquesByNombre(@PathVariable String nombre){
        return repository.getParquesByNombre(nombre);
    }

    @GetMapping("/provincia/{provincia}")
    public List<Parques> getParquesByProvincia(@PathVariable String provincia){
        return repository.findByProvincia(provincia);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Parques> getParquesByTipo(@PathVariable String tipo){
        return repository.findByTipo(tipo);
    }

    @GetMapping("/superficie/{superficie}")
    public List<Parques> getParquesBySuperficie(@PathVariable Integer superficie){
        return repository.findBySuperficie(superficie);
    }

    @GetMapping("/descripcion/{descripcion}")
    public List<Parques> getParquesByDescripcion(@PathVariable String descripcion){
        return repository.findByDescripcion(descripcion);
    }

    @GetMapping("/clima/{clima}")
    public List<Parques> getParquesByClima(@PathVariable String clima){
        return repository.findByClima(clima);
    }

    @GetMapping("/actividades/{actividades}")
    public List<Parques> getParquesByActividades(@PathVariable String actividades){
        return repository.findByActividades(actividades);
    }

    @GetMapping("/nombreParques")
    public List<String> getNombreParques() {
        return repository.findAllBy();
    }

    @PostMapping("/post")
    public ResponseEntity<Parques> nuevo(@RequestBody Parques parques, @RequestParam String token) {
        if (security.validateToken(token)) {
            Parques nuevoParque = repository.save(parques);
            return new ResponseEntity<>(nuevoParque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parques> put(@PathVariable Long id, @RequestBody Parques parques, @RequestParam String token) {
        if (!security.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            var parqueSeleccionado = repository.findById(id);

            if (parqueSeleccionado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                var parque = parqueSeleccionado.get();
                parque.setNombre(parques.getNombre());
                parque.setProvincia(parques.getProvincia());
                parque.setTipo(parques.getTipo());
                parque.setSuperficie(parques.getSuperficie());
                parque.setDescripcion(parques.getDescripcion());
                parque.setClima(parques.getClima());
                parque.setActividades(parques.getActividades());

                return new ResponseEntity<>(repository.save(parque), HttpStatus.OK);
            }
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Parques> delete(@PathVariable Long id, @RequestParam String token) {
        if (!security.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (repository.existsById(id)) {
            Parques deletedParque = repository.findById(id).get();
            repository.deleteById(id);
            return new ResponseEntity<>(deletedParque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
