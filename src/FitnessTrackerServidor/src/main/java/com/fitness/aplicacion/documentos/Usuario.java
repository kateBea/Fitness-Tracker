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
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa un usuario en el sistema con información personal detallada
 * y registros como planes de dieta, comidas registradas y rutinas de ejercicio.
 * Esta clase está anotada para su uso con MongoDB, proporciona métodos
 * utilitarios como equals, hashCode y builders.
 *
 * @version 1.0
 * */

// Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

// Spring Mongo
@Document(value = "Usuarios")

public class Usuario {

	/**
	 * Dirección de correo electrónico del usuario.
	 * Este campo sirve como identificador único en el sistema.
	 * */
	@Id
	@EqualsAndHashCode.Include
	@Field(value = "correo_electronico")
	@JsonProperty(value = "correo_electronico")
	private String email;

	/**
	 * El nombre de usuario del usuario.
	 * */
	@Field(value = "nombre_usuario")
	@JsonProperty(value = "nombre_usuario")
	private String nombreUsuario;

	/**
	 * La contraseña del usuario.
	 * */
	@Field(value = "password")
	@JsonProperty(value = "password")
	private String contrasena;

	/**
	 * El nombre propio del usuario.
	 * */
	@Field(value = "nombre")
	@JsonProperty(value = "nombre")
	private String nombre;

	/**
	 * El primer apellido del usuario.
	 * */
	@Field(value = "primer_apellido")
	@JsonProperty(value = "primer_apellido")
	private String primerApellido;

	/**
	 * El segundo apellido del usuario.
	 * */
	@Field(value = "segundo_apellido")
	@JsonProperty(value = "segundo_apellido")
	private String segundoApellido;

	/**
	 * La fecha de nacimiento del usuario.
	 * */
	@Field(value = "fecha_nacimiento")
	@JsonProperty(value = "fecha_nacimiento")
	private LocalDate fechaDeNacimiento;

	/**
	 * La fecha y hora en que el usuario se registró.
	 * */
	@Field(value = "fecha_registro")
	@JsonProperty(value = "fecha_registro")
	private LocalDateTime fechaRegistro;

	/**
	 * La fecha y hora de la última modificación realizada por el usuario.
	 * Ciertos campos de este modelo son modificables, este campo en concreto
	 * guarda la última fecha y hora de la última vez que el usuario realizó cambios
	 * sobre dichos campos.
	 * */
	@Field(value = "ultima_modificacion")
	@JsonProperty(value = "ultima_modificacion")
	private LocalDateTime fechaUltimaModificacion;

	/**
	 * La altura del usuario, en centímetros.
	 * */
	@Field(value = "altura")
	@JsonProperty(value = "altura")
	private float altura;

	/**
	 * La imagen de perfil del usuario, codificada en formato base 64.
	 *
	 * @implNote Por simplificación se ha implementado como String, está por probar
	 * implementar como array de bytes.
	 * */
	@Field(value = "imagen")
	@JsonProperty(value = "imagen")
	private String imagen;

	/**
	 * El peso objetivo del usuario, en kilogramos. Representa un valor
	 * de peso el cual el usuario toma como referencia para su peso real.
	 * */
	@Field(value = "objetivo_peso")
	@JsonProperty(value = "objetivo_peso")
	private float objetivoPeso;

	/**
	 * El peso actual del usuario, en kilogramos.
	 * */
	@Field(value = "peso")
	@JsonProperty(value = "peso")
	private float peso;

	/**
	 * El sexo del usuario, representado por un enum {@link Sexo}.
	 * */
	@Field(value = "sexo")
	@JsonProperty(value = "sexo")
	private Sexo sexo;

	/**
	 * Una lista de planes de dieta asociados con el usuario.
	 * */
	@Field(value = "dietas")
	@JsonProperty(value = "dietas")
	private List<Dieta> dietas;

	/**
	 * Una lista de comidas que el usuario ha registrado.
	 * */
	@Field(value = "comidas_registradas")
	@JsonProperty(value = "comidas_registradas")
	private List<Comida> comidasRegistradas;

	/**
	 * Una lista de rutinas de ejercicio asociadas con el usuario.
	 * */
	@Field(value = "rutinas")
	@JsonProperty(value = "rutinas")
	private List<Rutina> rutinas;
}