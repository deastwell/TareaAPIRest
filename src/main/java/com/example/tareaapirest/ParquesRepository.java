package com.example.tareaapirest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParquesRepository extends JpaRepository<Parques, Long> {
    List<Parques> findAll();


    Parques getParquesById(Long id);

    Parques getParquesByNombre(String nombre);

    List<Parques> findByProvincia(String provincia);


    List<Parques> findByTipo(String tipo);

    List<Parques> findBySuperficie(Integer superficie);

    List<Parques> findByDescripcion(String descripcion);


    List<Parques> findByClima(String clima);

    List<Parques> findByActividades(String actividades);

    List<String> findAllBy();
}
