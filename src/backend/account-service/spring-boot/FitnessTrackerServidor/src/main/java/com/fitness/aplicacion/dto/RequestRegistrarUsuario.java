package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class RequestRegistrarUsuario {
	@JsonProperty(value = "correo_electronico", required = true)
	private String email;

	@JsonProperty(value = "password", required = true)
	private String contrasena;

	@JsonProperty(value = "nombre_usuario", required = true)
	private String nombreDeUsuario;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "primer_apellido")
	private String primerApellido;

	@JsonProperty(value = "segundo_apellido")
	private String segundoApellido;

	@JsonProperty(value = "fecha_nacimiento")
	private LocalDate fechaDeNacimiento;

	// En centímetros
	@JsonProperty(value = "altura")
	private float altura;

	// En kilogramos
	@JsonProperty(value = "peso")
	private float peso;

	@JsonProperty(value = "sexo")
	private String sexo;
}
