package com.fitness.aplicacion.documentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(value = "Usuarios")
public class Usuario {
	@Id
	@EqualsAndHashCode.Include
	@Field(value = "correo_electronico")
	@JsonProperty(value = "correo_electronico")
	private String email;

	@Field(value = "nombre_usuario")
	@JsonProperty(value = "nombre_usuario")
	private String nombre;

	@Field(value = "password")
	@JsonProperty(value = "password")
	private String contrasena;

	@Field(value = "primer_apellido")
	@JsonProperty(value = "primer_apellido")
	private String primerApellido;

	@Field(value = "segundo_apellido")
	@JsonProperty(value = "segundo_apellido")
	private String segundoApellido;

	@Field(value = "fecha_nacimiento")
	@JsonProperty(value = "fecha_nacimiento")
	private LocalDate fechaDeNacimiento;

	@Field(value = "fecha_registro")
	@JsonProperty(value = "fecha_registro")
	private LocalDate fechaRegistro;

	// En centímetros
	@Field(value = "altura")
	@JsonProperty(value = "altura")
	private float altura;

	// En kilogramos
	@Field(value = "peso")
	@JsonProperty(value = "peso")
	private float peso;

	@Field(value = "sexo")
	@JsonProperty(value = "sexo")
	private Sexo sexo;

	@Field(value = "dietas")
	@JsonProperty(value = "dietas")
	private List<Dieta> dietas;

	@Field(value = "rutinas")
	@JsonProperty(value = "rutinas")
	private List<Rutina> rutinas;
}