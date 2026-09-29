package com.salesianos.dam.AD_PSP__TAREA_1_API_de_Monumentos.TAREA1.API.de.Monumentos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monumentos {

    @Id @GeneratedValue
    private Long id;
    private String codigoPais;
    private String nombrePais;
    private String nombreCiudad;
    private Double latitud;
    private Double longitud;
    private String nombreMonumento;
    private String descripcionMonumento;
    private String urlfoto;
}
