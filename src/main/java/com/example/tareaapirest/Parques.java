package com.example.tareaapirest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "parques")
public class Parques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String provincia;

    private String tipo;

    private Integer superficie;

    private String descripcion;

    private String clima;

    private String actividades;


}
