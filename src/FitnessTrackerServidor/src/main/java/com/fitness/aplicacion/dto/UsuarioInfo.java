package com.fitness.aplicacion.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioInfo {
	private String nombre;
	private String email;
}
